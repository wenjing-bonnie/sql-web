package com.wj.hsqldb.controller;

import com.wj.hsqldb.controller.base.BaseHttpServlet;
import com.wj.hsqldb.service.BookManageTransactionTemplateService;
import com.wj.hsqldb.service.BookManagerJdbcService;
import com.wj.hsqldb.model.Book;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * Created by wenjing.liu on 2021/2/25 in J1.
 * 图书列表：使用的{@link BookManageTransactionTemplateService}来处理事务
 */

@WebServlet(name = "BookListServlet", value = "/booklist")
public class BookListController extends BaseHttpServlet {
    @Deprecated
    @Autowired
    private BookManagerJdbcService bookManagerService;
    @Autowired
    private BookManageTransactionTemplateService bookManageTransactionTemplateService;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Book> books = bookManageTransactionTemplateService.getBook();
        System.out.println("book size = " + books.size());
        req.setAttribute("book", books);
        req.getRequestDispatcher("/book/booklist.jsp").forward(req, resp);
    }
}
