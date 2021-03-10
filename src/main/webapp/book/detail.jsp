<%@ page import="com.wj.hsqldb.model.Book" %><%--
  Created by IntelliJ IDEA.
  User: j1
  Date: 2021/3/10
  Time: 10:40
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<% Book book = (Book) request.getAttribute("book"); %>
<head>
    <title><%= book.name%>
    </title>
</head>
<body>

<label style="font-size: larger;color: rebeccapurple;">图书的名字为：<%= book.name%>
</label>
<br>
<label style="font-size: larger;color: rebeccapurple;">图书的价格为：<%= book.price%>
</label>
<br>
<label style="font-size: larger;color: rebeccapurple;">图书上线时间：<%= book.online%>
</label>
<br>

</body>
</html>
