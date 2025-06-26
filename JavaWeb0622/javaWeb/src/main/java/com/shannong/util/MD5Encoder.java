package com.shannong.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.nio.charset.StandardCharsets;

public class MD5Encoder {

    /**
     * 完整的MD5编码过程
     * @param input 原始字符串
     * @return 32位小写MD5字符串
     * @throws RuntimeException 当MD5算法不可用时
     */
    public static String encode(String input) {
        // 参数校验
        if (input == null) {
            throw new IllegalArgumentException("Input string cannot be null");
        }

        try {
            // 1. 获取MessageDigest实例
            MessageDigest md = MessageDigest.getInstance("MD5");

            // 2. 将字符串转换为字节数组（必须指定编码）
            byte[] inputBytes = input.getBytes(StandardCharsets.UTF_8);

            // 3. 计算哈希值（digest方法会执行完整计算）
            byte[] hashBytes = md.digest(inputBytes);

            // 4. 将字节数组转换为十六进制表示
            return bytesToHex(hashBytes);

        } catch (NoSuchAlgorithmException e) {
            // 理论上所有Java实现都必须支持MD5
            throw new RuntimeException("MD5 algorithm not available", e);
        }
    }

    /**
     * 字节数组转十六进制字符串（优化版）
     * @param bytes 字节数组
     * @return 十六进制字符串
     */
    private static String bytesToHex(byte[] bytes) {
        // 一个字节对应两个十六进制字符
        char[] hexChars = new char[bytes.length * 2];
        // 预定义十六进制字符
        final char[] hexArray = "0123456789abcdef".toCharArray();

        for (int i = 0; i < bytes.length; i++) {
            // 取字节的高4位
            int high = (bytes[i] & 0xF0) >>> 4;
            // 取字节的低4位
            int low = bytes[i] & 0x0F;
            // 转换为对应的十六进制字符
            hexChars[i * 2] = hexArray[high];
            hexChars[i * 2 + 1] = hexArray[low];
        }
        return new String(hexChars);
    }
}
