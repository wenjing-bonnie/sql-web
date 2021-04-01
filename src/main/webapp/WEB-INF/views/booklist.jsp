<%@ page import="com.wj.mysql.model.Book" %>
<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: wenjing.liu
  Date: 2021/3/31
  Time: 14:18
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<P>目前数据库中的所有的书的信息如下：</P>
<%
    List<Book> bookList = (List<Book>) request.getAttribute("book");
    for (int i = 0; i < bookList.size(); i++) {
        Book book = bookList.get(i);
%>
        <label><%= book.toString() %>
        </label>
        <br>
<%
    }
%>

</body>
</html>
