package com.wj.mysql.service;

import com.wj.mysql.model.Book;
import com.wj.mysql.model.BookMapper;
import org.mybatis.spring.support.SqlSessionDaoSupport;

import java.util.List;

/**
 * Created by wenjing.liu on 2021/3/24 in J1.
 */

public class BookDaoSupport extends SqlSessionDaoSupport implements BookMapper {

    @Override
    public int deleteByPrimaryKey(Integer id) {
        return 0;
    }

    @Override
    public int insert(Book record) {

        return getSqlSessionTemplate().insert("com.wj.mysql.model.BookMapper.insert", record);
    }

    @Override
    public int insertSelective(Book record) {
        return 0;
    }

    @Override
    public Book selectByPrimaryKey(Integer id) {
        return null;
    }

    @Override
    public int updateByPrimaryKeySelective(Book record) {
        return 0;
    }

    @Override
    public int updateByPrimaryKey(Book record) {
        return 0;
    }

    @Override
    public List<Book> select() {
        return getSqlSessionTemplate().selectList("com.wj.mysql.model.BookMapper.select");
    }
}
