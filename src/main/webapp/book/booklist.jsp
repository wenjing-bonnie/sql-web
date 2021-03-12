<%@ page import="java.util.List" %>
<%@ page import="com.wj.hsqldb.model.Book" %><%--
  Created by IntelliJ IDEA.
  User: j1
  Date: 2021/2/25
  Time: 16:33
  To change this template use File | Settings | File Templates.
--%>
<%--需要导入C标签该包--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>图书列表</title>
</head>
<body>
<%

    List books = (List) request.getAttribute("book");
    boolean isEmpty = books.isEmpty();
    String display = isEmpty ? "none" : "block"; //display: ${display}

%>
<label>contextPath:${pageContext.request.contextPath}</label>

<table width="50%" border="1px" style="margin-top: 50px;border-collapse: collapse;" title="${display}">

    <tr> <!--加粗-->
        <th>Book id</th>
        <th>Book name</th>
        <th>Book price</th>
        <th>Book online</th>
        <th></th>
    </tr>

    <c:forEach items="${book}" var="it">

        <tr style="color: red;alignment: center" onclick="window.location.href='/detail?id=${it.id}'">
            <td>${it.id}</td>
            <td>${it.name}</td>
            <td>${it.price}</td>
            <td>${it.online}</td>
            <td>查看</td>
        </tr>
    </c:forEach>

</table>


<input type="button" value="添加图书"
       style="color: aqua; width: 10%;height: 5%;font-size: larger;margin-top: 50px;background: yellow;"
       onclick="window.location.href='/book/addbook.jsp'">

<input type="button" value="添加图书管理人员" onclick="window.location.href='../librarian/addlibrarian.jsp'">
</body>
</html>
