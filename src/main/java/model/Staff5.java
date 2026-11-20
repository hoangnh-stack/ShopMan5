package model;


public class Staff5 extends Member5 {
    private String position;
    public Staff5() { super(); }
    public Staff5(String position) {
        this.position = position;
    }
    public String getPosition() { return position; }
    public void setPosition(String position) { this.position = position; }
}
