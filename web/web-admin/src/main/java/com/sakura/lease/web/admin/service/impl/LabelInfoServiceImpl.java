package com.sakura.lease.web.admin.service.impl;

import cn.hutool.core.util.ObjUtil;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sakura.lease.model.entity.LabelInfo;
import com.sakura.lease.model.enums.ItemType;
import com.sakura.lease.web.admin.service.LabelInfoService;
import com.sakura.lease.web.admin.mapper.LabelInfoMapper;
import org.springframework.stereotype.Service;

import java.util.List;

/**
* @author liubo
* @description 针对表【label_info(标签信息表)】的数据库操作Service实现
* @createDate 2023-07-24 15:48:00
*/
@Service
public class LabelInfoServiceImpl extends ServiceImpl<LabelInfoMapper, LabelInfo> implements LabelInfoService{

    /**
     * 根据类型查询标签列表
     * @param type 类型
     * @return 标签列表
     */
    @Override
    public List<LabelInfo> listLabel(ItemType type) {
        List<LabelInfo> list = lambdaQuery()
                .eq(ObjUtil.isNotEmpty(type), LabelInfo::getType, type)
                .list();
        return list;
    }
}




