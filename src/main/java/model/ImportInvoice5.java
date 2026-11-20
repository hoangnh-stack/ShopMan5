package model;
import java.util.Date;

public class ImportInvoice5 {
    private int id;
    private Date importDate;
    private float totalAmount;
    private String paymentStatus;
    private Manager5 manager;
    private Supplier5 supplier;
    private ImportItem5[] items;
    public ImportInvoice5() {  }
    public ImportInvoice5(int id, Date importDate, float totalAmount, String paymentStatus, Manager5 manager, Supplier5 supplier, ImportItem5[] items) {
        this.id = id;
        this.importDate = importDate;
        this.totalAmount = totalAmount;
        this.paymentStatus = paymentStatus;
        this.manager = manager;
        this.supplier = supplier;
        this.items = items;
    }
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public Date getImportDate() { return importDate; }
    public void setImportDate(Date importDate) { this.importDate = importDate; }
    public float getTotalAmount() { return totalAmount; }
    public void setTotalAmount(float totalAmount) { this.totalAmount = totalAmount; }
    public String getPaymentStatus() { return paymentStatus; }
    public void setPaymentStatus(String paymentStatus) { this.paymentStatus = paymentStatus; }
    public Manager5 getManager() { return manager; }
    public void setManager(Manager5 manager) { this.manager = manager; }
    public Supplier5 getSupplier() { return supplier; }
    public void setSupplier(Supplier5 supplier) { this.supplier = supplier; }
    public ImportItem5[] getItems() { return items; }
    public void setItems(ImportItem5[] items) { this.items = items; }
    public float getTongTien() {
        float t = 0;
        if (items != null) for (ImportItem5 it : items) t += it.getThanhTien();
        return t;
    }
}
