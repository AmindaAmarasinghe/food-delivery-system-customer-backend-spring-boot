package dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class DeliveryCompletion {
    @JsonProperty("orderID")
    private String orderID;
    @JsonProperty("riderID")
    private String riderID;
    @JsonProperty("delivered")
    private boolean delivered;
    @JsonProperty("feedback")
    private String feedback;
    @JsonProperty("stars")
    private int stars;

    public String toString() {
        return "{" +
                "riderID:\"" + riderID + "\"," +
                "orderID:\"" + orderID + "\"," +
                "delivered:\"" + delivered + "\"," +
                "feedback:\"" + feedback + "\"," +
                "stars:\"" + stars + "\"" +
                "}";
    }
}