package dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class DeliveryStatus {
    @JsonProperty("orderId")
    private String orderId;
    @JsonProperty("restaurantId")
    private String restaurantId;
    @JsonProperty("riderId")
    private String riderId;
    @JsonProperty("status")
    private String status; // 'ACCEPTED', 'DELIVERED'
    @JsonProperty("currentLatitude")
    private String currentLatitude;
    @JsonProperty("currentLongitude")
    private String currentLongitude;

    @JsonProperty("timestamp")
    private long timestamp;
    @JsonProperty("customerName")
    private String customerName;
    @JsonProperty("customerNumber")
    private String customerNumber;
    @JsonProperty("orderDetails")
    private String orderDetails;

    public String toString() {
        return "{" +
                "\"riderId\":\"" + riderId + "\"," +
                "\"order_id\":\"" + orderId + "\"," +
                "\"restaurant_id\":\"" + restaurantId + "\"," +
                "\"status\":\"" + status + "\"," +
                "\"currentLatitude\":\"" + currentLatitude + "\"," +
                "\"currentLongitude\":\"" + currentLongitude + "\"," +
                "\"customerName\":\"" + customerName + "\"," +
                "\"customerNumber\":\"" + customerNumber + "\"," +
                "\"orderDetails\":\"" + orderDetails + "\"," +
                "\"timestamp\":\"" + timestamp + "\"" +
                "}";
    }
}
