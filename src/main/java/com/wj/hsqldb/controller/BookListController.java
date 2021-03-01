package com.wj.hsqldb.controller;

import com.wj.hsqldb.model.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
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
public class BookListController extends HttpServlet {
    @Autowired
    public BookManagerService bookManagerService;

    public void setBookManagerService(BookManagerService service) {
        this.bookManagerService = service;
    }

    //    @Override
//    public void init(ServletConfig config) throws ServletException {
//        super.init(config);
//        SpringBeanAutowiringSupport.processInjectionBasedOnCurrentContext(this);
//        System.out.println("Servlet init config ........"+bookManagerService);
//    }
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        WebApplicationContext context = WebApplicationContextUtils.getWebApplicationContext(getServletConfig().getServletContext());
        bookManagerService = context.getBean(BookManagerService.class);
        List<Book> books = bookManagerService.getBook();
//        Book book = new Book();
//        book.id = 1;
//        book.name = "das";
//        book.price = 23.0f;
//        book.online = "2010-01-01";
//        books.add(book);
//        books.add(book);
//        books.add(book);
//        books.add(book);
        req.setAttribute("book", books);
        req.getRequestDispatcher("/book/booklist.jsp").forward(req, resp);
    }
}
