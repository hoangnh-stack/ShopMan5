package view;

import java.text.SimpleDateFormat;
import java.util.Date;
import model.*;

/** Hàm tiện ích dùng chung cho các lớp giao diện (kiểm tra và tách dữ liệu nhập). */
public class Util5 {
    public static String t(String s) { return s == null ? "" : s.trim(); }

    public static boolean phoneOk(String s) { return s.matches("^0\\d{9,10}$"); }

    public static boolean emailOk(String s) { return s.matches("^[\\w.+-]+@[\\w-]+(\\.[\\w-]+)+$"); }

    public static Date parseDate(String s) {
        try {
            SimpleDateFormat f = new SimpleDateFormat("dd/MM/yyyy");
            f.setLenient(false);
            return f.parse(s);
        } catch (Exception e) { return null; }
    }

    /** "Nguyễn Văn An" -> họ = Nguyễn, tên đệm = Văn, tên = An. */
    public static FullName5 tachHoTen(String hoTen) {
        String[] p = t(hoTen).split("\\s+");
        FullName5 f = new FullName5();
        if (p.length == 1) { f.setFirstName(""); f.setMiddleName(""); f.setLastName(p[0]); return f; }
        f.setFirstName(p[0]); f.setLastName(p[p.length - 1]);
        StringBuilder sb = new StringBuilder();
        for (int i = 1; i < p.length - 1; i++) sb.append(i > 1 ? " " : "").append(p[i]);
        f.setMiddleName(sb.toString());
        return f;
    }

    /** "15 Trần Phú, Mộ Lao, Hà Đông, Hà Nội" -> số nhà, đường, phường, quận, thành phố. */
    public static Address5 tachDiaChi(String diaChi) {
        String[] p = t(diaChi).split("\\s*,\\s*");
        Address5 a = new Address5();
        String dau = p[0];
        int sp = dau.indexOf(' ');
        if (sp > 0 && Character.isDigit(dau.charAt(0))) { a.setHouseNumber(dau.substring(0, sp)); a.setStreet(dau.substring(sp + 1)); }
        else { a.setHouseNumber(""); a.setStreet(dau); }
        a.setWard(p.length > 1 ? p[1] : ""); a.setDistrict(p.length > 2 ? p[2] : ""); a.setCity(p.length > 3 ? p[3] : "");
        return a;
    }
}
