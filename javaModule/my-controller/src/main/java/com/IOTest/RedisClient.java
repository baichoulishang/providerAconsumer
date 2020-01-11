package com.IOTest;

/**
 * @author 陈宜康
 * @date 2019/12/18 20:03
 * @forWhat
 */
public interface RedisClient {

    String get(String key);

    void set(String key, String value);

    void close();

}
