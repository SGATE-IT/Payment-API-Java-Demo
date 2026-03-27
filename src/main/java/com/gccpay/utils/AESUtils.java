package com.gccpay.utils;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.util.Base64;

/**
 * AES 加解密工具类
 *
 * <p>算法：AES/CBC/PKCS5Padding，使用固定 IV（16字节全零）</p>
 */
public class AESUtils {

    /** CBC 模式初始向量（16字节全零） */
    private static final String AES_IV = "0000000000000000";

    /**
     * AES 加密
     *
     * @param content 明文字符串（UTF-8）
     * @param key     AES 密钥（32位十六进制字符串，即16字节）
     * @return Base64 编码的密文
     */
    public static String encrypt(String content, String key) {
        try {
            SecretKeySpec secretKeySpec = new SecretKeySpec(key.getBytes(), "AES");
            IvParameterSpec ivParameterSpec = new IvParameterSpec(AES_IV.getBytes());

            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
            cipher.init(Cipher.ENCRYPT_MODE, secretKeySpec, ivParameterSpec);

            byte[] encryptedBytes = cipher.doFinal(content.getBytes("UTF-8"));
            return Base64.getEncoder().encodeToString(encryptedBytes);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Encryption failed");
        }
    }

    /**
     * AES 解密
     *
     * @param encryptedData Base64 编码的密文
     * @param key           AES 密钥（32位十六进制字符串，即16字节）
     * @return 解密后的明文字符串（UTF-8）
     */
    public static String decrypt(String encryptedData, String key) {
        try {
            byte[] encryptedBytes = Base64.getDecoder().decode(encryptedData);
            SecretKeySpec secretKeySpec = new SecretKeySpec(key.getBytes(), "AES");
            IvParameterSpec ivParameterSpec = new IvParameterSpec(AES_IV.getBytes());

            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
            cipher.init(Cipher.DECRYPT_MODE, secretKeySpec, ivParameterSpec);

            byte[] decryptedBytes = cipher.doFinal(encryptedBytes);
            return new String(decryptedBytes, "UTF-8");
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Decryption failed");
        }
    }
}
