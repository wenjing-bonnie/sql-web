package com.wj.hsqldb.controller;

import com.wj.hsqldb.db.DbOperation;
import com.wj.hsqldb.model.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


/**
 * Created by wenjing.liu on 2021/2/25 in J1.
 * <p>
 * 配合Servlet来完成与jsp的页面展示
 */
@Component
public class BookManagerService {
    @Autowired
    DbOperation operation;

    @Value("#{jdbcConfiguration.jdbcTable}")
    private String jdbcTable;

    public void createBookTable() {
        String sql = String.format("CREATE TABLE IF NOT EXISTS %s " +
                "(id INTEGER PRIMARY KEY , name VARCHAR(50) NOT NULL, price DOUBLE, online DATE)", jdbcTable);
        int result = operation.createTable(sql);
        System.out.println(String.format("创建 %s 表的结果为 %d", jdbcTable, result));
    }

    public int addBook(Book book) {
        //  因为暂时不知道让id自加1,所以用这种笨方法先记录下
        String querySQL = String.format("SELECT *FROM %s", jdbcTable);
        int count = 0;
        try {
            ResultSet set = operation.query(querySQL);
            count = set == null ? 0 : set.getRow();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        //新数据的id
        int id = count <= 0 ? 1 : count;
        // 插入新的数据
        String sql = String.format("INSERT INTO %s \n" +
                "(id, name, price, online)\n" +
                "VALUES (%d , '%s', %f,'%s' )", jdbcTable, id, book.name, book.price, book.online);
        int result = operation.insert(sql);

        System.out.println(String.format("%s已经成功加入数据库,目前数据库总共有%d条数据 ", book.name, result));
        return result;
    }

    public List<Book> getBook() {
        List<Book> books = new ArrayList<>();
        String sql = String.format("SELECT * FROM %s ", jdbcTable);
        ResultSet set = operation.query(sql);
        if (set == null) {
            return books;
        }
        try {
            while (set.next()) {
                Book book = new Book();
                book.id = set.getInt(1);
                book.name = set.getString(2);
                book.price = set.getFloat(3);
                book.online = set.getDate(4).toString();
                System.out.println("查询的数据为：" + book.toString());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return books;
    }
}