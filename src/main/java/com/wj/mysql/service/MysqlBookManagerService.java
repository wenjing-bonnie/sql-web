package com.wj.mysql.service;

import com.wj.hsqldb.datasource.JdbcDataSource;
import com.wj.mysql.model.FakeBook;
import org.apache.commons.dbcp.BasicDataSource;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.util.List;

/**
 * Created by wenjing.liu on 2021/3/19 in J1.
 * <p>
 * 对图书进行增删改查,该数据库使用的是MySQL
 */
@Component
public class MysqlBookManagerService {

    @Resource
    private BasicDataSource mysqlDataSource;

    private JdbcTemplate jdbcTemplate;

    @PostConstruct
    public void createDatabase() {
        jdbcTemplate = new JdbcTemplate(mysqlDataSource);
        String url = "create table if not exists book (\n" +
                " `id` int(10) not null  auto_increment, \n" +
                "`name` varchar(20) , \n" +
                "`price` float,\n" +
                " `online` date ,\n" +
                "primary key(`id`))auto_increment=1001 ;";
        jdbcTemplate.execute(url);
    }

    public void insertBook(FakeBook fakeBook) {
        String url = String.format("INSERT INTO book\n" +
                "     (name, price, online)\n" +
                "     values\n" +
                "     (\"%s\", %f, '%s')", fakeBook.name, fakeBook.price, fakeBook.online);
        jdbcTemplate.update(url);
    }

    public void query() {
        String url = "select *from book";
        List<FakeBook> books = jdbcTemplate.query(url, new BeanPropertyRowMapper<>(FakeBook.class));
        printBooks(books);
    }

    private void printBooks(List<FakeBook> books) {
        for (FakeBook book : books) {
            System.out.println(book.toString());
        }
    }


}
