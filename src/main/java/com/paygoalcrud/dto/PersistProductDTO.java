
package com.paygoalcrud.dto;

public class PersistProductDTO {
    private String name;
    private String description;
    private double price;
    private int stock;

    public PersistProductDTO() {
    }

    public PersistProductDTO(String name, String description, double price, int stock) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.stock = stock;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public double getPrice() {
        return price;
    }

    public int getStock() {
        return stock;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    @Override
    public String toString() {
        return "PersistProductDTO{" + "name=" + name + ", description=" + description + ", price=" + price + ", stock=" + stock + '}';
    }
    
}
