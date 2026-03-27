package com.gccpay.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class InqTranOrderEntity {
    /** 商户号 */
    private String merchantId;
    /** 商户订单号（与 orderId 二选一） */
    private String merOrderId;
    /** 平台订单号（与 merOrderId 二选一） */
    private String orderId;
}
