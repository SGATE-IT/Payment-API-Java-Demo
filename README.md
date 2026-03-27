# Payment API Demo

API 文档：[https://docs.sgate.sa/zh/v2/payinApi/](https://docs.sgate.sa/zh/v2/payinApi/)

## 项目结构

```
src/main/java/com/gccpay/
├── DemoConfig.java              # 配置常量（商户号、密钥、AES Key、API地址）
├── ApiClient.java               # 公共请求客户端（签名、加密、发送、解密、验签）
├── entity/
│   ├── CreateOrderEntity.java   # 创建订单请求体
│   ├── CreateRefundEntity.java  # 创建退款请求体
│   ├── InqTranOrderEntity.java  # 查询交易请求体
│   └── InqRefundOrderEntity.java# 查询退款请求体
├── request/
│   ├── CreateOrderDemo.java     # 创建订单示例
│   ├── CreateRefundDemo.java    # 创建退款示例
│   ├── InqTranOrderDemo.java    # 查询交易示例
│   ├── InqRefundOrderDemo.java  # 查询退款示例
│   └── DownloadFileDemo.java    # 下载对账文件示例
└── utils/
    ├── AESUtils.java            # AES 加解密（AES/CBC/PKCS5Padding）
    └── RSAUtils.java            # RSA 签名/验签（SHA256withRSA）
```

## 快速开始

**1. 配置密钥**

修改 `DemoConfig.java`，填入您的商户信息：

| 配置项 | 说明 |
|---|---|
| `MERCHANT_ID` | 商户号，由平台分配 |
| `MERCHANT_KEY_ID` | 密钥ID，由平台分配 |
| `MERCHANT_PRIVATE_KEY` | 商户RSA私钥（PKCS8），用于对请求签名 |
| `PLATFORM_PUBLIC_KEY` | 平台RSA公钥（X509），需在管理平台配置，用于验证响应签名 |
| `AES_KEY` | AES密钥（32位十六进制），由平台提供 |

**2. 运行 Demo**

直接运行对应 Demo 类的 `main` 方法即可。

## 请求流程

每次 API 调用均经过以下安全处理（由 `ApiClient` 统一处理）：

```
请求体 JSON
  → 商户私钥 RSA 签名 → 写入请求头 signature
  → AES 加密请求体    → 以 {"aesBuffer":"..."} 格式发送

响应体 data 字段
  → AES 解密
  → 平台公钥验证响应头 signature
```

## 接口列表

| Demo 类 | 接口路径 | 说明 |
|---|---|---|
| `CreateOrderDemo` | `/pay/merchant/createOrder` | 创建支付订单 |
| `InqTranOrderDemo` | `/pay/merchant/inquiryTransactions` | 查询交易订单 |
| `CreateRefundDemo` | `/pay/merchant/refund` | 申请退款 |
| `InqRefundOrderDemo` | `/pay/merchant/inquiryRefund` | 查询退款结果 |
| `DownloadFileDemo` | `/pay/merchant/downloadFile` | 下载对账文件 |
