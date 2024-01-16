
package dto;
import com.fasterxml.jackson.annotation.JsonProperty;

public class ProcessedOrderItem {
    @JsonProperty("food_id")
    String food_id;

    public String getFood_id() {
        return food_id;
    }

    public void setFood_id(String food_id) {
        this.food_id = food_id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public String toString() {
        return "{" +
                "\"food_id\":\"" + food_id + '\"' +
                ", \"title\":\"" + title + '\"' +
                ", \"price\":\"" + price + '\"' +
                ", \"quantity\":\"" + quantity +
                "\", \"order_status\":\"" + order_status + '\"' +
                ", \"payment_status\":\"" + payment_status + '\"' +
                '}';
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getOrder_status() {
        return order_status;
    }

    public void setOrder_status(String order_status) {
        this.order_status = order_status;
    }

    public String getPayment_status() {
        return payment_status;
    }

    public void setPayment_status(String payment_status) {
        this.payment_status = payment_status;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @JsonProperty("title")
    String title;
    @JsonProperty("price")
    String price;
    @JsonProperty("quantity")
    int quantity;


    @JsonProperty("order_status")
    String order_status;
    @JsonProperty("payment_status")
    String payment_status;
}
