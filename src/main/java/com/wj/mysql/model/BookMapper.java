package com.wj.mysql.model;

import java.util.List;

public interface BookMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Book record);

    int insertSelective(Book record);

    Book selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Book record);

    int updateByPrimaryKey(Book record);

    /**
     * 需要查询所有的书，新增
     *
     * @return
     */
    List<Book> select();
}