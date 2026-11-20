<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="title" value="GDChinhQL5 - Trang chủ nhân viên quản lí"/>
<%@ include file="_head.jspf" %>
<h1>Trang chủ nhân viên quản lí</h1>
<p class="sub center">Xin chào, ${manager.fullName.firstName} ${manager.fullName.middleName} ${manager.fullName.lastName}</p>
<form method="post" action="${pageContext.request.contextPath}/quanli/GDChinhQL5" class="center">
  <button class="btn big" type="button" disabled>Xem thống kê</button>
  <button class="btn big" type="button" disabled>Quản lí thông tin hàng hóa</button>
  <button class="btn big primary" type="submit" name="subNhapHang" value="1">Nhập hàng từ nhà cung cấp</button>
  <button class="btn big" type="button" disabled>Quản lí thông tin nhà cung cấp</button>
</form>

