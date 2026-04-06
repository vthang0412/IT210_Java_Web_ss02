# Phân tích Bài 1 – Chẩn đoán luồng chết trong Spring MVC

## 1. Lỗi trong MyWebAppInitializer.java
```java
@Override
protected String[] getServletMappings() {
    return new String[] { "/api/*" };
}
Giải thích:

Servlet mapping "/api/*" khiến DispatcherServlet chỉ nhận các request bắt đầu bằng /api/.
URL /welcome không nằm trong phạm vi /api/
* → trình duyệt trả 404 – Not Found.
DispatcherServlet đang “nghe” các URL kiểu /api/... mà thôi.

## 2. Lỗi trong WebConfig.java
@ComponentScan(basePackages = "com.demo.service")

Hậu quả:

Spring MVC chỉ quét package com.demo.service.
WelcomeController nằm ở com.demo.controller, nên không được quét, request /welcome vẫn 404.
3. Tổng hợp
Nếu chỉ sửa Servlet Mapping mà không sửa ComponentScan:
DispatcherServlet nhận request /welcome.
Nhưng Spring không tìm thấy WelcomeController → 404 vẫn xảy ra.
Cần sửa cả hai lỗi để ứng dụng chạy đúng.
4. Giải pháp
MyWebAppInitializer.java
@Override
protected String[] getServletMappings() {
    return new String[] { "/" }; 
}
WebConfig.java
@ComponentScan(basePackages = {"com.demo.controller", "com.demo.service"})
Khi đó:
DispatcherServlet nhận tất cả request.
Spring quét được Controller → /welcome hiển thị thành công.