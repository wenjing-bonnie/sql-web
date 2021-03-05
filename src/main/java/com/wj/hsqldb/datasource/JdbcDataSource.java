package com.wj.hsqldb.datasource;

import com.alibaba.druid.pool.DruidDataSource;
import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.apache.commons.dbcp.BasicDataSource;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;

/**
 * Created by wenjing.liu on 2021/2/25 in J1.
 * 用该类可以直接代替{@link com.wj.hsqldb.db.DbOperation}和{@link JdbcDataSource}
 */
@Component
public class JdbcDataSource {

    private ComboPooledDataSource c3p0DataSource;
    private BasicDataSource dbcpDataSource;
    private DriverManagerDataSource driverManagerDataSource;
    private DruidDataSource druidDataSource;
    private DbcpRoutingDataSource dbcpRoutingDataSource;

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

    public void setDbcpRoutingDataSource(DbcpRoutingDataSource dbcpRoutingDataSource) {
        this.dbcpRoutingDataSource = dbcpRoutingDataSource;
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

    //TODO 还没有验证
    public DataSource createDbcpRoutingDataSource() {
        return dbcpRoutingDataSource;
    }


}
