package com.gccpay;

import com.alibaba.fastjson.JSONObject;
import com.gccpay.utils.AESUtils;
import com.gccpay.utils.RSAUtils;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

/**
 * GCCPay API 公共请求客户端
 *
 * <p>封装了所有接口共用的安全处理流程：
 * <ol>
 *   <li>请求体 JSON 序列化</li>
 *   <li>使用商户私钥对请求体进行 RSA 签名，写入请求头 {@code signature}</li>
 *   <li>使用 AES 密钥对请求体加密，以 {@code {"aesBuffer":"..."}} 格式发送</li>
 *   <li>解析响应，当 {@code code == "10000"} 时使用 AES 密钥解密 {@code data} 字段</li>
 *   <li>使用平台公钥验证响应签名</li>
 * </ol>
 * </p>
 */
public class ApiClient {

    private static final RestTemplate restTemplate = new RestTemplate();
    private static final Gson gson = new Gson();

    /**
     * 发送加密并签名的 POST 请求
     *
     * @param url         完整的接口地址
     * @param requestBody 请求体对象，将自动序列化为 JSON
     * @return 解密后的响应数据字符串；若业务失败则返回 null
     */
    public static String post(String url, Object requestBody) {
        try {
            String bodyJson = JSONObject.toJSONString(requestBody);

            // 1. 构建请求头（含签名）
            HttpHeaders headers = buildHeaders(bodyJson);

            // 2. 加密请求体
            String encrypted = AESUtils.encrypt(bodyJson, DemoConfig.AES_KEY);
            Map<String, String> envelope = new HashMap<>();
            envelope.put("aesBuffer", encrypted);

            // 3. 发送请求
            HttpEntity<String> entity = new HttpEntity<>(JSONObject.toJSONString(envelope), headers);
            ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.POST, entity, String.class);

            System.out.println("[HTTP Status] " + response.getStatusCode().value());
            System.out.println("[Raw Response] " + response.getBody());

            // 4. 处理响应
            return handleResponse(response);

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 构建请求头
     * <ul>
     *   <li>{@code merchantKeyId} - 密钥ID</li>
     *   <li>{@code timeStamp}     - 当前 Unix 时间戳（秒）</li>
     *   <li>{@code signature}     - 对请求体 JSON 的 RSA 签名（Base64）</li>
     * </ul>
     */
    private static HttpHeaders buildHeaders(String bodyJson) throws Exception {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("merchantKeyId", DemoConfig.MERCHANT_KEY_ID);
        headers.set("timeStamp", String.valueOf(System.currentTimeMillis() / 1000));
        headers.set("signature", RSAUtils.sign(bodyJson, DemoConfig.MERCHANT_PRIVATE_KEY));
        return headers;
    }

    /**
     * 处理响应：解密 data 字段并验证平台签名
     *
     * @return 解密后的明文数据；业务失败时返回 null
     */
    private static String handleResponse(ResponseEntity<String> response) throws Exception {
        JsonObject json = gson.fromJson(response.getBody(), JsonObject.class);
        String code = json.get("code").getAsString();

        if (!"10000".equals(code)) {
            System.out.println("[Business Error] code=" + code + ", msg=" + json.get("msg"));
            return null;
        }

        // 解密响应 data
        String decrypted = AESUtils.decrypt(json.get("data").getAsString(), DemoConfig.AES_KEY);
        System.out.println("[Decrypted Data] " + decrypted);

        // 验证平台签名
        String respSignature = response.getHeaders().getFirst("signature");
        boolean valid = RSAUtils.verify(decrypted, respSignature, DemoConfig.PLATFORM_PUBLIC_KEY);
        System.out.println("[Signature Verify] " + (valid ? "SUCCESS" : "FAILED"));

        return decrypted;
    }
}
