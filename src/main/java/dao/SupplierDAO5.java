package dao;
import java.sql.*;
import java.util.ArrayList;
import model.*;

public class SupplierDAO5 extends DAO5 {
    public SupplierDAO5() { super(); }

    /** Tìm nhà cung cấp có tên chứa từ khóa. */
    public ArrayList<Supplier5> timNCCTheoTen(String ten) {
        ArrayList<Supplier5> ds = new ArrayList<>();
        String sql = "SELECT s.id, s.name, s.phone, s.email, s.note, a.id AS aid, a.houseNumber, a.street,"
                   + " a.ward, a.district, a.city FROM tblSupplier5 s JOIN tblAddress5 a ON s.tblAddress5id = a.id"
                   + " WHERE s.name LIKE ?";
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, "%" + ten + "%");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Address5 a = new Address5(rs.getInt("aid"), rs.getString("houseNumber"), rs.getString("street"),
                                          rs.getString("ward"), rs.getString("district"), rs.getString("city"));
                ds.add(new Supplier5(rs.getInt("id"), rs.getString("name"), rs.getString("phone"),
                                     rs.getString("email"), rs.getString("note"), a));
            }
        } catch (Exception e) { e.printStackTrace(); }
        return ds;
    }

    /** Thêm nhà cung cấp mới (địa chỉ + nhà cung cấp trong một giao dịch). */
    public boolean themNCC(Supplier5 s) {
        String sqlA = "INSERT INTO tblAddress5(houseNumber, street, ward, district, city) VALUES (?,?,?,?,?)";
        String sqlS = "INSERT INTO tblSupplier5(tblAddress5id, name, phone, email, note) VALUES (?,?,?,?,?)";
        try {
            con.setAutoCommit(false);
            Address5 a = s.getAddress();
            PreparedStatement psA = con.prepareStatement(sqlA, Statement.RETURN_GENERATED_KEYS);
            psA.setString(1, a.getHouseNumber()); psA.setString(2, a.getStreet()); psA.setString(3, a.getWard());
            psA.setString(4, a.getDistrict());    psA.setString(5, a.getCity());
            psA.executeUpdate();
            ResultSet rs = psA.getGeneratedKeys(); rs.next(); int idA = rs.getInt(1);
            PreparedStatement psS = con.prepareStatement(sqlS, Statement.RETURN_GENERATED_KEYS);
            psS.setInt(1, idA); psS.setString(2, s.getName()); psS.setString(3, s.getPhone());
            psS.setString(4, s.getEmail()); psS.setString(5, s.getNote());
            psS.executeUpdate();
            ResultSet rs2 = psS.getGeneratedKeys(); rs2.next(); s.setId(rs2.getInt(1));
            con.commit(); return true;
        } catch (Exception e) {
            try { con.rollback(); } catch (SQLException ex) { ex.printStackTrace(); }
            e.printStackTrace(); return false;
        } finally {
            try { con.setAutoCommit(true); } catch (SQLException ex) { ex.printStackTrace(); }
        }
    }
}
