package dao;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class MemberDAO5 extends DAO5 {
    public MemberDAO5() { super(); }

    /** Kiểm tra tên đăng nhập đã tồn tại chưa: true = đã tồn tại. */
    public boolean kiemTraTonTaiUsername(String username) {
        String sql = "SELECT id FROM tblMember5 WHERE username = ?";
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, username);
            ResultSet rs = ps.executeQuery();
            return rs.next();
        } catch (Exception e) { e.printStackTrace(); }
        return true;   // lỗi truy vấn: coi như không cho đăng kí
    }
}
