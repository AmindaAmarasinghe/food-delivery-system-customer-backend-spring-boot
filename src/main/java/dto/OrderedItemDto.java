package dto;

import customer.entity.Order;
import lombok.Data;

@Data
public class OrderedItemDto {

    private int order_id;
    private int food_id;
    private String price;
    private String title;
    private String quantity;
    private String payment_status;
    private String order_status;

    public int getOrder_id() {
        return order_id;
    }

    public int getFood_id() {
        return food_id;
    }

    public String getPrice() {
        return price;
    }

    public String getTitle() {
        return title;
    }

    public String getQuantity() {
        return quantity;
    }

    public String getPayment_status() {
        return payment_status;
    }

    public String getOrder_status() {
        return order_status;
    }

}
