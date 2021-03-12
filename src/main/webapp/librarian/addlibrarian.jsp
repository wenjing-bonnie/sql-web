<%--
  Created by IntelliJ IDEA.
  User: j1
  Date: 2021/2/25
  Time: 16:20
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>添加图书管理人员</title>
</head>
<body>

<form action="${pageContext.request.contextPath}/addlibrarian" method="get">
    <div>
        <label>请输入图书管理人员的姓名：</label>
        <input type="text" name="name">
    </div>
    <div>
        <label>请输入图书管理人员的年龄：</label>
        <input type="number" name="age">
    </div>

    <div>
        <label>请输入图书管理人员的性别：</label>
        <input type="text" name="sex">
    </div>
    <div>
        <input type="submit" value="保存">
    </div>
</form>
</body>
</html>
