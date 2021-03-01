package com.wj.hsqldb.controller;

import org.springframework.stereotype.Component;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

/**
 * Created by wenjing.liu on 2021/3/1 in J1.
 * 所有的Bean是归Spring容器管理，但是Servlet有单独的容器，归tomcat管理
 * 所以在Servlet是无法通过@Component来获取实例的。所以才
 */
@Component
public class BaseHttpServlet extends HttpServlet {

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        ServletContext application = this.getServletContext();
        SpringBeanAutowiringSupport.processInjectionBasedOnServletContext(this,application);
        System.out.println("Servlet init config ........");
    }
}
