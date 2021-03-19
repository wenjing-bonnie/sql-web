package com.wj.mysql.service;

import org.apache.commons.dbcp.BasicDataSource;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * Created by wenjing.liu on 2021/3/19 in J1.
 * <p>
 * 对图书进行增删改查,该数据库使用的是MySQL
 */
@Component
public class BookManagerService {

    @Resource
    private BasicDataSource mysqlDataSource;


}
