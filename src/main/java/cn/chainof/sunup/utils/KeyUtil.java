package cn.chainof.sunup.utils;

import java.util.Random;

/**
 */
public class KeyUtil {

    /**
     * 生成唯一的主键
     * 格式: 时间+随机数
     * @return
     */
    public static synchronized Long genUniqueKey() {
        Random random = new Random();
        Integer number = random.nextInt(900000) + 100000;
        String s = System.currentTimeMillis() + String.valueOf(number);

        return Long.valueOf(s);
    }
}
