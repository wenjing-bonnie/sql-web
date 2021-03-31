package com.wj.mysql.utils;

import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

import javax.annotation.Resource;

/**
 * Created by wenjing.liu on 2021/3/31 in J1.
 * https://blog.csdn.net/weixin_44415997/article/details/100098081
 * 解决 <url-pattern>/mvc/*</url-pattern>拦截路径的时候，提示" org.springframework.web.servlet.PageNotFound - No mapping for GET /mvc/getBook"
 * 目前没有找到其他的解决方案
 */
public class SetAlwaysUseFullPathModule {
    @Resource
    public void setHandlerMapping(RequestMappingHandlerMapping mapping){
        mapping.setAlwaysUseFullPath(true);

    }
}
