<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="title" value="GDThemHang5 - Thêm mới mặt hàng"/>
<%@ include file="_head.jspf" %>
<h1>Thêm mới mặt hàng</h1>
<c:if test="${thanhCong}">
  <div class="ok">Thêm mặt hàng thành công!</div>
  <a class="btn primary" href="${pageContext.request.contextPath}/quanli/GDNhapHang5">OK</a>
</c:if>
<c:if test="${!thanhCong}">
<c:if test="${not empty loi}"><div class="err">${loi}</div></c:if>
<form method="post" action="${pageContext.request.contextPath}/quanli/GDThemHang5">
  <label>Tên mặt hàng *</label><input type="text" name="inTenHang" value="${param.inTenHang}">
  <div class="row">
    <div><label>Loại hàng</label><input type="text" name="inLoai" value="${param.inLoai}"></div>
    <div><label>Đơn vị tính *</label><input type="text" name="inDonVi" value="${param.inDonVi}" placeholder="chai, gói, thùng, kg..."></div>
    <div><label>Giá bán (đ) *</label><input type="text" name="inGiaBan" value="${param.inGiaBan}"></div>
  </div>
  <label>Mô tả</label><input type="text" name="inMoTa" value="${param.inMoTa}">
  <div class="actions">
    <button class="btn primary" type="submit" name="subLuu" value="1">Lưu</button>
    <button class="btn" type="submit" name="subHuy" value="1" formnovalidate>Hủy</button>
  </div>
</form>
</c:if>
<%@ include file="_foot.jspf" %>
