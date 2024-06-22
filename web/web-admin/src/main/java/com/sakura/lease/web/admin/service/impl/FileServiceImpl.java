package com.sakura.lease.web.admin.service.impl;

import com.sakura.lease.common.minio.MinioProperties;
import com.sakura.lease.web.admin.service.FileService;
import io.minio.*;
import io.minio.errors.*;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

@Service
public class FileServiceImpl implements FileService {

    @Resource
    private MinioClient minioClient;

    @Resource
    private MinioProperties minioProperties;


    /**
     * 上传文件
     *
     * @param file 文件
     * @return 文件访问路径
     */
    @Override
    public String upload(MultipartFile file) throws ServerException, InsufficientDataException, ErrorResponseException, IOException, NoSuchAlgorithmException, InvalidKeyException, InvalidResponseException, XmlParserException, InternalException {
        // 检查指定的存储桶是否存在于MinIO服务器中
        boolean bucketExists = minioClient.bucketExists(
                BucketExistsArgs.builder()
                        .bucket(minioProperties.getBucketName())
                        .build());
        if (!bucketExists) {

            // 如果存储桶不存在，则创建一个新的存储桶
            minioClient.makeBucket(MakeBucketArgs.builder().bucket(minioProperties.getBucketName()).build());

            // 为新创建的存储桶设置策略，以允许上传和下载文件
            minioClient.setBucketPolicy(SetBucketPolicyArgs.builder()
                    .bucket(minioProperties.getBucketName())
                    .config(createBucketPolicyConfig(minioProperties.getBucketName()))
                    .build());
        }

        // 根据当前日期和UUID生成唯一的文件名，以确保文件名的唯一性
        String filename = new SimpleDateFormat("yyyyMMdd")
                .format(new Date()) + "/" + UUID.randomUUID() + "-" + file.getOriginalFilename();

        // 将文件上传到MinIO服务器的指定存储桶中
        minioClient.putObject(PutObjectArgs.builder().
                bucket(minioProperties.getBucketName()).
                object(filename).
                stream(file.getInputStream(), file.getSize(), -1).
                contentType(file.getContentType()).build());

        // 返回文件在MinIO服务器上的完整URL路径
        return String.join("/", minioProperties.getEndpoint(), minioProperties.getBucketName(), filename);
    }

    private String createBucketPolicyConfig(String bucketName) {
        return """
                {
                  "Statement" : [ {
                    "Action" : "s3:GetObject",
                    "Effect" : "Allow",
                    "Principal" : "*",
                    "Resource" : "arn:aws:s3:::%s/*"
                  } ],
                  "Version" : "2012-10-17"
                }
                """.formatted(bucketName);
    }
}
