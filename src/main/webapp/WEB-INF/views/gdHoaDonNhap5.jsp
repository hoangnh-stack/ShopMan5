<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="title" value="GDHoaDonNhap5 - Hóa đơn nhập hàng"/>
<%@ include file="_head.jspf" %>
<h1>Hóa đơn nhập hàng</h1>
<c:if test="${not empty loi}"><div class="err noprint">${loi}</div></c:if>
<c:if test="${not empty thongBao}"><div class="ok noprint">${thongBao}</div></c:if>
<div class="info">
  <b>Mã hóa đơn:</b> ${hoaDon.id} &nbsp;|&nbsp; <b>Ngày nhập:</b> <fmt:formatDate value="${hoaDon.importDate}" pattern="dd/MM/yyyy"/><br>
  <b>Nhà cung cấp:</b> ${hoaDon.supplier.name} (${hoaDon.supplier.phone}) &nbsp;|&nbsp;
  <b>Người lập:</b> ${hoaDon.manager.fullName.firstName} ${hoaDon.manager.fullName.middleName} ${hoaDon.manager.fullName.lastName}
</div>
<table><tr><th class="c">TT</th><th>Mã hàng</th><th>Tên mặt hàng</th><th class="num">Số lượng</th><th class="num">Đơn giá</th><th class="num">Thành tiền</th></tr>
<c:forEach var="it" items="${hoaDon.items}" varStatus="st">
<tr><td class="c">${st.count}</td><td>${it.product.id}</td><td>${it.product.name}</td><td class="num">${it.quantity}</td>
<td class="num"><fmt:formatNumber value="${it.importPrice}" pattern="#,##0"/></td>
<td class="num"><fmt:formatNumber value="${it.thanhTien}" pattern="#,##0"/></td></tr>
</c:forEach></table>
<div class="total">Tổng tiền: <fmt:formatNumber value="${hoaDon.tongTien}" pattern="#,##0"/> đ</div>
<p><b>Trạng thái thanh toán:</b> ${hoaDon.paymentStatus}</p>
<form method="post" action="${pageContext.request.contextPath}/quanli/GDHoaDonNhap5" class="actions noprint">
  <button class="btn primary" type="submit" name="subThanhToan" value="1">In hóa đơn và thanh toán</button>
  <button class="btn" type="button" onclick="window.print()">Chỉ in</button>
  <button class="btn" type="submit" name="subVeTrangChu" value="1">Về trang chủ</button>
</form>
<c:if test="${tuIn}"><script>window.onload = function () { window.print(); };</script></c:if>
<%@ include file="_foot.jspf" %>
