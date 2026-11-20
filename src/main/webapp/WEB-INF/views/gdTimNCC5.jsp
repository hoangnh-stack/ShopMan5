<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="title" value="GDTimNCC5 - Tìm nhà cung cấp"/>
<%@ include file="_head.jspf" %>
<h1>Tìm nhà cung cấp</h1>
<form method="post" action="${pageContext.request.contextPath}/quanli/GDTimNCC5">
  <label>Tên nhà cung cấp</label>
  <div class="row"><div><input type="text" name="inTenNCC" value="${param.inTenNCC}" placeholder="Ví dụ: Thực phẩm"></div>
  <div style="flex:0 0 auto"><button class="btn primary" type="submit" name="subTim" value="1">Tìm</button></div></div>
</form>
<c:if test="${daTim && empty listNCC}"><div class="info">Không có nhà cung cấp nào phù hợp. Hãy thêm mới nhà cung cấp.</div></c:if>
<c:if test="${not empty listNCC}">
<h2>Danh sách nhà cung cấp</h2>
<table><tr><th class="c">TT</th><th>Mã NCC</th><th>Tên nhà cung cấp</th><th>Điện thoại</th><th>Địa chỉ</th><th class="c">Chọn</th></tr>
<c:forEach var="s" items="${listNCC}" varStatus="st">
<tr><td class="c">${st.count}</td><td>${s.id}</td><td>${s.name}</td><td>${s.phone}</td>
<td>${s.address.houseNumber} ${s.address.street}, ${s.address.ward}, ${s.address.district}, ${s.address.city}</td>
<td class="c"><form class="inline" method="post" action="${pageContext.request.contextPath}/quanli/GDTimNCC5">
  <button class="btn small primary" type="submit" name="chonNCC" value="${st.index}">Chọn</button></form></td></tr>
</c:forEach></table>
</c:if>
<form method="post" action="${pageContext.request.contextPath}/quanli/GDTimNCC5" class="actions">
  <button class="btn" type="submit" name="subThemNCC" value="1">Thêm mới nhà cung cấp</button>
  <button class="btn" type="submit" name="subQuayLai" value="1">Quay lại</button>
</form>
<%@ include file="_foot.jspf" %>
