package com.gccpay.request;

import com.alibaba.fastjson.JSONObject;
import com.gccpay.DemoConfig;
import com.gccpay.utils.AESUtils;
import com.gccpay.utils.RSAUtils;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 下载对账文件 Demo
 *
 * <p>接口文档：https://docs.sgate.sa/zh/v2/payinApi/</p>
 * <p>接口地址：POST /pay/merchant/downloadFile</p>
 * <p>
 * 注意：该接口返回文件流（byte[]），处理方式与普通 JSON 接口不同，
 * 文件名从响应头 Content-Disposition 中解析，文件内容直接保存到本地。
 * </p>
 */
public class DownloadFileDemo {

    // TODO：替换为实际的下载文件接口地址
    private static final String DOWNLOAD_URL = DemoConfig.API_BASE_URL + "/downloadFile";

    /** 文件保存目录（相对路径） */
    private static final String SAVE_DIR = "./download/";

    private static final RestTemplate restTemplate = new RestTemplate();

    public static void main(String[] args) {
        // 构造请求参数
        Map<String, Object> params = new HashMap<>();
        params.put("merchantId", DemoConfig.MERCHANT_ID);
        params.put("orderDate", "20250310");   // 交易日期，格式：yyyyMMdd
        params.put("fileType", "settle");    // 文件类型：settle=对账文件

        try {
            String bodyJson = JSONObject.toJSONString(params);

            // 构建请求头（含签名）
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            headers.set("merchantKeyId", DemoConfig.MERCHANT_KEY_ID);
            headers.set("timeStamp", String.valueOf(System.currentTimeMillis() / 1000));
            headers.set("signature", RSAUtils.sign(bodyJson, DemoConfig.MERCHANT_PRIVATE_KEY));

            // 加密请求体
            String encrypted = AESUtils.encrypt(bodyJson, DemoConfig.AES_KEY);
            Map<String, String> envelope = new HashMap<>();
            envelope.put("aesBuffer", encrypted);

            // 发送请求，响应为文件流
            HttpEntity<String> entity = new HttpEntity<>(JSONObject.toJSONString(envelope), headers);
            ResponseEntity<byte[]> response = restTemplate.exchange(DOWNLOAD_URL, HttpMethod.POST, entity, byte[].class);

            if (response.getStatusCode() == HttpStatus.OK) {
                String fileName = parseFileName(response.getHeaders());
                if (fileName == null) {
                    fileName = "download_" + System.currentTimeMillis() + ".xlsx";
                }
                saveFile(response.getBody(), SAVE_DIR + fileName);
                System.out.println("[Download] Saved to: " + SAVE_DIR + fileName);
            } else {
                System.out.println("[Download] Failed, status: " + response.getStatusCode());
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /** 从 Content-Disposition 响应头中解析文件名 */
    private static String parseFileName(HttpHeaders headers) {
        String contentDisposition = headers.getFirst(HttpHeaders.CONTENT_DISPOSITION);
        if (contentDisposition != null) {
            Matcher matcher = Pattern.compile("filename=([^;\"\\s]+)").matcher(contentDisposition);
            if (matcher.find()) {
                return matcher.group(1);
            }
        }
        return null;
    }

    /** 将文件字节流写入本地磁盘 */
    private static void saveFile(byte[] data, String filePath) throws IOException {
        java.io.File dir = new java.io.File(filePath).getParentFile();
        if (dir != null && !dir.exists()) {
            dir.mkdirs();
        }
        try (FileOutputStream fos = new FileOutputStream(filePath)) {
            fos.write(data);
        }
    }
}
