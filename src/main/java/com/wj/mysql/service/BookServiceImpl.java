package com.wj.mysql.service;

import com.wj.mysql.model.Book;
import com.wj.mysql.model.BookMapper;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by wenjing.liu on 2021/3/22 in J1.
 * 处理Book的实现类
 */
@Component
public class BookServiceImpl implements BookMapper {
    //方法一
    //@Resource
    private SqlSessionTemplate sqlSessionTemplate;
    //方法二
    @Resource
    private BookDaoSupport bookDaoSupport;
    // @Autowired
    private BookMapper bookService;

    @Override
    public int deleteByPrimaryKey(Integer id) {
        return 0;
    }

    @Override
    public int insert(Book record) {
        return insertByDaoSupport(record);
        //return insertByTemplate(record);
    }

    private int insertByTemplate(Book recode) {
        return sqlSessionTemplate.insert("com.wj.mysql.model.BookMapper.insert", recode);
    }

    private int insertByDaoSupport(Book recode) {
        return bookDaoSupport.insert(recode);
    }

    private int insertByMapper(Book recode) {
        return bookService.insert(recode);
    }

    @Override
    public List<Book> select() {
        return selectByDaoSupport();
        //return selectByTemplate();
        //return selectByMapper();
    }

    private List<Book> selectByTemplate() {
        List<Book> books = sqlSessionTemplate.selectList("com.wj.mysql.model.BookMapper.select");
        return books;
    }

    private List<Book> selectByMapper() {
        return bookService.select();
    }

    private List<Book> selectByDaoSupport() {
        return bookDaoSupport.select();
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
