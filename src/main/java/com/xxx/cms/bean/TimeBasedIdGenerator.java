package com.xxx.cms.bean;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

public class TimeBasedIdGenerator {

    /**
     * 生成基于时间戳和随机数的唯一ID
     *
     * @return 生成的唯一ID
     */
    public static String generateId() {
        // 生成当前时间戳
        long timestamp = System.currentTimeMillis();

        // 生成随机数
        Random random = new Random();
        int randomNum = random.nextInt(1000); // 生成0到999之间的随机数

        // 格式化时间戳
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmssSSS");
        String formattedTimestamp = sdf.format(new Date(timestamp));

        // 将时间戳和随机数结合生成ID
        return formattedTimestamp + String.format("%03d", randomNum);
    }
}
