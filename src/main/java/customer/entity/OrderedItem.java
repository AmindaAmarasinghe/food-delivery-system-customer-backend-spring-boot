package customer.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "ordered_item")
public class OrderedItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int orderedItemId;

    @ManyToOne
    @JoinColumn(name = "order_id")
    private Order order;

    private int food_id;
    private String price;

    @Override
    public String toString() {
        return "OrderedItem{" +
                "orderedItemId=" + orderedItemId +
                ", order=" + order.toString() +
                ", food_id=" + food_id +
                ", price='" + price + '\'' +
                ", title='" + title + '\'' +
                ", quantity='" + quantity + '\'' +
                ", payment_status='" + payment_status + '\'' +
                ", order_status='" + order_status + '\'' +
                '}';
    }

    private String title;
    private String quantity;
    private String payment_status;
    private String order_status;

    public void setOrderedItemId(int orderedItemId) {
        this.orderedItemId = orderedItemId;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public void setFood_id(int food_id) {
        this.food_id = food_id;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public void setPayment_status(String payment_status) {
        this.payment_status = payment_status;
    }

    public void setOrder_status(String order_status) {
        this.order_status = order_status;
    }
}
