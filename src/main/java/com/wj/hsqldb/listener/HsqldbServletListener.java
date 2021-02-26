package com.wj.hsqldb.listener;

import com.wj.hsqldb.controller.BookManagerService;
import com.wj.hsqldb.db.DbOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 * Created by wenjing.liu on 2021/2/26 in J1.
 * 用来在Servlet容器初始化的时候打开数据库,好像用法错误。再想想怎么处理吧！！！！
 */
@Configuration
@ComponentScan
public class HsqldbServletListener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        System.out.println("HsqldbServletListener context Initialized!!! ");
       // bookManagerService.createBookTable();
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        System.out.println("HsqldbServletListener context Destroyed!!! ");
       // bookManagerService.closeBookTable();
    }
}
