package com.wj.hsqldb.service;

import com.wj.hsqldb.model.Book;
import org.apache.commons.dbcp.BasicDataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;


/**
 * Created by wenjing.liu on 2021/2/25 in J1.
 * <p>
 * 配合Servlet来完成与jsp的页面展示
 * 通过声明式事务来管理事务，并且通过{@link #verifyTransaction(Book, String)}来验证已成功加入事务
 */
@Configuration
@PropertySource("classpath:/config/jdbc.properties")
public class BookManagerXmlTransactionService {
    @Value("${jdbc.table}")
    private String jdbcTable = "book";
    private String xmlTransTable = "hot";
    @Resource(type = BasicDataSource.class, name = "xmlBasicDataSource")
    private BasicDataSource jdbcDataSource;
    private JdbcTemplate jdbcTemplate;

    @PostConstruct
    //@Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.READ_COMMITTED)
    public void createBookTable() {
        jdbcTemplate = new JdbcTemplate(jdbcDataSource);
        System.out.println("BookManagerXmlTransactionService 初始化 自动创建表!!!");
        String sql = String.format("CREATE TABLE IF NOT EXISTS %s " +
                "(id INTEGER PRIMARY KEY , name VARCHAR(50) NOT NULL, price DOUBLE, online DATE)", jdbcTable);
        jdbcTemplate.execute(sql);
        String sqlHot = String.format("CREATE TABLE IF NOT EXISTS %s " +
                "(id INTEGER PRIMARY KEY , name VARCHAR(50) NOT NULL, price DOUBLE, online DATE)", xmlTransTable);
        jdbcTemplate.execute(sqlHot);
        System.out.println(String.format("BookManagerXmlTransactionService 创建 %s 表和 %s表", jdbcTable, xmlTransTable));
    }


    // @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.READ_COMMITTED)
    public Book getBook(String id) {
        return getBookSQL(jdbcTable, id);
    }

    // @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.READ_COMMITTED)
    public void verifyTransaction(Book book, String id) {
        //在插入之前先查询下每个表中的数据
        System.out.println("====异常数据之前====");
        System.out.println(getTableRowCount(jdbcTable, getBookSQL(jdbcTable).size()));
        System.out.println(getTableRowCount(xmlTransTable, getBookSQL(xmlTransTable).size()));
        //将这个数据加入到原xbook表中,这个时候该操作会抛出异常
        //将这个数据插入到hot表中，若增加了事务，则这个操作应该是操作失败的.
        addBookSQL(xmlTransTable, book);
        System.out.println("====异常数据报错之前插入hot表的数据====");
        System.out.println(getTableRowCount(xmlTransTable, getBookSQL(xmlTransTable).size()));
        System.out.println("====产生异常数据报错====");
        String insert = String.format("INSERT INTO %s \n" +
                "(id, name, price, online)\n" +
                "VALUES (%d , '%s', %f,'%s' )", jdbcTable, Integer.parseInt(id), book.name, book.price, book.online);
        jdbcTemplate.update(insert);
    }

    private String getTableRowCount(String table, int count) {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String date = df.format(new Date());
        return String.format("%s ||   目前 %s 表中一共有 %d 条数据", date, table, count);
    }

    private int addBookSQL(String table, Book book) {
        String sql = String.format("SELECT * FROM %s ", table);
        List<Book> books = jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Book.class));
        //在总行数的基础上在加1，得到新数据的id
        int id = books == null ? 1 : books.size() + 1;
        // 插入新的数据
        String insert = String.format("INSERT INTO %s \n" +
                "(id, name, price, online)\n" +
                "VALUES (%d , '%s', %f,'%s' )", table, id, book.name, book.price, book.online);
        int result = jdbcTemplate.update(insert);

        //System.out.println(String.format("%s已经成功加入数据库,目前数据库总共有%d条数据 ", book.name, result));
        return result;
    }

    // @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.READ_COMMITTED)
    private List<Book> getBookSQL(String table) {
        String sql = String.format("SELECT * FROM %s ", table);
        List<Book> books = jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Book.class));
        if (books == null) {
            return books;
        }
        return books;
    }

    // @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.READ_COMMITTED)
    private Book getBookSQL(String table, String id) {
        String sql = String.format("SELECT * FROM %s WHERE id=%s", table, id);
        Book book = jdbcTemplate.queryForObject(sql, new RowMapper<Book>() {
            @Override
            public Book mapRow(ResultSet resultSet, int i) throws SQLException {
                Book item = new Book();
                item.id = resultSet.getInt(1);
                item.name = resultSet.getString(2);
                item.price = resultSet.getFloat(3);
                item.online = resultSet.getDate(4).toString();
                return item;
            }
        });
        //在查询的过程中插入同样id的数据，如果启动了事务，则不会插入成功
        return book;
    }


}
