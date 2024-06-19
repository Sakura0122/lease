package com.sakura.lease.web.admin.service;

import com.sakura.lease.model.entity.LabelInfo;
import com.baomidou.mybatisplus.extension.service.IService;
import com.sakura.lease.model.enums.ItemType;

import java.util.List;

/**
* @author liubo
* @description 针对表【label_info(标签信息表)】的数据库操作Service
* @createDate 2023-07-24 15:48:00
*/
public interface LabelInfoService extends IService<LabelInfo> {

    /**
     * 根据类型查询标签列表
     * @param type 类型
     * @return 标签列表
     */
    List<LabelInfo> listLabel(ItemType type);
}
