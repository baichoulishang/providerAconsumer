package com.IOTest;

/**
 * @author 陈宜康
 * @date 2019/12/18 20:03
 * @forWhat
 */
public interface RedisClient {

    public String get(String key);

    public void set(String key, String value);

    public void close();

}
