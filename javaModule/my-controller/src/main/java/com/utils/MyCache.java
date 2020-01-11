package com.utils;

import org.apache.ibatis.cache.Cache;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.concurrent.locks.ReadWriteLock;

/**
 * @author 陈宜康
 * @date 2019/9/12 21:24
 * @forWhat
 */
public class MyCache implements Cache {
    private final Cache delegate;
    private Map<Object, Object> keyMap;
    private Object eldestKey;

    public MyCache(Cache delegate) {
        this.delegate = delegate;
        this.setSize(1024);
    }

    public String getId() {
        System.out.println("利用了自己写的换存工具哦");
        return this.delegate.getId();
    }

    public int getSize() {
        return this.delegate.getSize();
    }

    public void setSize(final int size) {
        this.keyMap = new LinkedHashMap<Object, Object>(size, 0.75F, true) {
            private static final long serialVersionUID = 4267176411845948333L;

            protected boolean removeEldestEntry(Map.Entry<Object, Object> eldest) {
                boolean tooBig = this.size() > size;
                if (tooBig) {
                    eldestKey = eldest.getKey();
                }

                return tooBig;
            }
        };
    }

    public void putObject(Object key, Object value) {
        this.delegate.putObject(key, value);
        this.cycleKeyList(key);
    }

    public Object getObject(Object key) {
        this.keyMap.get(key);
        return this.delegate.getObject(key);
    }

    public Object removeObject(Object key) {
        return this.delegate.removeObject(key);
    }

    public void clear() {
        this.delegate.clear();
        this.keyMap.clear();
    }

    public ReadWriteLock getReadWriteLock() {
        return null;
    }

    private void cycleKeyList(Object key) {
        this.keyMap.put(key, key);
        if (this.eldestKey != null) {
            this.delegate.removeObject(this.eldestKey);
            this.eldestKey = null;
        }

    }
}
