package com.wj.mysql.controller;

import com.wj.mysql.model.Book;
import com.wj.mysql.model.FakeBook;
import com.wj.mysql.service.BookServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.List;

/**
 * Created by wenjing.liu on 2021/3/25 in J1.
 * 使用Spring mvc
 */
@Controller
@RequestMapping("/mvc")
public class BookMvcController {
    @Autowired
    private BookServiceImpl service;
    @RequestMapping(value = "/getbook")
    public ModelAndView getBook(HttpServletRequest request,
                                HttpServletResponse response) {
        Book book = new Book();
        book.setName("Spring MVC");
        book.setPrice(49.0);
        book.setOnline(new Date());
        int result = service.insert(book);
        System.out.println("=====  插入的数据，返回为：" + result);
        List<Book> books = service.select();
        System.out.println("====   查询的数据为：");
        for (Book bo : books) {
            System.out.println(bo.toString());
        }
        ModelAndView modelAndView = new ModelAndView("/booklist");
        modelAndView.addObject("book",books);

        //增加业务处理之后，将返回的数据传递给ModelAndView
        return  modelAndView;
    }
}
