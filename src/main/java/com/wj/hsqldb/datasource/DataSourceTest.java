package com.wj.hsqldb.datasource;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import com.wj.hsqldb.db.JdbcConfiguration;
import org.apache.commons.dbcp.BasicDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by wenjing.liu on 2021/3/4 in J1.
 */

public class DataSourceTest {
    @Autowired
    private static JdbcConfiguration configuration;

    public static void main(String[] args) {
        testXml();
    }


    private static void testXml() {
        ApplicationContext context = new ClassPathXmlApplicationContext("classpath:config/application-context-db.xml");
        //ComboPooledDataSource dataSource = (ComboPooledDataSource) context.getBean("c3p0DataSource");
       // System.out.println("jdbc url = " + dataSource.getJdbcUrl());
        BasicDataSource dataSource = (BasicDataSource) context.getBean("dbcpDataSource");
        System.out.println("jdbc url = " + dataSource.getUrl());
    }

}
