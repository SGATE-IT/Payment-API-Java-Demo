package com.gccpay.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class InqRefundOrderEntity {
    /** 商户号 */
    private String merchantId;
    /** 商户退款单号（与 refundOrderId 二选一） */
    private String merRefundOrderId;
    /** 平台退款单号（与 merRefundOrderId 二选一） */
    private String refundOrderId;
}
