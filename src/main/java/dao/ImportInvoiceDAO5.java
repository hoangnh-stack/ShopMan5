package dao;
import java.sql.*;
import model.*;

public class ImportInvoiceDAO5 extends DAO5 {
    public ImportInvoiceDAO5() { super(); }

    /** Lưu hóa đơn nhập và các dòng hàng nhập, cập nhật tồn kho, trong một giao dịch. */
    public boolean luuHoaDonNhap(ImportInvoice5 hd) {
        String sqlH = "INSERT INTO tblImportInvoice5(tblSupplier5id, managerId, importDate, paymentStatus) VALUES (?,?,?,?)";
        String sqlI = "INSERT INTO tblImportItem5(tblImportInvoice5id, tblProduct5id, quantity, importPrice) VALUES (?,?,?,?)";
        String sqlP = "UPDATE tblProduct5 SET stockQuantity = stockQuantity + ? WHERE id = ?";
        try {
            con.setAutoCommit(false);
            PreparedStatement psH = con.prepareStatement(sqlH, Statement.RETURN_GENERATED_KEYS);
            psH.setInt(1, hd.getSupplier().getId()); psH.setInt(2, hd.getManager().getId());
            psH.setDate(3, new java.sql.Date(hd.getImportDate().getTime())); psH.setString(4, "Chưa thanh toán");
            psH.executeUpdate();
            ResultSet rs = psH.getGeneratedKeys(); rs.next(); int idH = rs.getInt(1);
            PreparedStatement psI = con.prepareStatement(sqlI, Statement.RETURN_GENERATED_KEYS);
            PreparedStatement psP = con.prepareStatement(sqlP);
            for (ImportItem5 it : hd.getItems()) {
                psI.setInt(1, idH); psI.setInt(2, it.getProduct().getId());
                psI.setInt(3, it.getQuantity()); psI.setFloat(4, it.getImportPrice());
                psI.executeUpdate();
                psP.setInt(1, it.getQuantity()); psP.setInt(2, it.getProduct().getId());
                psP.executeUpdate();
            }
            con.commit(); hd.setId(idH); hd.setPaymentStatus("Chưa thanh toán");
            return true;
        } catch (Exception e) {
            try { con.rollback(); } catch (SQLException ex) { ex.printStackTrace(); }
            e.printStackTrace(); return false;
        } finally {
            try { con.setAutoCommit(true); } catch (SQLException ex) { ex.printStackTrace(); }
        }
    }

    /** Ghi nhận thanh toán cho nhà cung cấp: chuyển trạng thái sang "Đã thanh toán". */
    public boolean thanhToanHoaDonNhap(int idHD) {
        String sql = "UPDATE tblImportInvoice5 SET paymentStatus = 'Đã thanh toán'"
                   + " WHERE id = ? AND paymentStatus <> 'Đã thanh toán'";
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, idHD);
            return ps.executeUpdate() > 0;
        } catch (Exception e) { e.printStackTrace(); }
        return false;
    }
}
