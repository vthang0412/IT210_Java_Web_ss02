Phân tích Bài 2 – JSP lộn xộn (report.jsp)

## 1. Vị trí & loại vấn đề

| # | Vị trí / Thành phần | Loại vấn đề | Hậu quả |
|---|--------------------|------------|---------|
| 1 | `<%! private int requestCounter = 0; %>` | Race Condition / Scope sai | Biến toàn cục chia sẻ cho tất cả request → nhiều user truy cập cùng lúc bị lẫn lộn lượt xem |
| 2 | `<% requestCounter++; %>` | Logic trong View | JSP chứa logic tính toán → vi phạm nguyên tắc Thin View |
| 3 | `<td><%= sv.getFullName() %></td>` | XSS | Trực tiếp in dữ liệu người dùng → nguy cơ Cross-site Scripting |
| 4 | `<td><%= sv.getScore()%>;</td>` | Syntax dư dấu chấm phẩy | JSP vẫn chạy nhưng dư ký tự → dễ nhầm lẫn |
| 5 | `<!-- Tiêu đề trang báo cáo -->` | Comment HTML / nội bộ | HTML comment render ra client, không nên dùng cho ghi chú nội bộ → dùng `<%-- --%>` |

---

## 2. Nguyên tắc rút ra
- **Thin View:** Không chứa logic, chỉ hiển thị dữ liệu đã chuẩn bị.
- **EL + JSTL:** Dùng `${}` và `<c:forEach>` thay vì `<% %>` để code sạch, dễ maintain.
- **XSS:** Luôn dùng `<c:out>` khi hiển thị dữ liệu người dùng.
- **Scope:** Không dùng biến toàn cục trong JSP; Controller đẩy vào request scope.

---

## 3. Giải pháp chuẩn (report.jsp)
- Không dùng Scriptlet `<% %>` để lặp/điều kiện.
- Dùng `<c:forEach>` vòng lặp và `<c:choose>` điều kiện.
- Dùng `<c:out>` xuất dữ liệu.

Ví dụ hiển thị điểm sinh viên:
```jsp
<h1><c:out value="${reportTitle}"/></h1>
<p>Lượt xem trang: <c:out value="${requestCounter}"/></p>

<table border="1">
    <tr>
        <th>STT</th>
        <th>Họ tên</th>
        <th>Điểm</th>
        <th>Xếp loại</th>
    </tr>
    <c:forEach var="sv" items="${studentList}" varStatus="status">
        <tr>
            <td><c:out value="${status.index + 1}"/></td>
            <td><c:out value="${sv.fullName}"/></td>
            <td><c:out value="${sv.score}"/></td>
            <td>
                <c:choose>
                    <c:when test="${sv.score >= 90}">Xuất sắc</c:when>
                    <c:when test="${sv.score >= 80}">Giỏi</c:when>
                    <c:when test="${sv.score >= 70}">Khá</c:when>
                    <c:when test="${sv.score >= 60}">Trung bình khá</c:when>
                    <c:when test="${sv.score >= 50}">Trung bình</c:when>
                    <c:otherwise>Yếu</c:otherwise>
                </c:choose>
            </td>
        </tr>
    </c:forEach>
</table>