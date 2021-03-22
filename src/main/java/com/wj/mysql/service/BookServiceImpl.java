package com.wj.mysql.service;

import com.wj.mysql.model.Book;
import com.wj.mysql.model.BookMapper;
import org.mybatis.spring.mapper.MapperFactoryBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.util.List;

/**
 * Created by wenjing.liu on 2021/3/22 in J1.
 * 处理Book的实现类
 */
//@Component
public class BookServiceImpl implements BookMapper {
    @Resource
    private MapperFactoryBean factoryBean;
    private BookMapper bookService;

    @PostConstruct
    private void createMapper() {
        // bookService = sessionFactory.
        bookService = factoryBean.getSqlSession().getMapper(BookMapper.class);
    }

    @Override
    public int deleteByPrimaryKey(Integer id) {
        return 0;
    }

    @Override
    public int insert(Book record) {
        return bookService.insert(record);
    }

    @Override
    public List<Book> select() {
        return bookService.select();
    }

    @Override
    public int insertSelective(Book record) {
        return 0;
    }

    @Override
    public Book selectByPrimaryKey(Integer id) {
        return bookService.selectByPrimaryKey(id);
    }

    @Override
    public int updateByPrimaryKeySelective(Book record) {
        return 0;
    }

    @Override
    public int updateByPrimaryKey(Book record) {
        return 0;
    }

}
