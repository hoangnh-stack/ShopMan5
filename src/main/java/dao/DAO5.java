package dao;
import java.sql.Connection;
import java.sql.DriverManager;

public class DAO5 {
    protected static Connection con;          // kết nối dùng chung cho các lớp DAO (-con : Connection)

    public DAO5() {                           // mở kết nối JDBC tới MySQL nếu chưa có
        if (con == null) {
            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
                con = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/shopman5?useUnicode=true&characterEncoding=UTF-8",
                    "root", "123456");
            } catch (Exception e) { e.printStackTrace(); }
        }
    }
}
