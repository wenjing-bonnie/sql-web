package com.wj.hsqldb;

import com.wj.hsqldb.controller.BookManagerService;
import com.wj.hsqldb.db.JdbcConfiguration;
import com.wj.hsqldb.model.Book;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * Created by wenjing.liu on 2021/2/25 in J1.
 */
@Configuration
@ComponentScan
public class BookManagerDbTest {

    public static void main(String[] args) {
        testDb();
    }


    /**
     * 用来测试读写数据库的正确性
     */
    private static void testDb() {
        ApplicationContext context = new AnnotationConfigApplicationContext(BookManagerDbTest.class);
        JdbcConfiguration configuration = context.getBean(JdbcConfiguration.class);
        configuration.createStatement();
        BookManagerService service = context.getBean(BookManagerService.class);
        service.createBookTable();
        Book book = new Book();
        book.name = "headfirst";
        book.price = 29.9f;
        book.online = "2020-1-2";
        service.addBook(book);
        service.getBook();
    }
}
