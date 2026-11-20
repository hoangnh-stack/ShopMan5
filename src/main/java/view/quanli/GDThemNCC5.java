package view.quanli;

import java.io.IOException;
import java.util.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import dao.*;
import model.*;
import view.Util5;

@WebServlet("/quanli/GDThemNCC5")
public class GDThemNCC5 extends HttpServlet {

    private final SupplierDAO5 supplierDAO = new SupplierDAO5();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (req.getSession().getAttribute("manager") == null) { resp.sendRedirect(req.getContextPath() + "/quanli/GDChinhQL5"); return; }
        req.getRequestDispatcher("/WEB-INF/views/gdThemNCC5.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (req.getParameter("subHuy") != null) { resp.sendRedirect(req.getContextPath() + "/quanli/GDTimNCC5"); return; }
        String ten = Util5.t(req.getParameter("inTenNCC")), sdt = Util5.t(req.getParameter("inSdt"));
        String email = Util5.t(req.getParameter("inEmail")), diaChi = Util5.t(req.getParameter("inDiaChi"));
        String ghiChu = Util5.t(req.getParameter("inGhiChu"));
        String loi = null;
        if (ten.isEmpty() || sdt.isEmpty() || diaChi.isEmpty()) loi = "Vui lòng nhập tên, số điện thoại và địa chỉ.";
        else if (!Util5.phoneOk(sdt.replace(" ", ""))) loi = "Số điện thoại không hợp lệ.";
        else if (!email.isEmpty() && !Util5.emailOk(email)) loi = "Email không hợp lệ.";
        if (loi == null) {
            Supplier5 s = new Supplier5();
            s.setName(ten); s.setPhone(sdt); s.setEmail(email); s.setNote(ghiChu); s.setAddress(Util5.tachDiaChi(diaChi));
            if (supplierDAO.themNCC(s)) req.setAttribute("thanhCong", Boolean.TRUE);      // bước 19-21
            else loi = "Không thể lưu nhà cung cấp, vui lòng thử lại.";
        }
        req.setAttribute("loi", loi);
        req.getRequestDispatcher("/WEB-INF/views/gdThemNCC5.jsp").forward(req, resp);
    }
}
