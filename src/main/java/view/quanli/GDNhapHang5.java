package view.quanli;

import java.io.IOException;
import java.util.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import dao.*;
import model.*;
import view.Util5;

@WebServlet("/quanli/GDNhapHang5")
public class GDNhapHang5 extends HttpServlet {

    private final ProductDAO5 productDAO = new ProductDAO5();
    private final ImportInvoiceDAO5 invoiceDAO = new ImportInvoiceDAO5();

    @Override
    @SuppressWarnings("unchecked")
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession ss = req.getSession();
        if (ss.getAttribute("manager") == null || ss.getAttribute("supplier") == null) {
            resp.sendRedirect(req.getContextPath() + "/quanli/GDTimNCC5"); return;
        }
        if (ss.getAttribute("listNhap") == null) ss.setAttribute("listNhap", new ArrayList<ImportItem5>());
        req.setAttribute("listHang", ss.getAttribute("listHang"));
        double tong = 0;
        for (ImportItem5 it : (ArrayList<ImportItem5>) ss.getAttribute("listNhap")) tong += it.getThanhTien();
        req.setAttribute("tongTien", tong);
        req.getRequestDispatcher("/WEB-INF/views/gdNhapHang5.jsp").forward(req, resp);
    }

    @Override
    @SuppressWarnings("unchecked")
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession ss = req.getSession();
        String ctx = req.getContextPath();
        if (ss.getAttribute("supplier") == null) { resp.sendRedirect(ctx + "/quanli/GDTimNCC5"); return; }
        ArrayList<ImportItem5> listNhap = (ArrayList<ImportItem5>) ss.getAttribute("listNhap");
        if (listNhap == null) { listNhap = new ArrayList<>(); ss.setAttribute("listNhap", listNhap); }
        String loi = null;

        if (req.getParameter("subQuayLai") != null) { resp.sendRedirect(ctx + "/quanli/GDTimNCC5"); return; }
        if (req.getParameter("subThemHang") != null) { resp.sendRedirect(ctx + "/quanli/GDThemHang5"); return; }

        if (req.getParameter("subTim") != null) {                            // bước 29-36
            ss.setAttribute("listHang", productDAO.timHangTheoTen(Util5.t(req.getParameter("inTenHang"))));
            req.setAttribute("daTim", Boolean.TRUE);
        } else if (req.getParameter("subThem") != null) {                    // bước 51-53
            ArrayList<Product5> ds = (ArrayList<Product5>) ss.getAttribute("listHang");
            String chon = req.getParameter("chonHang");
            try {
                int sl = Integer.parseInt(Util5.t(req.getParameter("inSoLuong")));
                float gia = Float.parseFloat(Util5.t(req.getParameter("inDonGia")));
                if (chon == null || ds == null) loi = "Hãy tìm và chọn một mặt hàng trước khi thêm.";
                else if (sl <= 0 || gia <= 0) loi = "Số lượng và đơn giá phải lớn hơn 0.";
                else {
                    Product5 p = ds.get(Integer.parseInt(chon));
                    boolean da = false;
                    for (ImportItem5 it : listNhap)
                        if (it.getProduct().getId() == p.getId()) { it.setQuantity(it.getQuantity() + sl); it.setImportPrice(gia); da = true; }
                    if (!da) { ImportItem5 it = new ImportItem5(); it.setProduct(p); it.setQuantity(sl); it.setImportPrice(gia); listNhap.add(it); }
                }
            } catch (NumberFormatException e) { loi = "Số lượng và đơn giá phải là số hợp lệ."; }
        } else if (req.getParameter("xoaDong") != null) {                    // xóa một dòng trong bảng hàng nhập
            int i = Integer.parseInt(req.getParameter("xoaDong"));
            if (i >= 0 && i < listNhap.size()) listNhap.remove(i);
        } else if (req.getParameter("subXacNhan") != null) {                 // bước 54-60
            if (listNhap.isEmpty()) loi = "Chưa có mặt hàng nào trong hóa đơn nhập.";
            else {
                ImportInvoice5 hd = new ImportInvoice5();
                hd.setManager((Manager5) ss.getAttribute("manager"));
                hd.setSupplier((Supplier5) ss.getAttribute("supplier"));
                hd.setImportDate(new Date());
                hd.setItems(listNhap.toArray(new ImportItem5[0]));
                if (invoiceDAO.luuHoaDonNhap(hd)) {
                    ss.setAttribute("hoaDon", hd);
                    ss.removeAttribute("listNhap"); ss.removeAttribute("listHang");
                    resp.sendRedirect(ctx + "/quanli/GDHoaDonNhap5");         // bước 61
                    return;
                }
                loi = "Lỗi khi lưu hóa đơn nhập, vui lòng thử lại.";
            }
        }
        req.setAttribute("loi", loi);
        doGet(req, resp);
    }
}
