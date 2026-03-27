package com.gccpay.request;

import com.gccpay.ApiClient;
import com.gccpay.DemoConfig;
import com.gccpay.entity.InqTranOrderEntity;

/**
 * 查询交易订单 Demo
 *
 * <p>接口文档：https://docs.sgate.sa/zh/v2/payinApi/</p>
 * <p>接口地址：POST /pay/merchant/inquiryTransactions</p>
 */
public class InqTranOrderDemo {

    public static void main(String[] args) {
        InqTranOrderEntity query = new InqTranOrderEntity();
        query.setMerchantId(DemoConfig.MERCHANT_ID);
        query.setMerOrderId("");                             // 商户订单号（与 orderId 二选一）
        query.setOrderId("ORD260327093843194491930");        // 平台订单号（与 merOrderId 二选一）

        ApiClient.post(DemoConfig.API_BASE_URL + "/inquiryTransactions", query);
    }
}
