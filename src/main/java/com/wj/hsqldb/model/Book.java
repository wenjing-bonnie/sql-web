package com.wj.hsqldb.model;

/**
 * Created by wenjing.liu on 2021/2/25 in J1.
 */

public class Book {
    public long id;
    public String name;
    public float price;
    public String online;

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", online='" + online + '\'' +
                '}';
    }
}
