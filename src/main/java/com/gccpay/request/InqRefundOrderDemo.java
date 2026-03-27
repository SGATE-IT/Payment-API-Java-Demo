package com.gccpay.request;

import com.gccpay.ApiClient;
import com.gccpay.DemoConfig;
import com.gccpay.entity.InqRefundOrderEntity;

/**
 * 查询退款订单 Demo
 *
 * <p>接口文档：https://docs.sgate.sa/zh/v2/payinApi/</p>
 * <p>接口地址：POST /pay/merchant/inquiryRefund</p>
 */
public class InqRefundOrderDemo {

    public static void main(String[] args) {
        InqRefundOrderEntity query = new InqRefundOrderEntity();
        query.setMerchantId(DemoConfig.MERCHANT_ID);
        query.setMerRefundOrderId("");                        // 商户退款单号（与 refundOrderId 二选一）
        query.setRefundOrderId("REF250306123725194030261");   // 平台退款单号（与 merRefundOrderId 二选一）

        ApiClient.post(DemoConfig.API_BASE_URL + "/inquiryRefund", query);
    }
}
