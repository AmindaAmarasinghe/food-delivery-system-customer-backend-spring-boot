package dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Data;

import java.util.List;
@Data
public class ProcessedOrderWrapper {
    public String getOrder_id() {
        return order_id;
    }

    public void setOrder_id(String order_id) {
        this.order_id = order_id;
    }

    public List<ProcessedOrderItem> getItems() {
        return items;
    }

    public void setItems(List<ProcessedOrderItem> items) {
        this.items = items;
    }

    @Override
    public String toString() {

        return "{" +
                "\"order_id\":\"" + order_id + '\"' +
                ", \"items\":\"" + items.toString() + '\"' +
                '}';
    }
    @JsonProperty("order_id")
    private String order_id;
    @JsonProperty("items")
    private List<ProcessedOrderItem> items;
}
