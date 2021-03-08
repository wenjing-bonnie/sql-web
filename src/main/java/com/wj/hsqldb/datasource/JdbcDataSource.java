package com.wj.hsqldb.datasource;

import com.alibaba.druid.pool.DruidDataSource;
import com.mchange.v2.c3p0.ComboPooledDataSource;
import com.zaxxer.hikari.HikariDataSource;
import org.apache.commons.dbcp.BasicDataSource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;

/**
 * Created by wenjing.liu on 2021/2/25 in J1.
 * 该类通过配置文件进行实例化
 * 用该类可以直接代替{@link com.wj.hsqldb.db.DbOperation}和{@link com.wj.hsqldb.db.JdbcConfiguration}
 */
public class JdbcDataSource {

    private ComboPooledDataSource c3p0DataSource;
    private BasicDataSource dbcpDataSource;
    private DriverManagerDataSource driverManagerDataSource;
    private DruidDataSource druidDataSource;
    private DbcpRoutingDataSource dbcpRoutingDataSource;
    private HikariDataSource hikariDataSource;
    private JdbcTemplate jdbcTemplate;

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

    public void setHikariDataSource(HikariDataSource hikariDataSource) {
        this.hikariDataSource = hikariDataSource;
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


    public DataSource createHikariDataSource() {
        return hikariDataSource;
    }

    //JdbcTemplate是Spring对JDBC的封装，目的是使JDBC更加易于使用。JdbcTemplate是Spring的一部分。JdbcTemplate处理了资源的建立和释放
    public JdbcTemplate createJdbcTemplate() {
        if (jdbcTemplate == null) {
            jdbcTemplate = new JdbcTemplate(driverManagerDataSource);
        }
        return jdbcTemplate;
    }


}
