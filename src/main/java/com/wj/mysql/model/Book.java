package com.wj.mysql.model;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * book
 * @author 
 */
@Data
public class Book implements Serializable {
    private Integer id;

    private String name;

    private Double price;

    private Date online;

    private static final long serialVersionUID = 1L;
}