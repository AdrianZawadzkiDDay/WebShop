package shop.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class OrderDb {
    private List<Order> orders;
    private  static OrderDb instance;
    private static long currentId = 0;


    private OrderDb(){
        this.orders = new ArrayList<>();
    }

    public static OrderDb getInstance(){
        if(instance == null){
            instance = new OrderDb();
        }
        return instance;
    }

    public List<Order> getProducts() { return orders; }

    public long addNewOrder(final Map<Product, Integer> cart, final String address, final String email){
        final Order order = new Order(currentId, email, address, cart);
        this.orders.add(order);

        return  currentId++;
    }

    public List<Order> getOrdersForUserId(final String  email){
        return orders.stream().filter(i -> i.getEmail().equalsIgnoreCase(email))
                .collect(Collectors.toList());
    }
}
