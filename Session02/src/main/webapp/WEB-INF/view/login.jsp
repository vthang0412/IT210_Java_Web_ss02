<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>Đăng nhập</title>
    <style>
        body { font-family: Arial; background: #f0f2f5; }
        .container { width: 350px; margin: 100px auto; padding: 30px; background: white; border-radius: 8px; box-shadow: 0 0 10px rgba(0,0,0,0.2);}
        input { width: 100%; padding: 8px; margin: 5px 0; }
        button { width: 100%; padding: 10px; background: #007bff; color: white; border: none; cursor: pointer; border-radius: 4px;}
        button:hover { background: #0056b3; }
        .error { color: red; margin-bottom: 10px; }
    </style>
</head>
<body>
<div class="container">
    <h2>Đăng nhập</h2>
    <c:if test="${not empty loginError}">
        <div class="error"><c:out value="${loginError}"/></div>
    </c:if>
    <form method="post" action="<c:url value='${pageContext.request.contextPath}/login' />">
        <input type="text" name="username" placeholder="Tên đăng nhập"/>
        <input type="password" name="password" placeholder="Mật khẩu"/>
        <button type="submit">Đăng nhập</button>
    </form>
</div>
</body>
</html>