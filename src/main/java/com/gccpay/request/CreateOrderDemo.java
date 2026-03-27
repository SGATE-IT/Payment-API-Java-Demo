package com.gccpay.request;

import com.gccpay.ApiClient;
import com.gccpay.DemoConfig;
import com.gccpay.entity.CreateOrderEntity;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.UUID;

/**
 * 创建订单 Demo
 *
 * <p>接口文档：https://docs.sgate.sa/zh/v2/payinApi/</p>
 * <p>接口地址：POST /pay/merchant/createOrder</p>
 */
public class CreateOrderDemo {

    public static void main(String[] args) {
        CreateOrderEntity order = buildOrder();
        ApiClient.post(DemoConfig.API_BASE_URL + "/createOrder", order);
    }

    /**
     * 构造创建订单请求体
     */
    private static CreateOrderEntity buildOrder() {
        CreateOrderEntity order = new CreateOrderEntity();
        order.setMerchantId(DemoConfig.MERCHANT_ID);
        order.setMerOrderId(UUID.randomUUID().toString());   // 商户订单号（全局唯一）
        order.setSubMerId("");                           // 子商户号（无则留空）
        order.setOrderType("online_payin");
        order.setPaymentType("stcpay,cardpay,applepay");
        order.setOrderAmt(new BigDecimal("130"));
        order.setOrderCurrency("SAR");
        order.setExpireTime(30);                         // 订单有效时长（分钟）
        order.setFrontRedirectUrl("http://localhost:8080/pay/callback");
        order.setOrderDesc("Test order");

        // 商品信息（可多个）
        CreateOrderEntity.ProductInfo p1 = new CreateOrderEntity.ProductInfo();
        p1.setProductType("physical");
        p1.setProductName("hua wei car");
        p1.setProductSku("23232323");
        p1.setProductUnitPrice(new BigDecimal("100"));
        p1.setProductQuantity(1);
        p1.setProductCurrency("SAR");
        p1.setProductDesc("TEST");
        p1.setProductAvatarUrl("https://example.com/p1.jpg");

        CreateOrderEntity.ProductInfo p2 = new CreateOrderEntity.ProductInfo();
        p2.setProductType("virtual");
        p2.setProductName("game coin");
        p2.setProductSku("g12345");
        p2.setProductUnitPrice(new BigDecimal("30"));
        p2.setProductQuantity(2);
        p2.setProductCurrency("SAR");
        p2.setProductDesc("TEST virtual");
        p2.setProductAvatarUrl("https://example.com/p2.jpg");

        order.setProductInfo(Arrays.asList(p1, p2));

        // 客户信息
        order.getCustomerInfo().setCustomerUid("1234-5637-2334-1233");
        order.getCustomerInfo().setCustomerName("Mr heihei");
        order.getCustomerInfo().setCustomerPhone("5001999921");
        order.getCustomerInfo().setCustomerEmail("123456789@example.com");
        order.getCustomerInfo().setCustomerPlatform("wechat");

        // 收货地址
        order.getDeliveryInfo().setFirstName("lala");
        order.getDeliveryInfo().setLastName("Mr");
        order.getDeliveryInfo().setPhone("5001999921");
        order.getDeliveryInfo().setCountry("china");
        order.getDeliveryInfo().setCity("beijing");
        order.getDeliveryInfo().setAddress("China beijing chaoyang");
        order.getDeliveryInfo().setZipCode("100000");

        return order;
    }
}
