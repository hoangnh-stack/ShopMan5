package view.khachhang;

import java.io.IOException;
import java.util.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import dao.*;
import model.*;
import view.Util5;

@WebServlet("/khachhang/GDDangKyThanhVien5")
public class GDDangKyThanhVien5 extends HttpServlet {

    private final MemberDAO5 memberDAO = new MemberDAO5();
    private final CustomerDAO5 customerDAO = new CustomerDAO5();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/views/gdDangKyThanhVien5.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (req.getParameter("subHuy") != null) {
            resp.sendRedirect(req.getContextPath() + "/khachhang/GDChinhKH5");
            return;
        }
        String hoTen = Util5.t(req.getParameter("inHoTen")), ngaySinh = Util5.t(req.getParameter("inNgaySinh"));
        String gioiTinh = Util5.t(req.getParameter("inGioiTinh")), sdt = Util5.t(req.getParameter("inSdt"));
        String email = Util5.t(req.getParameter("inEmail")), diaChi = Util5.t(req.getParameter("inDiaChi"));
        String user = Util5.t(req.getParameter("inUsername")), pass = req.getParameter("inPassword") == null ? "" : req.getParameter("inPassword");
        String xn = req.getParameter("inXacNhanPassword") == null ? "" : req.getParameter("inXacNhanPassword");

        String loi = null;
        Date dob = Util5.parseDate(ngaySinh);
        if (hoTen.isEmpty() || ngaySinh.isEmpty() || sdt.isEmpty() || email.isEmpty() || diaChi.isEmpty() || user.isEmpty() || pass.isEmpty())
            loi = "Vui lòng nhập đầy đủ các thông tin bắt buộc.";
        else if (dob == null) loi = "Ngày sinh không hợp lệ (định dạng dd/MM/yyyy).";
        else if (!Util5.phoneOk(sdt)) loi = "Số điện thoại không hợp lệ.";
        else if (!Util5.emailOk(email)) loi = "Email không hợp lệ.";
        else if (!pass.equals(xn)) loi = "Mật khẩu xác nhận không khớp.";
        else if (memberDAO.kiemTraTonTaiUsername(user)) loi = "Tên đăng nhập đã tồn tại.";   // bước 5-7 (v.3.0)

        if (loi == null) {
            Customer5 c = new Customer5();                       // lớp thực thể đóng gói thông tin
            c.setFullName(Util5.tachHoTen(hoTen)); c.setDateOfBirth(dob); c.setGender(gioiTinh);
            c.setPhone(sdt); c.setEmail(email); c.setAddress(Util5.tachDiaChi(diaChi));
            c.setUsername(user); c.setPassword(pass); c.setNote("");
            c.setRegistrationDate(new Date());
            if (customerDAO.dangKyThanhVien(c)) req.setAttribute("thanhCong", Boolean.TRUE);
            else loi = "Không thể đăng kí, vui lòng thử lại.";
        }
        req.setAttribute("loi", loi);
        req.getRequestDispatcher("/WEB-INF/views/gdDangKyThanhVien5.jsp").forward(req, resp);
    }
}
