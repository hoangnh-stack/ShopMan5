<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="title" value="GDNhapHang5 - Nhập hàng từ nhà cung cấp"/>
<%@ include file="_head.jspf" %>
<h1>Nhập hàng từ nhà cung cấp</h1>
<div class="info"><b>Nhà cung cấp:</b> ${supplier.name} &nbsp;|&nbsp; <b>Điện thoại:</b> ${supplier.phone}</div>
<c:if test="${not empty loi}"><div class="err">${loi}</div></c:if>
<form method="post" action="${pageContext.request.contextPath}/quanli/GDNhapHang5">
  <label>Tên mặt hàng</label>
  <div class="row"><div><input type="text" name="inTenHang" value="${param.inTenHang}" placeholder="Ví dụ: Dầu ăn"></div>
  <div style="flex:0 0 auto"><button class="btn primary" type="submit" name="subTim" value="1">Tìm</button>
  <button class="btn" type="submit" name="subThemHang" value="1" formnovalidate>Thêm mới mặt hàng</button></div></div>

  <c:if test="${daTim && empty listHang}"><div class="info">Không có mặt hàng nào phù hợp. Hãy thêm mới mặt hàng.</div></c:if>
  <c:if test="${not empty listHang}">
  <h2>Danh sách mặt hàng tìm được (chọn một mặt hàng)</h2>
  <table><tr><th class="c">Chọn</th><th>Mã hàng</th><th>Tên mặt hàng</th><th>Loại</th><th>ĐVT</th><th class="num">Tồn kho</th></tr>
  <c:forEach var="p" items="${listHang}" varStatus="st">
    <tr><td class="c"><input type="radio" name="chonHang" value="${st.index}" ${param.chonHang == st.index ? 'checked' : ''}></td>
    <td>${p.id}</td><td>${p.name}</td><td>${p.category}</td><td>${p.unit}</td><td class="num">${p.stockQuantity}</td></tr>
  </c:forEach></table>
  <div class="row">
    <div><label>Số lượng nhập</label><input type="text" name="inSoLuong" value="${param.inSoLuong}"></div>
    <div><label>Đơn giá nhập (đ)</label><input type="text" name="inDonGia" value="${param.inDonGia}"></div>
    <div style="flex:0 0 auto;align-self:flex-end"><button class="btn primary" type="submit" name="subThem" value="1">Thêm</button></div>
  </div>
  </c:if>
</form>

<h2>Danh sách hàng nhập</h2>
<c:if test="${empty sessionScope.listNhap}"><div class="info">Chưa có mặt hàng nào trong hóa đơn nhập.</div></c:if>
<c:if test="${not empty sessionScope.listNhap}">
<form method="post" action="${pageContext.request.contextPath}/quanli/GDNhapHang5">
<table><tr><th class="c">TT</th><th>Mã hàng</th><th>Tên mặt hàng</th><th class="num">Số lượng</th><th class="num">Đơn giá</th><th class="num">Thành tiền</th><th class="c">Xóa</th></tr>
<c:forEach var="it" items="${sessionScope.listNhap}" varStatus="st">
<tr><td class="c">${st.count}</td><td>${it.product.id}</td><td>${it.product.name}</td><td class="num">${it.quantity}</td>
<td class="num"><fmt:formatNumber value="${it.importPrice}" pattern="#,##0"/></td>
<td class="num"><fmt:formatNumber value="${it.thanhTien}" pattern="#,##0"/></td>
<td class="c"><button class="btn small danger" type="submit" name="xoaDong" value="${st.index}">×</button></td></tr>
</c:forEach></table>
</form>
<div class="total">Tổng tiền: <fmt:formatNumber value="${tongTien}" pattern="#,##0"/> đ</div>
</c:if>
<form method="post" action="${pageContext.request.contextPath}/quanli/GDNhapHang5" class="actions">
  <button class="btn primary" type="submit" name="subXacNhan" value="1">Xác nhận hóa đơn</button>
  <button class="btn" type="submit" name="subQuayLai" value="1">Quay lại</button>
</form>
<%@ include file="_foot.jspf" %>
