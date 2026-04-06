# Analysis: Scope & Race Condition trong hệ thống quản lý đơn hàng

## 1. Thông báo lỗi đăng nhập — Request Scope

**Tại sao phải dùng Request Scope?**
- Thông báo lỗi chỉ cần tồn tại **trong lần request hiện tại** (ví dụ: khi người dùng nhập sai username/password).
- Sử dụng **Request Scope** (`model.addAttribute`) sẽ đảm bảo thông báo chỉ xuất hiện **1 lần** trên trang login. Khi người dùng tải lại trang hoặc chuyển sang trang khác, thông báo tự mất.

**Điều gì xảy ra nếu dùng Session Scope?**
- Thông báo sẽ tồn tại **trong toàn bộ phiên làm việc**.
- Khi người dùng chuyển trang hoặc nhấn reload, thông báo vẫn hiển thị, gây nhầm lẫn và không đúng UX.
- Nhiều user trên cùng session (trường hợp share trình duyệt) sẽ bị hiển thị lỗi của nhau.

---

## 2. totalViewCount — Application Scope

**Tại sao phải dùng Application Scope?**
- `totalViewCount` là **bộ đếm toàn hệ thống**, cần chia sẻ cho tất cả user.
- Application Scope tồn tại trong **ServletContext**, được tất cả session và request truy cập.
- Mỗi lần bất kỳ user nào xem /orders, giá trị tăng lên → đúng yêu cầu.

**Nếu dùng Session Scope?**
- Mỗi user sẽ có **1 biến riêng** trong session.
- Khi user A xem 3 lần, count A = 3; user B xem 2 lần, count B = 2 → không tổng hợp được.
- Tổng lượt xem toàn hệ thống sẽ **không chính xác**.

---

## 3. Race Condition

**Race Condition là gì?**
- Khi nhiều luồng (threads) truy cập và **thay đổi cùng một biến chung** mà không đồng bộ, giá trị có thể bị **ghi đè hoặc sai lệch**.
- Ví dụ, khi hai user cùng truy cập /orders cùng lúc:

```java
Integer count = (Integer) application.getAttribute("totalViewCount");
if (count == null) count = 0;
count++;
application.setAttribute("totalViewCount", count);