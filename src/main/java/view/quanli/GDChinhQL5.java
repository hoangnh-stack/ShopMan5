package view.quanli;

import java.io.IOException;
import java.util.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import dao.*;
import model.*;
import view.Util5;

@WebServlet("/quanli/GDChinhQL5")
public class GDChinhQL5 extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession ss = req.getSession();
        // Giả định quản lí đã đăng nhập (use case Đăng nhập nằm ngoài phạm vi): quản lí mặc định id = 1
        Manager5 m = new Manager5(); m.setId(1); m.setUsername("quanli");
        FullName5 f = new FullName5(); f.setFirstName("Nguyễn"); f.setMiddleName("Huy"); f.setLastName("Hoàng"); m.setFullName(f);
        ss.setAttribute("manager", m);
        ss.removeAttribute("supplier"); ss.removeAttribute("listNCC"); ss.removeAttribute("listHang");
        ss.removeAttribute("listNhap"); ss.removeAttribute("hoaDon");
        req.getRequestDispatcher("/WEB-INF/views/gdChinhQL5.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (req.getParameter("subNhapHang") != null) resp.sendRedirect(req.getContextPath() + "/quanli/GDTimNCC5");
        else doGet(req, resp);
    }
}
