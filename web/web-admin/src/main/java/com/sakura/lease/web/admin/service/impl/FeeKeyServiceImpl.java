package com.sakura.lease.web.admin.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.sakura.lease.model.entity.FeeKey;
import com.sakura.lease.model.entity.FeeValue;
import com.sakura.lease.web.admin.mapper.FeeKeyMapper;
import com.sakura.lease.web.admin.service.FeeKeyService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sakura.lease.web.admin.service.FeeValueService;
import com.sakura.lease.web.admin.vo.fee.FeeKeyVo;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author liubo
 * @description 针对表【fee_key(杂项费用名称表)】的数据库操作Service实现
 * @createDate 2023-07-24 15:48:00
 */
@Service
public class FeeKeyServiceImpl extends ServiceImpl<FeeKeyMapper, FeeKey> implements FeeKeyService {

    @Resource
    private FeeKeyMapper feeKeyMapper;

    @Resource
    private FeeValueService feeValueService;

    /**
     * 查询全部杂费名称和杂费值列表
     *
     * @return
     */
    @Override
    public List<FeeKeyVo> feeInfoList() {
        List<FeeKeyVo> list = feeKeyMapper.feeInfoList();
        return list;
    }

    /**
     * 根据id删除杂费名称和下面的属性值
     * @param feeKeyId
     */
    @Override
    public void removeFeeKeyById(String feeKeyId) {
        // 删除杂费名称
        removeById(feeKeyId);
        // 删除杂费值
        LambdaQueryWrapper<FeeValue> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(FeeValue::getFeeKeyId, feeKeyId);
        feeValueService.remove(wrapper);
    }
}




