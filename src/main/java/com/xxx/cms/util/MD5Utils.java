package com.xxx.cms.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Random;

/**
 * MD5工具类
 *
 * @author fangyufan
 */
public class MD5Utils {
    /**
     * 盐值的获取范围表
     */
    private static final char[] HEX = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};
    /**
     * 一个参数的长度
     */
    private static final int ONE_PARAM=1;
    /**
     * 两个参数的长度
     */
    private static final int TWO_PARAMS = 2;
    /**
     * 盐值长度
     */
    private static final int SALT_LENGTH = 16;
    /**
     * 加盐MD5哈希值长度
     */
    private static final int MD5_WITH_SALT_LENGTH = 48;
    /**
     * 加盐到MD5哈希值的循环中每次循环操作的字符数
     */
    private static final int LOOP_STEP = 3;


    /**
     * 获取加盐MD5哈希值
     *
     * @param strs 传递一个参数时表示输入的字符串；传递两个参数时第一个参数表示输入的字符串，第二个参数表示要进行校验的字符串
     * @return 加盐的MD5哈希值
     */
    public static String getMD5WithSalt(String... strs) {
        //创建用于实现加密算法的类的对象
        MessageDigest md;
        try {
            md = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }

        //创建盐的对象
        String salt = "";
        //如果传入了一个参数：表示获取的是新数据，盐值随机生成
        if (strs.length == ONE_PARAM) {
            salt = salt();
        }
        //如果传入了两个参数：表示要对传入的数据进行校验，盐值从相关记录获取
        else if (strs.length == TWO_PARAMS) {
            //获取从相关记录中查找到的哈希值
            String queriedHash = strs[1];
            salt = getSaltFromHash(queriedHash);
        }

        //获取输入字符串
        String inputStr = strs[0];
        //对输入字符串进行加盐
        String inputWithSalt = inputStr + salt;
        //哈希计算,转换输出得到MD5哈希值
        String hashResult = byte2HexStr(md.digest(inputWithSalt.getBytes()));
        //将盐值存储到hash值中，每两个hash字符中间插入一个盐字符，得到加盐MD5哈希值
        char[] chars = new char[MD5_WITH_SALT_LENGTH];
        for (int i = 0; i < MD5_WITH_SALT_LENGTH; i += LOOP_STEP) {
            chars[i] = hashResult.charAt(i / LOOP_STEP * 2);
            chars[i + 1] = salt.charAt(i / LOOP_STEP);
            chars[i + 2] = hashResult.charAt(i / LOOP_STEP * 2 + 1);
        }
        hashResult = new String(chars);
        return hashResult;
    }


    /**
     * 盐值的随机生成
     *
     * @return 返回盐值
     */
    public static String salt() {
        Random random = new Random();
        StringBuilder sb = new StringBuilder(16);
        for (int i = 0; i < sb.capacity(); i++) {
            sb.append(HEX[random.nextInt(16)]);
        }
        return sb.toString();
    }

    /**
     * 将字节数组转换成十六进制字符串
     *
     * @param bytes 字节数组
     * @return 转换后得到的十六进制字符串
     */
    private static String byte2HexStr(byte[] bytes) {
        StringBuilder result = new StringBuilder();
        for (byte b : bytes) {
            //将字节转化为十六进制字符
            result.append(String.format("%02X", b));
        }
        return result.toString();
    }


    /**
     * 从加盐的MD5哈希值中获取盐值
     *
     * @param hash 加盐的MD5哈希值
     * @return 获取到的盐值
     */
    public static String getSaltFromHash(String hash) {
        StringBuilder sb = new StringBuilder();
        char[] hashArr = hash.toCharArray();
        for (int i = 0; i < hash.length(); i += LOOP_STEP) {
            sb.append(hashArr[i + 1]);
        }
        return sb.toString();
    }
}
