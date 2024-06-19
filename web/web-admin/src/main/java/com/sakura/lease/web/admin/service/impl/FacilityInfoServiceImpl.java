package com.sakura.lease.web.admin.service.impl;

import cn.hutool.core.util.ObjUtil;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sakura.lease.model.entity.FacilityInfo;
import com.sakura.lease.model.enums.ItemType;
import com.sakura.lease.web.admin.service.FacilityInfoService;
import com.sakura.lease.web.admin.mapper.FacilityInfoMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import java.util.List;

/**
* @author liubo
* @description 针对表【facility_info(配套信息表)】的数据库操作Service实现
* @createDate 2023-07-24 15:48:00
*/
@Service
public class FacilityInfoServiceImpl extends ServiceImpl<FacilityInfoMapper, FacilityInfo> implements FacilityInfoService{

    /**
     * 根据类型查询配套信息列表
     * @param type 类型
     * @return 配套信息列表
     */
    @Override
    public List<FacilityInfo> listFacility(ItemType type) {
        List<FacilityInfo> list = lambdaQuery()
                .eq(ObjUtil.isNotEmpty(type), FacilityInfo::getType, type)
                .list();
        return list;
    }
}




