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
<%@ page contentType="text/html;charset=UTF-8" language="java"  %>

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
<table width="50%" border="1px" align="center" style="margin-top: 50px;" title="${display}">

    <tr> <!--加粗-->
        <th>Book id:</th>
        <th>Book name:</th>
        <th>Book price:</th>
        <th>Book online:</th>
    </tr>

<%--    使用el的条件：如果用的servlet-api.jar包低于javax.servlet-api-3.0.1版本，web版本必须是3.0之前的。--%>
    <c:forEach items="${book}" var="it" >

        <tr style="color: red">
            <td>${it.id}</td>
            <td>${it.name}</td>
            <td>${it.price}</td>
            <td>${it.online}</td>
        </tr>
    </c:forEach>

</table>
</body>
</html>
