package com.wj.hsqldb.controller.service;

import com.wj.hsqldb.controller.service.BookManagerJdbcService;
import com.wj.hsqldb.db.DbOperation;
import com.wj.hsqldb.model.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


/**
 * Created by wenjing.liu on 2021/2/25 in J1.
 * <p>
 * 配合Servlet来完成与jsp的页面展示
 * 在后面的代码会替换掉该类，使用{@link BookManagerJdbcService }来代替
 */
@Deprecated
@Configuration
@PropertySource("classpath:/config/jdbc.properties")
public class BookManagerService {
    @Autowired
    public DbOperation dbOperation;
    @Value("${jdbc.table}")
    private String jdbcTable = "book";

    @PostConstruct
    public void createBookTable() {
        System.out.println("BookManagerService 初始化 自动创建表!!!");
        String sql = String.format("CREATE TABLE IF NOT EXISTS %s " +
                "(id INTEGER PRIMARY KEY , name VARCHAR(50) NOT NULL, price DOUBLE, online DATE)", jdbcTable);
        int result = dbOperation.createTable(sql);
        System.out.println(String.format("BookManagerService 创建 %s 表的结果为 %d", jdbcTable, result));
    }

    public int addBook(Book book) {
        //  因为暂时不知道让id自加1,所以用这种笨方法先记录下
        String sql = String.format("SELECT * FROM %s ", jdbcTable);
        int count = 0;
        try {
            ResultSet set = dbOperation.query(sql);
            while (set.next()) {
                count++;
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        //在总行数的基础上在加1，得到新数据的id
        int id = count + 1;
        // 插入新的数据
        String insert = String.format("INSERT INTO %s \n" +
                "(id, name, price, online)\n" +
                "VALUES (%d , '%s', %f,'%s' )", jdbcTable, id, book.name, book.price, book.online);
        int result = dbOperation.insert(insert);

        System.out.println(String.format("%s已经成功加入数据库,目前数据库总共有%d条数据 ", book.name, result));
        return result;
    }

    public List<Book> getBook() {
        List<Book> books = new ArrayList<>();
        String sql = String.format("SELECT * FROM %s ", jdbcTable);
        ResultSet set = dbOperation.query(sql);
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
                books.add(book);
            }
            return books;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return books;
    }

    @PreDestroy
    public void closeBookTable() {
        System.out.println("BookManagerService初始化自动关闭表!!!");
        dbOperation.closeTable();
    }
}
