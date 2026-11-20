package view.quanli;

import java.io.IOException;
import java.util.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import dao.*;
import model.*;
import view.Util5;

@WebServlet("/quanli/GDTimNCC5")
public class GDTimNCC5 extends HttpServlet {

    private final SupplierDAO5 supplierDAO = new SupplierDAO5();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (req.getSession().getAttribute("manager") == null) { resp.sendRedirect(req.getContextPath() + "/quanli/GDChinhQL5"); return; }
        req.setAttribute("listNCC", req.getSession().getAttribute("listNCC"));
        req.getRequestDispatcher("/WEB-INF/views/gdTimNCC5.jsp").forward(req, resp);
    }

    @Override
    @SuppressWarnings("unchecked")
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession ss = req.getSession();
        String ctx = req.getContextPath();
        if (req.getParameter("subQuayLai") != null) { resp.sendRedirect(ctx + "/quanli/GDChinhQL5"); return; }
        if (req.getParameter("subThemNCC") != null) { resp.sendRedirect(ctx + "/quanli/GDThemNCC5"); return; }
        if (req.getParameter("subTim") != null) {
            ArrayList<Supplier5> ds = supplierDAO.timNCCTheoTen(Util5.t(req.getParameter("inTenNCC")));   // bước 5-10
            ss.setAttribute("listNCC", ds);
            req.setAttribute("listNCC", ds);
            req.setAttribute("daTim", Boolean.TRUE);
            req.getRequestDispatcher("/WEB-INF/views/gdTimNCC5.jsp").forward(req, resp);
            return;
        }
        String chon = req.getParameter("chonNCC");                 // click chọn một dòng trong bảng NCC
        if (chon != null) {
            ArrayList<Supplier5> ds = (ArrayList<Supplier5>) ss.getAttribute("listNCC");
            int i = Integer.parseInt(chon);
            if (ds != null && i >= 0 && i < ds.size()) {
                ss.setAttribute("supplier", ds.get(i));
                ss.setAttribute("listNhap", new ArrayList<ImportItem5>());
                ss.removeAttribute("listHang");
                resp.sendRedirect(ctx + "/quanli/GDNhapHang5");
                return;
            }
        }
        doGet(req, resp);
    }
}
