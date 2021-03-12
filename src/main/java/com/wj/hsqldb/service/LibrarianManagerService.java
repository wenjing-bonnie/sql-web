package com.wj.hsqldb.service;

import com.wj.hsqldb.model.Librarian;
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
        List<Librarian> librarians = getSession().createSQLQuery(sql).addEntity(Librarian.class).list();
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
        //hibernate 中createQuery与createSQLQuery两者区别是：
        //前者用的hql语句进行查询，后者可以用sql语句查询
        //前者以hibernate生成的Bean为对象装入list返回，后者则是以对象数组进行存储
        //所以使用createSQLQuery有时候也想以hibernate生成的Bean为对象装入list返回，就不是很方便
        //突然发现createSQLQuery有这样一个方法可以直接转换对象
        //Query query = session.createSQLQuery(sql).addEntity(XXXXXXX.class);
        //java.lang.ClassCastException: [Ljava.lang.Object; cannot be cast to com.wj.hsqldb.model.Librarian
        System.out.println(" = createSQLQuery = ");
        Query query = getSession().createSQLQuery(sql).addEntity(Librarian.class);
        printLibrarian(query);
        System.out.println(" = createQuery = ");
        sql = String.format("from Librarian %s ", tableName);
        Query<Librarian> other = getSession().createQuery(sql, Librarian.class);
        printLibrarian(other);
        return query.list();
    }
    //取得持久化对象的方法： get() load()
    //持久化对象保存，更新和删除：save(),update(),saveOrUpdate(),delete()


    public void printLibrarian(Query<Librarian> other) {
        for (Librarian librarian : other.list()) {
            System.out.println(librarian);
        }
    }
}
