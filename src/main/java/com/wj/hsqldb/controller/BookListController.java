package com.wj.hsqldb.controller;

import com.wj.hsqldb.controller.base.BaseHttpServlet;
import com.wj.hsqldb.model.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * Created by wenjing.liu on 2021/2/25 in J1.
 */


@WebServlet(name = "BookListServlet", value = "/booklist")
public class BookListController extends BaseHttpServlet {
    @Autowired
    public BookManagerService bookManagerService;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Book> books = bookManagerService.getBook();
        System.out.println("book size = " + books.size());
        req.setAttribute("book", books);
        req.getRequestDispatcher("/book/booklist.jsp").forward(req, resp);
    }
}
