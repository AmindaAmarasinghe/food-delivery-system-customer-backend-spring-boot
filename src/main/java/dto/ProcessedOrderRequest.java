package dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class ProcessedOrderRequest {

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

    public ProcessedOrderWrapper getOrders() {
        return orders;
    }

    public void setOrders(ProcessedOrderWrapper orders) {
        this.orders = orders;
    }

    @JsonProperty("restaurant_id")
    private String restaurant_id;
    @JsonProperty("customer")
    private Customer customer;
    @JsonProperty("orders")
    private ProcessedOrderWrapper orders;
    @JsonProperty("timestamp")
    private long timestamp;
    @Override
    public String toString() {
        return "{" +
                " \"restaurant_id\":\"" + restaurant_id +
                "\", \"customer\":" + customer.toString() +
                ", \"orders\":" + orders.toString() +
                ", \"timestamp\":" + timestamp +
                '}';
    }
}
