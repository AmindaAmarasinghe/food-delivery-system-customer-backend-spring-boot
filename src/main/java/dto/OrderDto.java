package dto;

import customer.entity.Customer;
import jakarta.persistence.*;
import lombok.Data;

@Data
public class OrderDto {

    public int getCustomer_id() {
        return customer_id;
    }

    public int getRestaurant_id() {
        return restaurant_id;
    }

    private int customer_id;

    private int restaurant_id;

    @Override
    public String toString() {
        return "OrderDto{" +
                "customer_id=" + customer_id +
                ", restaurant_id=" + restaurant_id +
                '}';
    }
}
