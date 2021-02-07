package com.km.common.utils.uuid;

import java.util.UUID;

/**
 * Description:
 *
 * @author GaoKunW
 * @date 2021/2/7 22:16
 */
public class IdUtils {

    /**
     * 随机生成 uuid
     *
     * @return
     */
    public static String randomUUID() {
        return UUID.randomUUID().toString();
    }

    /**
     * 随机生成没有 "_" 的uuid
     *
     * @return
     */
    public static String simpleUUID() {
        return randomUUID().replace("_", "");
    }

}
