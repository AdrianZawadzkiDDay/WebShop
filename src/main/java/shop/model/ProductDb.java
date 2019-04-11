package shop.model;

import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

public class ProductDb {
    private static ProductDb db;
    private static long currentId = 3L;

    private List<Product> products;

    private ProductDb(){
        this.products = new LinkedList<>();
        this.products.add(new Product(0L, LocalDateTime.now(), "Chess", "playing game", 40, "game", 9));
        this.products.add(new Product(1L, LocalDateTime.now(), "Reversi", "logical game", 30, "board game",8));
        this.products.add(new Product(2L, LocalDateTime.now(), "Tablet", "tablet with GPS", 160, "IT", 7));
    }

    public static ProductDb getInstance(){
        if(db == null){
            db = new ProductDb();
        }
        return db;
    }

    public List<Product> getProducts() {return products;}

    public Optional<Product> getProductById(final long id){
        return getProducts().stream()
                .filter(product -> product.getId().equals(id))
                .findFirst();
    }

    public Product getProduct(int index){ return products.get(index);}

    public long addNewProduct(final String name, final String description, final long price, final String category, final int quantity){
        long productId = currentId++;
        Product newProduct = new Product(productId, LocalDateTime.now(), name, description, price, category, quantity);
        this.products.add(newProduct);
        return productId;
    }

}
