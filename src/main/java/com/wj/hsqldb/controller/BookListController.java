package com.wj.hsqldb.controller;

import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by wenjing.liu on 2021/2/25 in J1.
 *
 */
@WebServlet(name = "helloServlet", value = "/hello-servlet")
public class BookListController extends HttpServlet {
    @Autowired
    BookManagerService bookManagerService;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/book/booklist.jsp").forward(req,resp);
    }
}
