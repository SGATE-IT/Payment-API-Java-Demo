package com.gccpay;

/**
 * Demo 配置常量
 * <p>
 * 在接入前，请将以下配置替换为您的实际商户信息：
 * - MERCHANT_ID        : 商户号，由平台分配
 * - MERCHANT_KEY_ID    : 密钥ID，由平台分配
 * - MERCHANT_PRIVATE_KEY : 商户RSA私钥（PKCS8格式），用于对请求报文签名
 * - PLATFORM_PUBLIC_KEY  : 平台RSA公钥（X509格式），需在管理平台配置，用于验证响应签名
 * - AES_KEY            : AES密钥（32位十六进制字符串），由平台提供，用于报文体的加解密
 * </p>
 */
public class DemoConfig {

    /** 商户号 */
    public static final String MERCHANT_ID = "M96620240924161327000862";

    /** 密钥ID */
    public static final String MERCHANT_KEY_ID = "12345678901";

    /**
     * 商户RSA私钥（PKCS8格式）
     * 用途：对每次请求报文进行 SHA256withRSA 签名，放入请求头 signature
     */
    public static final String MERCHANT_PRIVATE_KEY =
            "MIIEvQIBADANBgkqhkiG9w0BAQEFAASCBKcwggSjAgEAAoIBAQC55Y3+nuJilZMr" +
            "pN5l9fk4neHn3BmDOhPbWtOP/OLdDBni7b0EF5Pwp12ggKXbjNyXvX46aEExnGmL" +
            "tPySHNxomH4wQqA57R0oW5fx2iY8XO8XD+2yEqoHmTdONQ2llF0nUlLmpGh/b6Ra" +
            "fKKBH0XrIV76ACLlFL6CuyQGJ1qVSN1Y4NbUPURGBcfGO8Hz+3shOLLGlppTSqPL" +
            "P0K6+x6eSWXDCbCmKZybJoMmhYzi0fdeAf3kgmROxwVCpL6SynxTN7zyujZdcDE5" +
            "+HFupiVfCPFDp68DzdYGIfMPz1YMLIh9SNOPTQqVaXmtjNx+PaSYqCiJzmxvBjPb" +
            "wezw60gfAgMBAAECggEADn7Cz37a6JSaZCw35Aifa6jyWFHGP4BEBulFikdN9uFR" +
            "rdGbcnoJx0yvNUVMXLRwIvjb1d9GB7Tj85VwnrTXaH4Z7aNt7cMto3VfTk5HVJLRv" +
            "24Dh8yCxSyCSBV4Jr6AQQFFuF9+snXyTjsq5LMBxBYKQafIqmCV6YjnUMxSxavfaL" +
            "cTZDg/txoTYEKB07niS8dA9sUDVWk23A1E1E5YioOzSeEYWfEEE17xe6su+V5KmhB" +
            "ITqHtEuSRkccxWMvptbKObXQKIV2fx1brobQt5yzJviVUqiu0moi3haOs3VBgXq32w" +
            "BO9bq72dpZTbGQ9n5qOBvfbuSfewzLbHm6VkQKBgQDcqmj8JYwJ5RNx2Ar5gO1T7m" +
            "lG7QEJUY4M5InotJ0zXuYU3eTyKMo0BFUlt77ZJ6lR2PyCW554nV6Zg6awNbHsKHO" +
            "vNBJ4jlY+KnOYFchLQ4HeOSsnA9OO5XPl3oiIZsI5TItIoD4PclU3jsUBNhUwUeQx" +
            "spzM2n0mBkvXDez2CQKBgQDXqeKabLQ3jkYaI6OCHPcHGps+isO4OHvsEie3bMYtF" +
            "zLu6JdIJDGR6KxufBkthEAaWwG8ojJ0p3oNMA+LF8pRbSts4FirwREYZNZYsI3MZM" +
            "DUO+raFPq1v1ZpNfc3PSI1Bws9bGTXAbcsOxhuJaimGX1q6qglSRI9nzJMxHCW5wK" +
            "BgGRzxvsR9KAEgkeO+9/9CwzsOUyqU5B0aeAAoa8nmXBrQP46zSBX5USsvD5BWUXt" +
            "wiyaRMjrAEcUDJ6Byf3pU6eX+qHFaKss0KHYHWscb2OjxZjuGXDXUxV36ry4Axtk" +
            "/AGtkLJtEBNkDtsNySz1+8tVXDYrgynWRKZss1Wg50BRAoGACniXJgRNI71mrfI5C" +
            "CI75D5odzrpkdI8QhQHlaJUZPARawQkBD6toXX4mUyxNEKNkjoE9ZGyfXN8O5OvzY" +
            "MUMavpRdoGtCAloleTCK9Z0yi5LBTUrE4EdjqaCXWzUR1IweZbp1nR85aDvEQKRZ7" +
            "Sd24ZZs2J6HWJyzAlkxCentUCgYEAmAOHIQI6V8aNYT0xOjKOWIAqyI3pPruQ51K4" +
            "3yoo/XhrRLygfDj7ikQdFYd+gN1W3mlsT3EN2Qjz3XhczVOqE1RCn15v3WTydikzX" +
            "n8pEFmYf9kV2GS11eMTTKYv2UoLqN7j3a74fzfv67xsm5cJlbO6Dt3goBFZMerHdH" +
            "Sf7vY=";

    /**
     * 平台RSA公钥（X509格式）
     * 用途：验证平台响应报文的签名，确保数据来源可信
     */
    public static final String PLATFORM_PUBLIC_KEY =
            "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAueWN/p7iYpWTK6TeZfX5" +
            "OJ3h59wZgzoT21rTj/zi3QwZ4u29BBeT8KddoICl24zcl71+OmhBMZxpi7T8khzc" +
            "aJh+MEKgOe0dKFuX8domPFzvFw/tshKqB5k3TjUNpZRdJ1JS5qRof2+kWnyigR9F" +
            "6yFe+gAi5RS+grskBidalUjdWODW1D1ERgXHxjvB8/t7ITiyxpaaU0qjyz9Cuvse" +
            "nkllwwmwpimcmyaDJoWM4tH3XgH95IJkTscFQqS+ksp8Uze88ro2XXAxOfhxbqYlX" +
            "wjxQ6evA83WBiHzD89WDCyIfUjTj00KlWl5rYzcfj2kmKgoic5sbwYz28Hs8OtIH" +
            "wIDAQAB";

    /**
     * AES密钥（平台提供的32位十六进制字符串）
     * 用途：使用 AES/CBC/PKCS5Padding 对请求体进行加密，对响应体 data 字段进行解密
     */
    public static final String AES_KEY = "1af9206a4d0bd6becdbe188379a5f65d";

    /** API 基础地址（开发环境） */
    public static final String API_BASE_URL = "https://api-dev.gccpay.cn/pay/merchant";
}
