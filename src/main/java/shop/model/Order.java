package shop.model;

import java.util.Map;

public class Order {
    private long id;
    private String email;
    private String address;
    private Map<Product, Integer> items;

    public Order(long id, String email, String address, Map<Product, Integer> items) {
        this.id = id;
        this.email = email;
        this.address = address;
        this.items = items;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Map<Product, Integer> getItems() {
        return items;
    }

    public void setItems(Map<Product, Integer> items) {
        this.items = items;
    }
}
