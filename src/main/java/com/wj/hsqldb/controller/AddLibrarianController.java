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
    @Autowired
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
        //保存
        librarianManagerService.insertLibrarian(librarian);
        List<Librarian> librarians = librarianManagerService.getLibrarian();
        //查询
        if (librarians != null) {
            System.out.println(String.format("目前已有%d个管理员", librarians.size()));
            for (int i = 0; i < librarians.size(); i++) {
                System.out.println(librarians.get(i).toString());
            }
        }
        req.setAttribute("librarian", librarian);
        //调用forward() 的话,有关response对象的一切方法或者属性都会失去作用..只有request能被转向到下一个页面.
        //    调用include()的话,response跟request都能被传递到转向的下一个页面..
        //req.getRequestDispatcher("/librarian").forward(req, resp);
        //1..request.getRequestDispatcher(String arg0)---转向的特点:
        //2..response.sendRedirect(String arg0)---重定向的特点:
    }
}
