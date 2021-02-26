package com.wj.hsqldb.controller;

import com.wj.hsqldb.model.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by wenjing.liu on 2021/2/25 in J1.
 */

@Component
@WebServlet(name = "BookListServlet", value = "/booklist")
public class BookListController extends HttpServlet {
    @Autowired
    public BookManagerService bookManagerService;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Book> books = new ArrayList<>();// bookManagerService.getBook();
        Book book = new Book();
        book.id = 1;
        book.name = "das";
        book.price = 23.0f;
        book.online = "2010-01-01";
        books.add(book);
        books.add(book);
        books.add(book);
        books.add(book);
        req.setAttribute("book", books);
        req.setAttribute("item","134555");
        req.setAttribute("single",book);

        req.getRequestDispatcher("/book/booklist.jsp?add=3").forward(req, resp);
    }
}
