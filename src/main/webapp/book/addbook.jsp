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
    <title>添加图书</title>
</head>
<body>

<form action="${pageContext.request.contextPath}/addbook" method="get">
    <div>
        <label>请输入图书名：</label>
        <input type="text" name="name">
    </div>
    <div>
        <label>请输入图书价格：</label>
        <input type="number" step="0.01" name="price">
    </div>

    <div>
        <label>请输入图书上架时间：</label>
        <input type="date" name="online">
    </div>
    <div>
        <input type="submit" value="保存">
    </div>
</form>
</body>
</html>
