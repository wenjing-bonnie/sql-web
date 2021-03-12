<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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

<%--<c:forEach items="librarians" var="lib">--%>
<%--    <label>${lib.name}</label>--%>
<%--    <label>${lib.age}</label>--%>
<%--    <label>${lib.sex}</label>--%>
<%--</c:forEach>--%>

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
        <input type="submit" value="保存"> <!-- onclick="saveLibrarian()"-->
    </div>
</form>

<script type="text/javascript">
    function saveLibrarian() {
        var name = document.getElementById("name").value;
        var age = document.getElementById("age").value;
        var sex = document.getElementById("sex").value;
        $.ajax({
            type: "GET",
            url: "${pageContext.request.contextPath}/addlibrarian",
            data: "name=" + name + "&age=" + age + "&sex=" + sex,
            dataType: 'json',
            success: function (msg) {
                alert(msg);
            }, error: function () {

            }
        });
    }
</script>
</body>
</html>
