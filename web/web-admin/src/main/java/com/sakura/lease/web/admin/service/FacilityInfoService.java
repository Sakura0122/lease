package com.sakura.lease.web.admin.service;

import com.sakura.lease.model.entity.FacilityInfo;
import com.baomidou.mybatisplus.extension.service.IService;
import com.sakura.lease.model.enums.ItemType;

import java.util.List;

/**
* @author liubo
* @description 针对表【facility_info(配套信息表)】的数据库操作Service
* @createDate 2023-07-24 15:48:00
*/
public interface FacilityInfoService extends IService<FacilityInfo> {

    /**
     * 根据类型查询配套信息列表
     * @param type 类型
     * @return 配套信息列表
     */
    List<FacilityInfo> listFacility(ItemType type);
}
