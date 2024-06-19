package com.sakura.lease.web.admin.controller.apartment;


import com.sakura.lease.common.result.Result;
import com.sakura.lease.model.entity.FeeKey;
import com.sakura.lease.model.entity.FeeValue;
import com.sakura.lease.web.admin.service.FeeKeyService;
import com.sakura.lease.web.admin.service.FeeValueService;
import com.sakura.lease.web.admin.vo.fee.FeeKeyVo;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Tag(name = "房间杂费管理")
@RestController
@RequestMapping("/admin/fee")
public class FeeController {

    @Resource
    private FeeKeyService feeKeyService;

    @Resource
    private FeeValueService feeValueService;

    @Operation(summary = "保存或更新杂费名称")
    @PostMapping("key/saveOrUpdate")
    public Result saveOrUpdateFeeKey(@RequestBody FeeKey feeKey) {
        feeKeyService.saveOrUpdate(feeKey);
        return Result.ok();
    }

    @Operation(summary = "保存或更新杂费值")
    @PostMapping("value/saveOrUpdate")
    public Result saveOrUpdateFeeValue(@RequestBody FeeValue feeValue) {
        feeValueService.saveOrUpdate(feeValue);
        return Result.ok();
    }


    @Operation(summary = "查询全部杂费名称和杂费值列表")
    @GetMapping("list")
    public Result<List<FeeKeyVo>> feeInfoList() {
        List<FeeKeyVo> list = feeKeyService.feeInfoList();
        return Result.ok(list);
    }

    @Operation(summary = "根据id删除杂费名称")
    @DeleteMapping("key/deleteById")
    public Result removeFeeKeyById(@RequestParam String feeKeyId) {
        feeKeyService.removeFeeKeyById(feeKeyId);
        return Result.ok();
    }

    @Operation(summary = "根据id删除杂费值")
    @DeleteMapping("value/deleteById")
    public Result removeFeeValueById(@RequestParam String id) {
        feeValueService.removeById(id);
        return Result.ok();
    }
}
