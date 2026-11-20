package view.quanli;

import java.io.IOException;
import java.util.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import dao.*;
import model.*;
import view.Util5;

@WebServlet("/quanli/GDHoaDonNhap5")
public class GDHoaDonNhap5 extends HttpServlet {

    private final ImportInvoiceDAO5 invoiceDAO = new ImportInvoiceDAO5();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (req.getSession().getAttribute("hoaDon") == null) { resp.sendRedirect(req.getContextPath() + "/quanli/GDChinhQL5"); return; }
        req.getRequestDispatcher("/WEB-INF/views/gdHoaDonNhap5.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession ss = req.getSession();
        ImportInvoice5 hd = (ImportInvoice5) ss.getAttribute("hoaDon");
        if (hd == null) { resp.sendRedirect(req.getContextPath() + "/quanli/GDChinhQL5"); return; }
        if (req.getParameter("subVeTrangChu") != null) { resp.sendRedirect(req.getContextPath() + "/quanli/GDChinhQL5"); return; }
        if (req.getParameter("subThanhToan") != null) {                       // bước 63-67
            if ("Đã thanh toán".equals(hd.getPaymentStatus())) req.setAttribute("loi", "Hóa đơn này đã được thanh toán.");
            else if (invoiceDAO.thanhToanHoaDonNhap(hd.getId())) {
                hd.setPaymentStatus("Đã thanh toán");
                req.setAttribute("thongBao", "Đã in hóa đơn nhập và thanh toán cho nhà cung cấp thành công.");
                req.setAttribute("tuIn", Boolean.TRUE);                         // trang tự mở hộp thoại in
            } else req.setAttribute("loi", "Thanh toán thất bại, vui lòng thử lại.");
        }
        doGet(req, resp);
    }
}
