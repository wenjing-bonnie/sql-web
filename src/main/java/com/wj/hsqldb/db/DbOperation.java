package com.wj.hsqldb.db;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Created by wenjing.liu on 2021/2/25 in J1.
 * 对数据库的进行增删改查的操作类
 */
@Component
public class DbOperation {
    @Autowired
    public JdbcConfiguration jdbcConfiguration;


    /**
     * 创建表
     *
     * @param sql
     * @return
     */
    public int createTable(String sql) {
        Statement statement = jdbcConfiguration.createStatement();
        try {
            int result = statement.executeUpdate(sql);
            System.out.println(String.format("创建表的结果为 %d", result));
            return result;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        System.out.println("创建表失败了！！！");
        return -1;

    }

    /**
     * 查询
     *
     * @param sql
     * @return
     */
    public ResultSet query(String sql) {
        Statement statement = jdbcConfiguration.createStatement();
        try {
            ResultSet set = statement.executeQuery(sql);
            return set;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }

    /**
     * 插入
     *
     * @param sql
     * @return
     */
    public int insert(String sql) {
        Statement statement = jdbcConfiguration.createStatement();
        try {
            int set = statement.executeUpdate(sql);
            return set;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return -1;
    }

    public void closeTable() {
        jdbcConfiguration.closeConnection();
    }

}
