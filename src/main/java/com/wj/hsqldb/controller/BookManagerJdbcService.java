package com.wj.hsqldb.controller;

import com.wj.hsqldb.datasource.JdbcDataSource;
import com.wj.hsqldb.model.Book;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


/**
 * Created by wenjing.liu on 2021/2/25 in J1.
 * <p>
 * 配合Servlet来完成与jsp的页面展示
 * 该类通过{@link #jdbcTemplate}代替之前的{@link com.wj.hsqldb.db.DbOperation}
 */
@Configuration
@PropertySource("classpath:/config/jdbc.properties")
public class BookManagerJdbcService {
    @Value("${jdbc.table}")
    private String jdbcTable = "book";
    @Resource(type = JdbcDataSource.class)
    private JdbcDataSource jdbcDataSource;
    private JdbcTemplate jdbcTemplate;

    @PostConstruct
    public void createBookTable() {
        jdbcTemplate = jdbcDataSource.createJdbcTemplate();
        System.out.println("BookManagerJdbcService 初始化 自动创建表!!!");
        String sql = String.format("CREATE TABLE IF NOT EXISTS %s " +
                "(id INTEGER PRIMARY KEY , name VARCHAR(50) NOT NULL, price DOUBLE, online DATE)", jdbcTable);
        jdbcTemplate.execute(sql);
        System.out.println(String.format("BookManagerJdbcService 创建 %s 表", jdbcTable));
    }

    public int addBook(Book book) {
        List<Book> books = getBook();
        //在总行数的基础上在加1，得到新数据的id
        int id = books.size() + 1;
        // 插入新的数据
        String insert = String.format("INSERT INTO %s \n" +
                "(id, name, price, online)\n" +
                "VALUES (%d , '%s', %f,'%s' )", jdbcTable, id, book.name, book.price, book.online);
        int result = jdbcTemplate.update(insert);

        System.out.println(String.format("%s已经成功加入数据库,目前数据库总共有%d条数据 ", book.name, result));
        return result;
    }

    public List<Book> getBook() {
        String sql = String.format("SELECT * FROM %s ", jdbcTable);
        List<Book> books = jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Book.class));
        if (books == null) {
            return books;
        }
        return books;
    }

}
