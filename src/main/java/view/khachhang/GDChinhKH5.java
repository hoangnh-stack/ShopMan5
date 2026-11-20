package view.khachhang;

import java.io.IOException;
import java.util.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import dao.*;
import model.*;
import view.Util5;

@WebServlet("/khachhang/GDChinhKH5")
public class GDChinhKH5 extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/views/gdChinhKH5.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (req.getParameter("subDangKyThanhVien") != null)
            resp.sendRedirect(req.getContextPath() + "/khachhang/GDDangKyThanhVien5");
        else doGet(req, resp);
    }
}
