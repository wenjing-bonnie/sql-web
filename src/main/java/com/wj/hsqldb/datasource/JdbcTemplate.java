package com.wj.hsqldb.datasource;

import com.alibaba.druid.pool.DruidDataSource;
import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.apache.commons.dbcp.BasicDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Created by wenjing.liu on 2021/2/25 in J1.
 * 创建了HSQLDB数据库的配置类，用来读取jdbc.properties中的配置信息以及得到一个读写数据库的Statement
 */
@Component
public class JdbcTemplate {

    private ComboPooledDataSource c3p0DataSource;
    private BasicDataSource dbcpDataSource;
    private DriverManagerDataSource driverManagerDataSource;
    private DruidDataSource druidDataSource;


    public void setC3p0DataSource(ComboPooledDataSource c3p0DataSource) {
        this.c3p0DataSource = c3p0DataSource;
    }

    public void setDbcpDataSource(BasicDataSource dbcpDataSource) {
        this.dbcpDataSource = dbcpDataSource;
    }

    public void setDriverManagerDataSource(DriverManagerDataSource driverManagerDataSource) {
        this.driverManagerDataSource = driverManagerDataSource;
    }

    public void setDruidDataSource(DruidDataSource druidDataSource) {
        this.druidDataSource = druidDataSource;
    }

    @PostConstruct
    public void init() {
        System.out.println("JdbcTemplate init  。。。。。。 ");

    }


    public DataSource createComboPoolDataSource() {
        return c3p0DataSource;
    }


    public DataSource createBasicDataSource() {
        return dbcpDataSource;
    }


    public DataSource createDriverManagerDataSource() {
        return driverManagerDataSource;
    }

    public DataSource createDruidDataSource() {
        return druidDataSource;
    }


}
