<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<html>
<head>
    <title>Report</title>
    <style>
        body { font-family: Arial; background: #f0f2f5; padding: 20px; }
        h1 { color: #007bff; }
        table { border-collapse: collapse; width: 60%; margin-top: 20px; background: white; }
        th, td { border: 1px solid #ddd; padding: 8px; text-align: center; }
        th { background: #007bff; color: white; }
        tr:nth-child(even){ background-color: #f2f2f2; }
    </style>
</head>
<body>

<h1><c:out value="${reportTitle}"/></h1>

<table>
    <tr>
        <th>STT</th>
        <th>Họ tên</th>
        <th>Điểm</th>
        <th>Xếp loại</th>
    </tr>
    <c:forEach var="student" items="${studentList}" varStatus="status">
        <tr>
            <td><c:out value="${status.index + 1}"/></td>
            <td><c:out value="${student.name}"/></td>
            <td><c:out value="${student.score}"/></td>
            <td>
                <c:choose>
                    <c:when test="${student.score >= 90}">Xuất sắc</c:when>
                    <c:when test="${student.score >= 80}">Giỏi</c:when>
                    <c:when test="${student.score >= 70}">Khá</c:when>
                    <c:when test="${student.score >= 60}">Trung bình khá</c:when>
                    <c:when test="${student.score >= 50}">Trung bình</c:when>
                    <c:otherwise>Yếu</c:otherwise>
                </c:choose>
            </td>
        </tr>
    </c:forEach>
</table>

</body>
</html>