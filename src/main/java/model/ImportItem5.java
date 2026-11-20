package model;


public class ImportItem5 {
    private int id;
    private int quantity;
    private float importPrice;
    private Product5 product;
    public ImportItem5() {  }
    public ImportItem5(int id, int quantity, float importPrice, Product5 product) {
        this.id = id;
        this.quantity = quantity;
        this.importPrice = importPrice;
        this.product = product;
    }
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public int getQuantity() { return quantity; }
    public void setQuantity(int quantity) { this.quantity = quantity; }
    public float getImportPrice() { return importPrice; }
    public void setImportPrice(float importPrice) { this.importPrice = importPrice; }
    public Product5 getProduct() { return product; }
    public void setProduct(Product5 product) { this.product = product; }
    public float getThanhTien() { return quantity * importPrice; }
}
