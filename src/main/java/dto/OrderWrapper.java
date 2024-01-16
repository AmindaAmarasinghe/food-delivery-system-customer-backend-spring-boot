package dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class OrderWrapper {
    public String getOrder_id() {
        return order_id;
    }

    public void setOrder_id(String order_id) {
        this.order_id = order_id;
    }

    public String getOrders() {
        return items;
    }

    public void setOrders(String orders) {
        this.items = orders;
    }

    @Override
    public String toString() {
        return "{" +
                "\"order_id\":\"" + order_id +
                "\", \"items\":\"" + items.toString() + '\"' +
                '}';
    }

    @JsonProperty("order_id")
    private String order_id;
    @JsonProperty("items")
    private String items;
}
