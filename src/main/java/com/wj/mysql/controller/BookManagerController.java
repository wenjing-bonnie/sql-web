package com.wj.mysql.controller;

import com.wj.hsqldb.controller.base.BaseHttpServlet;
import com.wj.mysql.model.FakeBook;
import com.wj.mysql.service.MysqlBookManagerService;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by wenjing.liu on 2021/3/19 in J1.
 * 用来验证mysql的相关内容
 */
@WebServlet(urlPatterns = "/mysql")
public class BookManagerController extends BaseHttpServlet {
    @Autowired
    private MysqlBookManagerService service;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("=== 目前数据库里的数据 === ");
        service.query();
        for (int i = 2; i < 5; i++) {
            FakeBook book = new FakeBook();
            book.name = "HeadFirst " + i;
            book.price = 39.0f;
            book.online = "2020-01-0" + i;
            service.insertBook(book);
        }
        System.out.println("=== 插入数据之后，数据库里的数据 === ");
        service.query();
    }
}
