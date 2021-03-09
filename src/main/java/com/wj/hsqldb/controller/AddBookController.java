package com.wj.hsqldb.controller;

import com.wj.hsqldb.controller.base.BaseHttpServlet;
import com.wj.hsqldb.model.Book;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by wenjing.liu on 2021/3/2 in J1.
 * 添加图书的jsp
 */

@WebServlet(urlPatterns = "/addbook")
public class AddBookController extends BaseHttpServlet {
    @Deprecated //小白新手web开发简单总结（十二）-数据库连接的相关优化（事务管理）中替换成
    @Autowired
    public BookManagerJdbcService bookManagerService;
    @Autowired
    public BookManagerPlatformTransactionService bookManagerPlatformTransactionService;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("name");
        String price = req.getParameter("price");
        String online = req.getParameter("online");
        if (name == null || name.isEmpty() || price == null || price.isEmpty() || online == null || online.isEmpty()) {
            System.out.println("保存失败");
            return;
        }
        Book book = new Book();
        book.name = name;
        book.price = Float.valueOf(price);
        book.online = online;
        bookManagerPlatformTransactionService.addBook(book);
        req.getRequestDispatcher("/booklist").forward(req, resp);
    }
}
