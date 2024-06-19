package com.sakura.lease.web.admin.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.sakura.lease.model.entity.AttrKey;
import com.sakura.lease.model.entity.AttrValue;
import com.sakura.lease.web.admin.mapper.AttrKeyMapper;
import com.sakura.lease.web.admin.service.AttrKeyService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sakura.lease.web.admin.service.AttrValueService;
import com.sakura.lease.web.admin.vo.attr.AttrKeyVo;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.List;

/**
* @author liubo
* @description 针对表【attr_key(房间基本属性表)】的数据库操作Service实现
* @createDate 2023-07-24 15:48:00
*/
@Service
public class AttrKeyServiceImpl extends ServiceImpl<AttrKeyMapper, AttrKey> implements AttrKeyService{

    @Resource
    private AttrKeyMapper attrKeyMapper;

    @Resource
    private AttrValueService attrValueService;

    /**
     * 查询全部属性名称和属性值列表
     * @return
     */
    @Override
    public List<AttrKeyVo> listAttrInfo() {
        List<AttrKeyVo> list = attrKeyMapper.listAttrInfo();
        return list;
    }

    /**
     * 根据id删除属性名称和下面的属性值
     * @param attrKeyId
     */
    @Override
    public void removeAttrKeyById(String attrKeyId) {
        // 删除属性名称
        removeById(attrKeyId);

        // 删除属性名称下的所有属性值
        LambdaQueryWrapper<AttrValue> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(AttrValue::getAttrKeyId, attrKeyId);
        attrValueService.remove(wrapper);
    }
}




