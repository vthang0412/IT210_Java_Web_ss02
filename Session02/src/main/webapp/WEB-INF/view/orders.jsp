<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<html>
<head>
    <title>Đơn hàng</title>
    <style>
        body { font-family: Arial; background: #f0f2f5; padding: 20px; }
        table { border-collapse: collapse; width: 100%; background: white; }
        th, td { border: 1px solid #ddd; padding: 8px; text-align: center; }
        th { background: #007bff; color: white; }
        tr:nth-child(even){ background-color: #f2f2f2; }
        .header { margin-bottom: 20px; }
        .logout { margin-top: 20px; }
    </style>
</head>
<body>

<div class="header">
    <h2>Xin chào, <c:out value="${username}"/>! Vai trò: <c:out value="${role}"/></h2>
</div>

<table>
    <tr>
        <th>Mã đơn</th>
        <th>Sản phẩm</th>
        <th>Tổng tiền</th>
        <th>Ngày đặt</th>
    </tr>
    <c:forEach var="order" items="${orders}">
        <tr>
            <td><c:out value="${order.code}"/></td>
            <td><c:out value="${order.product}"/></td>
            <td><fmt:formatNumber value="${order.total}" type="currency" currencySymbol="₫"/></td>
            <td><fmt:formatDate value="${order.date}" pattern="dd/MM/yyyy"/></td>
        </tr>
    </c:forEach>
</table>

<p>Tổng lượt xem đơn hàng toàn hệ thống: <c:out value="${applicationScope.totalViewCount}"/></p>
<div class="logout">
    <a href="<c:url value='${pageContext.request.contextPath}/logout' />">Đăng xuất</a>
</div>

</body>
</html>