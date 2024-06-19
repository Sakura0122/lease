package com.sakura.lease.web.admin.controller.apartment;


import com.sakura.lease.common.result.Result;
import com.sakura.lease.model.entity.PaymentType;
import com.sakura.lease.web.admin.service.PaymentTypeService;
import com.sakura.lease.web.admin.service.impl.PaymentTypeServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Tag(name = "支付方式管理")
@RequestMapping("/admin/payment")
@RestController
public class PaymentTypeController {

    @Resource
    PaymentTypeService paymentTypeService;

    @Operation(summary = "查询全部支付方式列表")
    @GetMapping("list")
    @CrossOrigin
    public Result<List<PaymentType>> listPaymentType() {
        List<PaymentType> list = paymentTypeService.list();
        return Result.ok(list);
    }

    @Operation(summary = "保存或更新支付方式")
    @PostMapping("saveOrUpdate")
    public Result saveOrUpdatePaymentType(@RequestBody PaymentType paymentType) {
        paymentTypeService.saveOrUpdate(paymentType);
        return Result.ok();
    }

    @Operation(summary = "根据ID删除支付方式")
    @DeleteMapping("deleteById")
    public Result deletePaymentById(@RequestParam String id) {
        paymentTypeService.removeById(id);
        return Result.ok();
    }

}















