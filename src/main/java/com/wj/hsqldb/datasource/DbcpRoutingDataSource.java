package com.wj.hsqldb.datasource;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

/**
 * Created by wenjing.liu on 2021/3/5 in J1.
 * <p>
 * 多数据源，用来动态获取数据源
 */

public class DbcpRoutingDataSource extends AbstractRoutingDataSource {

    public static final String KEY_BOOK = "dbcpDataSource";
    public static final String KEY_CART = "dbcpDataSourceCart";
    //利用ThreadLocal解决线程安全问题
    private static final ThreadLocal<String> keys = new ThreadLocal<>();

    @Override
    protected Object determineCurrentLookupKey() {
        return getKey();
    }

    public void setKey(String key) {
        keys.set(key);
    }

    public String getKey() {
        return keys.get();
    }

    public void clearKey() {
        keys.remove();
    }
}
