# ShopMan5 – Bán hàng bách hóa trực tuyến (Đề số 32)

Code giao diện (JSP + Servlet) cho hai module trong báo cáo: **Khách hàng đăng kí thành viên** và **Nhân viên quản lí nhập hàng hóa**.
Kiến trúc ba lớp: `view` (servlet + JSP) – `dao` (JDBC) – `model` (thực thể), CSDL MySQL `shopman5`.

## Yêu cầu
- JDK 11+ , Maven 3.8+ , MySQL 8
- VS Code + **Extension Pack for Java** (và tùy chọn *Community Server Connectors* nếu muốn dùng Tomcat 9)

## Cách chạy
1. Tạo CSDL: chạy file `database/shopman5.sql` trong MySQL (Workbench hoặc `mysql -u root -p < database/shopman5.sql`).
2. Mở thư mục `ShopMan5` bằng VS Code (`File > Open Folder`).
3. Nếu MySQL không dùng `root/123456` thì sửa URL, user, password trong `src/main/java/dao/DAO5.java`.
4. Mở Terminal trong VS Code và chạy: `mvn jetty:run`
5. Mở trình duyệt: <http://localhost:8080/ShopMan5/>

Dùng Tomcat 9 thay cho Jetty: `mvn clean package`, rồi triển khai `target/ShopMan5.war` (Tomcat 10+ không chạy được vì dùng `javax.servlet`).

## Luồng giao diện
| Module | Luồng |
|---|---|
| Đăng kí thành viên | GDChinhKH5 → GDDangKyThanhVien5 → (lưu thành công) OK → GDChinhKH5 |
| Nhập hàng | GDChinhQL5 → GDTimNCC5 (⇄ GDThemNCC5) → GDNhapHang5 (⇄ GDThemHang5) → GDHoaDonNhap5 → GDChinhQL5 |

Dữ liệu mẫu: quản lí mặc định `quanli` (id = 1, được gán sẵn khi vào GDChinhQL5 vì use case Đăng nhập nằm ngoài phạm vi), 2 nhà cung cấp, 3 mặt hàng.
Thử nhanh: đăng kí với username mới; nhập hàng: tìm NCC “Thực phẩm” → chọn → tìm hàng “Dầu ăn” → chọn, SL 50, giá 42000 → Thêm → Xác nhận hóa đơn → In hóa đơn và thanh toán.

## Cấu trúc
```
src/main/java/model   (thực thể)      src/main/java/dao    (DAO5 + 5 lớp DAO)
src/main/java/view    (servlet theo gói khachhang / quanli, CharsetFilter, Util5)
src/main/webapp/WEB-INF/views   (các trang JSP)       src/main/webapp/css/style.css
database/shopman5.sql           (tạo bảng + dữ liệu mẫu)
```
Mật khẩu được lưu dạng văn bản thô để đơn giản hóa bài tập; hệ thống thật cần băm mật khẩu.
