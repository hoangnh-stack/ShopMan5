<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="title" value="GDChinhKH5 - Trang chủ khách hàng"/>
<%@ include file="_head.jspf" %>
<h1>Trang chủ khách hàng</h1><p class="sub center">Chào mừng bạn đến với ShopMan</p>
<form method="post" action="${pageContext.request.contextPath}/khachhang/GDChinhKH5" class="center">
  <button class="btn big" type="submit" name="subDangKyThanhVien" value="1">Đăng kí thành viên</button>
  <button class="btn big" type="button" disabled>Tìm kiếm / tra cứu hàng hóa</button>
  <button class="btn big" type="button" disabled>Đặt mua hàng trực tuyến</button>
  <button class="btn big" type="button" disabled>Theo dõi trạng thái hóa đơn</button>
</form>

