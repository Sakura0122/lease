package com.sakura.lease.common.mybatisplus;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

/**
 * @author: sakura
 * @date: 2024/6/5 下午9:29
 * @description:
 */
@Configuration
@MapperScan("com.sakura.lease.web.*.mapper")
public class MybatisPlusConfiguration {
}
