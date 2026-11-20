package dao;
import java.sql.*;
import model.*;

public class CustomerDAO5 extends DAO5 {
    public CustomerDAO5() { super(); }

    /** Lưu khách hàng mới: tblAddress5 -> tblMember5 -> tblCustomer5 trong một giao dịch. */
    public boolean dangKyThanhVien(Customer5 c) {
        String sqlA = "INSERT INTO tblAddress5(houseNumber, street, ward, district, city) VALUES (?,?,?,?,?)";
        String sqlM = "INSERT INTO tblMember5(tblAddress5id, username, password, firstName, middleName, lastName,"
                    + " dateOfBirth, gender, email, phone, role, note) VALUES (?,?,?,?,?,?,?,?,?,?,?,?)";
        String sqlC = "INSERT INTO tblCustomer5(tblMember5id, registrationDate) VALUES (?,?)";
        try {
            con.setAutoCommit(false);
            Address5 a = c.getAddress();
            PreparedStatement psA = con.prepareStatement(sqlA, Statement.RETURN_GENERATED_KEYS);
            psA.setString(1, a.getHouseNumber()); psA.setString(2, a.getStreet()); psA.setString(3, a.getWard());
            psA.setString(4, a.getDistrict());    psA.setString(5, a.getCity());
            psA.executeUpdate();
            ResultSet rsA = psA.getGeneratedKeys(); rsA.next(); int idA = rsA.getInt(1);

            FullName5 f = c.getFullName();
            PreparedStatement psM = con.prepareStatement(sqlM, Statement.RETURN_GENERATED_KEYS);
            psM.setInt(1, idA); psM.setString(2, c.getUsername()); psM.setString(3, c.getPassword());
            psM.setString(4, f.getFirstName()); psM.setString(5, f.getMiddleName()); psM.setString(6, f.getLastName());
            psM.setDate(7, new java.sql.Date(c.getDateOfBirth().getTime())); psM.setString(8, c.getGender());
            psM.setString(9, c.getEmail()); psM.setString(10, c.getPhone()); psM.setString(11, "customer");
            psM.setString(12, c.getNote());
            psM.executeUpdate();
            ResultSet rsM = psM.getGeneratedKeys(); rsM.next(); int idM = rsM.getInt(1);

            PreparedStatement psC = con.prepareStatement(sqlC);
            psC.setInt(1, idM); psC.setDate(2, new java.sql.Date(c.getRegistrationDate().getTime()));
            psC.executeUpdate();
            con.commit(); c.setId(idM);
            return true;
        } catch (Exception e) {
            try { con.rollback(); } catch (SQLException ex) { ex.printStackTrace(); }
            e.printStackTrace(); return false;
        } finally {
            try { con.setAutoCommit(true); } catch (SQLException ex) { ex.printStackTrace(); }
        }
    }
}
