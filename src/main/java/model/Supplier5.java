package model;


public class Supplier5 {
    private int id;
    private String name;
    private String phone;
    private String email;
    private String note;
    private Address5 address;
    public Supplier5() {  }
    public Supplier5(int id, String name, String phone, String email, String note, Address5 address) {
        this.id = id;
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.note = note;
        this.address = address;
    }
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getPhone() { return phone; }
    public void setPhone(String phone) { this.phone = phone; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public String getNote() { return note; }
    public void setNote(String note) { this.note = note; }
    public Address5 getAddress() { return address; }
    public void setAddress(Address5 address) { this.address = address; }
}
