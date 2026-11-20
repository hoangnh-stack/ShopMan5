package model;
import java.util.Date;

public class Member5 {
    private int id;
    private String username;
    private String password;
    private Date dateOfBirth;
    private String gender;
    private String email;
    private String phone;
    private String role;
    private String note;
    private FullName5 fullName;
    private Address5 address;
    public Member5() {  }
    public Member5(int id, String username, String password, Date dateOfBirth, String gender, String email, String phone, String role, String note, FullName5 fullName, Address5 address) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.dateOfBirth = dateOfBirth;
        this.gender = gender;
        this.email = email;
        this.phone = phone;
        this.role = role;
        this.note = note;
        this.fullName = fullName;
        this.address = address;
    }
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }
    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }
    public Date getDateOfBirth() { return dateOfBirth; }
    public void setDateOfBirth(Date dateOfBirth) { this.dateOfBirth = dateOfBirth; }
    public String getGender() { return gender; }
    public void setGender(String gender) { this.gender = gender; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public String getPhone() { return phone; }
    public void setPhone(String phone) { this.phone = phone; }
    public String getRole() { return role; }
    public void setRole(String role) { this.role = role; }
    public String getNote() { return note; }
    public void setNote(String note) { this.note = note; }
    public FullName5 getFullName() { return fullName; }
    public void setFullName(FullName5 fullName) { this.fullName = fullName; }
    public Address5 getAddress() { return address; }
    public void setAddress(Address5 address) { this.address = address; }
}
