package com.wj.mysql.model;

import org.springframework.stereotype.Component;

/**
 * Created by wenjing.liu on 2021/2/25 in J1.
 */
@Component
public class FakeBook {
    public int id;
    public String name;
    public float price;
    public String online;

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setOnline(String online) {
        this.online = online;
    }

    public String getOnline() {
        return online;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public float getPrice() {
        return price;
    }

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
