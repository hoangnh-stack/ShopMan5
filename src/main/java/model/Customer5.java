package model;
import java.util.Date;

public class Customer5 extends Member5 {
    private Date registrationDate;
    public Customer5() { super(); }
    public Customer5(Date registrationDate) {
        this.registrationDate = registrationDate;
    }
    public Date getRegistrationDate() { return registrationDate; }
    public void setRegistrationDate(Date registrationDate) { this.registrationDate = registrationDate; }
}
