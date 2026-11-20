<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="title" value="GDDangKyThanhVien5 - Đăng kí thành viên"/>
<%@ include file="_head.jspf" %>
<h1>Đăng kí thành viên</h1>
<c:if test="${thanhCong}">
  <div class="ok">Đăng kí thành công! Bạn có thể dùng tài khoản <b>${param.inUsername}</b> để đăng nhập.</div>
  <a class="btn primary" href="${pageContext.request.contextPath}/khachhang/GDChinhKH5">OK</a>
</c:if>
<c:if test="${!thanhCong}">
<c:if test="${not empty loi}"><div class="err">${loi}</div></c:if>
<form method="post" action="${pageContext.request.contextPath}/khachhang/GDDangKyThanhVien5">
  <div class="row">
    <div><label>Họ và tên *</label><input type="text" name="inHoTen" value="${param.inHoTen}" placeholder="Nguyễn Văn An"></div>
    <div><label>Ngày sinh * (dd/MM/yyyy)</label><input type="text" name="inNgaySinh" value="${param.inNgaySinh}" placeholder="12/05/2003"></div>
  </div>
  <div class="row">
    <div><label>Giới tính</label>
      <select name="inGioiTinh">
        <option ${param.inGioiTinh == 'Nam' ? 'selected' : ''}>Nam</option>
        <option ${param.inGioiTinh == 'Nữ' ? 'selected' : ''}>Nữ</option>
        <option ${param.inGioiTinh == 'Khác' ? 'selected' : ''}>Khác</option>
      </select></div>
    <div><label>Số điện thoại *</label><input type="text" name="inSdt" value="${param.inSdt}" placeholder="0912345678"></div>
  </div>
  <label>Email *</label><input type="text" name="inEmail" value="${param.inEmail}" placeholder="an.nguyen@gmail.com">
  <label>Địa chỉ * (số nhà đường, phường, quận, thành phố)</label>
  <input type="text" name="inDiaChi" value="${param.inDiaChi}" placeholder="15 Trần Phú, Mộ Lao, Hà Đông, Hà Nội">
  <div class="row">
    <div><label>Tên đăng nhập *</label><input type="text" name="inUsername" value="${param.inUsername}"></div>
    <div><label>Mật khẩu *</label><input type="password" name="inPassword"></div>
    <div><label>Xác nhận mật khẩu *</label><input type="password" name="inXacNhanPassword"></div>
  </div>
  <div class="actions">
    <button class="btn primary" type="submit" name="subDangKy" value="1">Đăng kí</button>
    <button class="btn" type="submit" name="subHuy" value="1" formnovalidate>Hủy</button>
  </div>
</form>
</c:if>
<%@ include file="_foot.jspf" %>
