package com.wj.hsqldb;

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
@Configuration
@ComponentScan //扫描所在类的包以及子包，来创建@Component的类
public class SpringContextListener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        System.out.println("SpringContextListener context Initialized!!! ");
       // bookManagerService.createBookTable();
       new AnnotationConfigApplicationContext(SpringContextListener.class);
    }


    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        System.out.println("SpringContextListener context Destroyed!!! ");
       // bookManagerService.closeBookTable();
    }
}
