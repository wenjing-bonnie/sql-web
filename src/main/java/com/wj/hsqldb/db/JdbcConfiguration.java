package com.wj.hsqldb.db;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import javax.annotation.PostConstruct;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Created by wenjing.liu on 2021/2/25 in J1.
 * 创建了HSQLDB数据库的配置类，用来读取jdbc.properties中的配置信息以及得到一个读写数据库的Statement
 */
@Deprecated
@Configuration
@PropertySource("classpath:/config/jdbc.properties")
public class JdbcConfiguration {
    @Value("${jdbc.url}")
    private String jdbcUrl; //= "jdbc:hsqldb:file:db/hsqldb/xbook";
    @Value("${jdbc.user}")
    private String jdbcUser;// = "SA";
    @Value("${jdbc.password}")
    private String jdbcPassword;// = "";
    @Value("${jdbc.table}")
    private String jdbcTable;// = "book";
    @Value("${jdbc.driverClass}")
    private String driverClass;

    public Connection connection;

    @PostConstruct
    public void init() {
        System.out.println("创建Jdbc 。。。。。。 ");
    }

    public Statement createStatement() {
        try {
            Class.forName(driverClass);
        } catch (ClassNotFoundException e) {
            System.out.println("jdbcDriver not found!!!");
            return null;
        }
        try {
            //JDBC不支持自增,创建或连接数据库
            connection = DriverManager.getConnection(jdbcUrl, jdbcUser, jdbcPassword);
            if (connection == null) {
                return null;
            }
            //创建里面的表
            Statement statement = connection.createStatement();
            return statement;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }

    public void closeConnection() {
        if (connection == null) {
            return;
        }
        try {
            connection.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

}
