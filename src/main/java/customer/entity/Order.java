package customer.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "orders")
public class Order {
    public int getOrder_id() {
        return order_id;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int order_id;

    @Override
    public String toString() {
        return "{" +
                "order_id=" + order_id +
                ", customer=" + customer +
                ", restaurant_id=" + restaurant_id +
                '}';
    }

    public void setOrder_id(int order_id) {
        this.order_id = order_id;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public void setRestaurant_id(int restaurant_id) {
        this.restaurant_id = restaurant_id;
    }

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;

    private int restaurant_id;
}
