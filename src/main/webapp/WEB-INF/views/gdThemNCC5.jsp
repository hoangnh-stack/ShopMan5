<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="title" value="GDThemNCC5 - Thêm mới nhà cung cấp"/>
<%@ include file="_head.jspf" %>
<h1>Thêm mới nhà cung cấp</h1>
<c:if test="${thanhCong}">
  <div class="ok">Thêm nhà cung cấp thành công!</div>
  <a class="btn primary" href="${pageContext.request.contextPath}/quanli/GDTimNCC5">OK</a>
</c:if>
<c:if test="${!thanhCong}">
<c:if test="${not empty loi}"><div class="err">${loi}</div></c:if>
<form method="post" action="${pageContext.request.contextPath}/quanli/GDThemNCC5">
  <label>Tên nhà cung cấp *</label><input type="text" name="inTenNCC" value="${param.inTenNCC}">
  <div class="row">
    <div><label>Số điện thoại *</label><input type="text" name="inSdt" value="${param.inSdt}"></div>
    <div><label>Email</label><input type="text" name="inEmail" value="${param.inEmail}"></div>
  </div>
  <label>Địa chỉ * (số nhà đường, phường, quận, thành phố)</label>
  <input type="text" name="inDiaChi" value="${param.inDiaChi}" placeholder="22 Lê Lợi, Hải Châu, Đà Nẵng">
  <label>Ghi chú</label><input type="text" name="inGhiChu" value="${param.inGhiChu}">
  <div class="actions">
    <button class="btn primary" type="submit" name="subLuu" value="1">Lưu</button>
    <button class="btn" type="submit" name="subHuy" value="1" formnovalidate>Hủy</button>
  </div>
</form>
</c:if>
<%@ include file="_foot.jspf" %>
