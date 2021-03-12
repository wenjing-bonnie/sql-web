package com.wj.hsqldb.service;

import com.wj.hsqldb.model.Librarian;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by wenjing.liu on 2021/3/11 in J1.
 * 图书管理人员的增删改查数据库
 */
@Component
public class LibrarianManagerService {
    private String tableName = "librarian";
    @Resource
    private SessionFactory sessionFactory;

    private Session getSession() {
        return sessionFactory.getCurrentSession();
    }

    /**
     * TODO id到底怎么进行自增？？？
     *
     * @return
     */
    public void insertLibrarian(Librarian librarian) {
        String sql = String.format("SELECT * FROM %s", tableName);
        List<Librarian> librarians = getSession().createSQLQuery(sql).list();
        if (librarians == null || librarians.isEmpty()) {
            librarian.setId(new Long(1));
        } else {
            librarian.setId(new Long(librarians.size() + 1));
        }
        getSession().save(librarian);
    }

    /**
     * 获取所有的管理员
     *
     * @return
     */
    public List<Librarian> getLibrarian() {
        String sql = String.format("SELECT * FROM %s", tableName);
        Query query = getSession().createSQLQuery(sql);
        return query.list();
    }

}
