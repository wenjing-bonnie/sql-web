package com.wj.hsqldb.controller;

import com.wj.hsqldb.controller.base.BaseHttpServlet;
import com.wj.hsqldb.model.Librarian;
import com.wj.hsqldb.service.LibrarianManagerService;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * Created by wenjing.liu on 2021/3/11 in J1.
 * 添加管理人员
 */
@WebServlet(urlPatterns = "/addlibrarian")
public class AddLibrarianController extends BaseHttpServlet {
    //@Autowired
    LibrarianManagerService librarianManagerService;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("name");
        String age = req.getParameter("age");
        String sex = req.getParameter("sex");
        if (name == null || name.isEmpty() || age == null || age.isEmpty() || sex == null || sex.isEmpty()) {
            System.out.println("保存失败");
            return;
        }
        Librarian librarian = new Librarian();
        librarian.setAge(Integer.parseInt(age));
        librarian.setName(name);
        librarian.setSex(sex);
        req.setAttribute("librarian", librarian);

        librarianManagerService.insertLibrarian(librarian);
//        List<Librarian> librarians = librarianManagerService.getLibrarian();
//        if (librarians != null) {
//            System.out.println("librarians = " + librarians.size());
//        }
        req.getRequestDispatcher("/librarian").forward(req, resp);

    }
}
