package com.wj.hsqldb.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * Created by wenjing.liu on 2021/3/11 in J1.
 * 图书管理员的基本信息
 * hibernate对实体类的要求
 * （1）属性必须是private 且有public的get和set方法
 * （2）必须有唯一标识属性来表示实体类
 * （3）属性的类型建议用包装类型
 * hibernate实体类有三种状态：
 * （1）瞬时态：没有id,与session没有关系
 * （2）持久态：有id,与session有关系
 * (3)托管太：有id,和session无关
 * 所以代码需要改变,来读取被注解标注的实体类,否则hibernate只是认为实体是简单的java类
 */
@Entity
public class Librarian {
    //@GeneratedValue注解的strategy属性提供四种值：
    //–AUTO： 主键由程序控制，是默认选项，不设置即此项。
    //–IDENTITY：主键由数据库自动生成，即采用数据库ID自增长的方式，Oracle不支持这种方式。
    //–SEQUENCE：通过数据库的序列产生主键，通过@SequenceGenerator 注解指定序列名，mysql不支持这种方式。
    //–TABLE：通过特定的数据库表产生主键，使用该策略可以使应用更易于数据库移植。
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private int age;
    private String sex;

    public void setId(Long id) {
        this.id = id;
    }

    @Id
    public Long getId() {
        return id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getAge() {
        return age;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getSex() {
        return sex;
    }

    @Override
    public String toString() {
        return "Librarian{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", sex='" + sex + '\'' +
                '}';
    }

}
