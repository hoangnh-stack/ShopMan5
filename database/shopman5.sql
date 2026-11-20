-- CSDL ShopMan5 (MySQL 8) - theo thiết kế phần C.2 của báo cáo
DROP DATABASE IF EXISTS shopman5;
CREATE DATABASE shopman5 CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
USE shopman5;

CREATE TABLE tblAddress5 (
  id INT AUTO_INCREMENT PRIMARY KEY,
  houseNumber VARCHAR(50), street VARCHAR(255), ward VARCHAR(255), district VARCHAR(255), city VARCHAR(255)
);

CREATE TABLE tblMember5 (
  id INT AUTO_INCREMENT PRIMARY KEY,
  tblAddress5id INT NOT NULL,
  username VARCHAR(100) NOT NULL UNIQUE,
  password VARCHAR(255) NOT NULL,
  firstName VARCHAR(100), middleName VARCHAR(100), lastName VARCHAR(100),
  dateOfBirth DATE, gender VARCHAR(20), email VARCHAR(255), phone VARCHAR(20),
  role VARCHAR(30), note VARCHAR(500),
  FOREIGN KEY (tblAddress5id) REFERENCES tblAddress5(id)
);

CREATE TABLE tblCustomer5 (
  tblMember5id INT PRIMARY KEY,
  registrationDate DATE,
  FOREIGN KEY (tblMember5id) REFERENCES tblMember5(id)
);

CREATE TABLE tblStaff5 (
  tblMember5id INT PRIMARY KEY,
  position VARCHAR(100),
  FOREIGN KEY (tblMember5id) REFERENCES tblMember5(id)
);

CREATE TABLE tblSupplier5 (
  id INT AUTO_INCREMENT PRIMARY KEY,
  tblAddress5id INT NOT NULL,
  name VARCHAR(255) NOT NULL, phone VARCHAR(20), email VARCHAR(255), note VARCHAR(500),
  FOREIGN KEY (tblAddress5id) REFERENCES tblAddress5(id)
);

CREATE TABLE tblProduct5 (
  id INT AUTO_INCREMENT PRIMARY KEY,
  name VARCHAR(255) NOT NULL, category VARCHAR(100), unit VARCHAR(50),
  salePrice FLOAT, stockQuantity INT DEFAULT 0, description VARCHAR(500), status VARCHAR(50)
);

CREATE TABLE tblImportInvoice5 (
  id INT AUTO_INCREMENT PRIMARY KEY,
  tblSupplier5id INT NOT NULL, managerId INT NOT NULL,
  importDate DATE, paymentStatus VARCHAR(50),
  FOREIGN KEY (tblSupplier5id) REFERENCES tblSupplier5(id),
  FOREIGN KEY (managerId) REFERENCES tblStaff5(tblMember5id)
);

CREATE TABLE tblImportItem5 (
  id INT AUTO_INCREMENT PRIMARY KEY,
  tblImportInvoice5id INT NOT NULL, tblProduct5id INT NOT NULL,
  quantity INT, importPrice FLOAT,
  FOREIGN KEY (tblImportInvoice5id) REFERENCES tblImportInvoice5(id),
  FOREIGN KEY (tblProduct5id) REFERENCES tblProduct5(id)
);

CREATE TABLE tblOrder5 (
  id INT AUTO_INCREMENT PRIMARY KEY,
  tblCustomer5id INT NOT NULL, salespersonId INT, deliveryStaffId INT,
  createdTime DATETIME, paymentMethod VARCHAR(50), status VARCHAR(50), deliveryAddress VARCHAR(500),
  FOREIGN KEY (tblCustomer5id) REFERENCES tblCustomer5(tblMember5id),
  FOREIGN KEY (salespersonId) REFERENCES tblStaff5(tblMember5id),
  FOREIGN KEY (deliveryStaffId) REFERENCES tblStaff5(tblMember5id)
);

CREATE TABLE tblOrderItem5 (
  id INT AUTO_INCREMENT PRIMARY KEY,
  tblOrder5id INT NOT NULL, tblProduct5id INT NOT NULL,
  quantity INT, unitPrice FLOAT,
  FOREIGN KEY (tblOrder5id) REFERENCES tblOrder5(id),
  FOREIGN KEY (tblProduct5id) REFERENCES tblProduct5(id)
);

-- ===== Dữ liệu mẫu =====
INSERT INTO tblAddress5(id, houseNumber, street, ward, district, city) VALUES
 (1, '1', 'Nguyễn Trãi', 'Thanh Xuân Trung', 'Thanh Xuân', 'Hà Nội'),
 (2, '12', 'Láng Hạ', 'Thành Công', 'Ba Đình', 'Hà Nội'),
 (3, '88', 'Nguyễn Huệ', 'Bến Nghé', 'Quận 1', 'TP. Hồ Chí Minh');

-- Nhân viên quản lí mặc định (id = 1), dùng cho chức năng nhập hàng
INSERT INTO tblMember5(id, tblAddress5id, username, password, firstName, middleName, lastName, dateOfBirth, gender, email, phone, role, note)
 VALUES (1, 1, 'quanli', '123456', 'Trần', 'Minh', 'Quân', '1990-03-15', 'Nam', 'quanli@shopman.vn', '0901000001', 'manager', '');
INSERT INTO tblStaff5(tblMember5id, position) VALUES (1, 'Nhân viên quản lí');

INSERT INTO tblSupplier5(tblAddress5id, name, phone, email, note) VALUES
 (2, 'Công ty TNHH Thực phẩm Sạch Việt', '0243 111 2222', 'sachviet@gmail.com', ''),
 (3, 'Công ty CP Thực phẩm Á Châu', '0283 333 4444', 'achau@gmail.com', '');

INSERT INTO tblProduct5(name, category, unit, salePrice, stockQuantity, description, status) VALUES
 ('Dầu ăn Simply 1L', 'Dầu ăn', 'Chai', 52000, 120, 'Dầu đậu nành Simply 1 lít', 'Đang kinh doanh'),
 ('Dầu ăn Neptune 2L', 'Dầu ăn', 'Chai', 98000, 40, 'Dầu ăn Neptune 2 lít', 'Đang kinh doanh'),
 ('Nước mắm Nam Ngư 500ml', 'Gia vị', 'Chai', 36000, 200, 'Nước mắm Nam Ngư chai 500ml', 'Đang kinh doanh');
