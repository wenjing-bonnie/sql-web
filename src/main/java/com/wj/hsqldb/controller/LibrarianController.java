package com.wj.hsqldb.controller;

import com.wj.hsqldb.controller.base.BaseHttpServlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by wenjing.liu on 2021/3/11 in J1.
 * 管理人员列表
 */
@WebServlet(urlPatterns = "/librarian")
public class LibrarianController extends BaseHttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }
}
