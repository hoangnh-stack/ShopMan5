package view.quanli;

import java.io.IOException;
import java.util.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import dao.*;
import model.*;
import view.Util5;

@WebServlet("/quanli/GDThemHang5")
public class GDThemHang5 extends HttpServlet {

    private final ProductDAO5 productDAO = new ProductDAO5();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (req.getSession().getAttribute("supplier") == null) { resp.sendRedirect(req.getContextPath() + "/quanli/GDTimNCC5"); return; }
        req.getRequestDispatcher("/WEB-INF/views/gdThemHang5.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (req.getParameter("subHuy") != null) { resp.sendRedirect(req.getContextPath() + "/quanli/GDNhapHang5"); return; }
        String ten = Util5.t(req.getParameter("inTenHang")), loai = Util5.t(req.getParameter("inLoai"));
        String dv = Util5.t(req.getParameter("inDonVi")), gia = Util5.t(req.getParameter("inGiaBan")), mota = Util5.t(req.getParameter("inMoTa"));
        String loi = null; float giaBan = 0;
        if (ten.isEmpty() || dv.isEmpty() || gia.isEmpty()) loi = "Vui lòng nhập tên, đơn vị tính và giá bán.";
        else {
            try { giaBan = Float.parseFloat(gia); if (giaBan <= 0) loi = "Giá bán phải lớn hơn 0."; }
            catch (NumberFormatException e) { loi = "Giá bán phải là số."; }
        }
        if (loi == null) {
            Product5 p = new Product5();
            p.setName(ten); p.setCategory(loai); p.setUnit(dv); p.setSalePrice(giaBan); p.setDescription(mota);
            p.setStockQuantity(0); p.setStatus("Đang kinh doanh");
            if (productDAO.themHang(p)) req.setAttribute("thanhCong", Boolean.TRUE);       // bước 44-46
            else loi = "Không thể lưu mặt hàng, vui lòng thử lại.";
        }
        req.setAttribute("loi", loi);
        req.getRequestDispatcher("/WEB-INF/views/gdThemHang5.jsp").forward(req, resp);
    }
}
