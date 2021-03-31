package com.wj.mysql.controller;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by wenjing.liu on 2021/3/25 in J1.
 * 使用Spring mvc
 */
@Controller
@RequestMapping("/mvc")
public class BookMvcController {
    @RequestMapping(value = "/getbook")
    public ModelAndView getBook(HttpServletRequest request,
                                HttpServletResponse response) {
        String name;
        return new ModelAndView("/librarian/addlibrarian");
    }
}
