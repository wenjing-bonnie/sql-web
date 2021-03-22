package com.wj.mysql.controller;

import com.wj.hsqldb.controller.base.BaseHttpServlet;
import com.wj.mysql.model.Book;
import com.wj.mysql.service.BookServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;
import java.util.List;

/**
 * Created by wenjing.liu on 2021/3/22 in J1.
 */
@WebServlet(urlPatterns = "/mybatis")
public class BookManagerMyBatisController extends BaseHttpServlet {
    @Autowired
    private BookServiceImpl service;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Book> books = service.select();
        System.out.println("查询的数据为：====");
        for (Book book : books) {
            System.out.println(book.toString());
        }
        Book book = new Book();
        book.setName("Java 基本语法");
        book.setPrice(49.0);
        book.setOnline(new Date());
        int result = service.insert(book);
        System.out.println(result);
        System.out.println(service.selectByPrimaryKey(1001));
    }
}
