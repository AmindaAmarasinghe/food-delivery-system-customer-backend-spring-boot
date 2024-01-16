package dto;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class OrderRequest {

    private String restaurant_id;

    public String getRestaurant_id() {
        return restaurant_id;
    }

    public void setRestaurant_id(String restaurant_id) {
        this.restaurant_id = restaurant_id;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public OrderWrapper getOrders() {
        return orders;
    }

    public void setOrders(OrderWrapper orders) {
        this.orders = orders;
    }

    private Customer customer;
    private OrderWrapper orders;
    private long timestamp;
    @Override
    public String toString() {
        return "{" +
                "\"restaurant_id\":\"" + restaurant_id +
                "\", \"customer\":" + customer.toString() +
                ", \"orders\":" + orders.toString() +
                ", \"timestamp\":" + timestamp +
                '}';
    }
}
