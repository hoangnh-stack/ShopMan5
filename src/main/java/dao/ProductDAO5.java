package dao;
import java.sql.*;
import java.util.ArrayList;
import model.Product5;

public class ProductDAO5 extends DAO5 {
    public ProductDAO5() { super(); }

    /** Tìm mặt hàng có tên chứa từ khóa. */
    public ArrayList<Product5> timHangTheoTen(String ten) {
        ArrayList<Product5> ds = new ArrayList<>();
        String sql = "SELECT id, name, category, unit, salePrice, stockQuantity, description, status"
                   + " FROM tblProduct5 WHERE name LIKE ?";
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, "%" + ten + "%");
            ResultSet rs = ps.executeQuery();
            while (rs.next())
                ds.add(new Product5(rs.getInt("id"), rs.getString("name"), rs.getString("category"),
                        rs.getString("unit"), rs.getFloat("salePrice"), rs.getInt("stockQuantity"),
                        rs.getString("description"), rs.getString("status")));
        } catch (Exception e) { e.printStackTrace(); }
        return ds;
    }

    /** Thêm mặt hàng mới. */
    public boolean themHang(Product5 p) {
        String sql = "INSERT INTO tblProduct5(name, category, unit, salePrice, stockQuantity, description, status)"
                   + " VALUES (?,?,?,?,?,?,?)";
        try (PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, p.getName()); ps.setString(2, p.getCategory()); ps.setString(3, p.getUnit());
            ps.setFloat(4, p.getSalePrice()); ps.setInt(5, 0);
            ps.setString(6, p.getDescription()); ps.setString(7, "Đang kinh doanh");
            ps.executeUpdate();
            ResultSet rs = ps.getGeneratedKeys(); if (rs.next()) p.setId(rs.getInt(1));
            return true;
        } catch (Exception e) { e.printStackTrace(); }
        return false;
    }
}
