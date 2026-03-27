package com.gccpay.request;

import com.gccpay.ApiClient;
import com.gccpay.DemoConfig;
import com.gccpay.entity.CreateRefundEntity;

import java.math.BigDecimal;
import java.util.UUID;

/**
 * 创建退款 Demo
 *
 * <p>接口文档：https://docs.sgate.sa/zh/v2/payinApi/</p>
 * <p>接口地址：POST /pay/merchant/refund</p>
 */
public class CreateRefundDemo {

    public static void main(String[] args) {
        CreateRefundEntity refund = buildRefund();
        ApiClient.post(DemoConfig.API_BASE_URL + "/refund", refund);
    }

    /**
     * 构造退款请求体
     */
    private static CreateRefundEntity buildRefund() {
        CreateRefundEntity refund = new CreateRefundEntity();
        refund.setMerchantId(DemoConfig.MERCHANT_ID);
        refund.setMerRefundOrderId(UUID.randomUUID().toString());   // 商户退款单号（全局唯一）
        refund.setOrigOrderId("ORD260327093133970761298");     // 原平台订单号
        refund.setOrigMerOrderId("");                           // 原商户订单号（与 origOrderId 二选一）
        refund.setRefundAmt(new BigDecimal("100"));
        refund.setRefundCurrency("SAR");
        refund.setRefundDesc("Test refund");
        return refund;
    }
}
