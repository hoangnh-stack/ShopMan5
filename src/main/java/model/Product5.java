package model;


public class Product5 {
    private int id;
    private String name;
    private String category;
    private String unit;
    private float salePrice;
    private int stockQuantity;
    private String description;
    private String status;
    public Product5() {  }
    public Product5(int id, String name, String category, String unit, float salePrice, int stockQuantity, String description, String status) {
        this.id = id;
        this.name = name;
        this.category = category;
        this.unit = unit;
        this.salePrice = salePrice;
        this.stockQuantity = stockQuantity;
        this.description = description;
        this.status = status;
    }
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getCategory() { return category; }
    public void setCategory(String category) { this.category = category; }
    public String getUnit() { return unit; }
    public void setUnit(String unit) { this.unit = unit; }
    public float getSalePrice() { return salePrice; }
    public void setSalePrice(float salePrice) { this.salePrice = salePrice; }
    public int getStockQuantity() { return stockQuantity; }
    public void setStockQuantity(int stockQuantity) { this.stockQuantity = stockQuantity; }
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
}
