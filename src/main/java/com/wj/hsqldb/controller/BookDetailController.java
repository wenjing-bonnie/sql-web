package com.wj.hsqldb.controller;

import com.wj.hsqldb.controller.base.BaseHttpServlet;
import com.wj.hsqldb.controller.service.BookManagerXmlTransactionService;
import com.wj.hsqldb.model.Book;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by wenjing.liu on 2021/3/10 in J1.
 * 查看图书的详情，用来验证声明式事务的使用方式
 */
@WebServlet(urlPatterns = "/detail")
public class BookDetailController extends BaseHttpServlet {
    @Autowired
    BookManagerXmlTransactionService transactionService;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // super.doGet(req, resp);
        String id = req.getParameter("id");
        Book book = transactionService.getBook(id);
        req.setAttribute("book", book);
        req.getRequestDispatcher("/book/detail.jsp").forward(req, resp);
    }
}
