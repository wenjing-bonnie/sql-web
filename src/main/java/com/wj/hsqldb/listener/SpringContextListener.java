package com.wj.hsqldb.listener;

import com.wj.hsqldb.service.BookManagerService;
import com.wj.hsqldb.db.JdbcConfiguration;
import com.wj.hsqldb.model.Book;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 * Created by wenjing.liu on 2021/2/26 in J1.
 * 用来在Servlet容器初始化的时候打开数据库,好像用法错误。再想想怎么处理吧！！！！
 */
@Deprecated
@Configuration
@ComponentScan("com") //扫描所在类的包以及子包，来创建@Component的类
public class SpringContextListener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        System.out.println("SpringContextListener context Initialized!!! ");
        // bookManagerService.createBookTable();
        //初始化所有的@Component的类
        new AnnotationConfigApplicationContext(SpringContextListener.class);
        //testDb();
    }


    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        System.out.println("SpringContextListener context Destroyed!!! ");
        // bookManagerService.closeBookTable();
    }

    /**
     * 用来测试读写数据库的正确性
     */
    public static void testDb() {
        ApplicationContext context = new AnnotationConfigApplicationContext(SpringContextListener.class);
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
