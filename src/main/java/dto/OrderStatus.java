package dto;

import lombok.Data;
import lombok.Getter;
import lombok.ToString;

import java.util.List;

public class OrderStatus {


    private String restaurant_id;

    private Customer customer;

    private Order orders;

    @Data
    @ToString
    public static class Customer {
        private String id;
        private String name;
        private String longitude;
        private String latitude;
        private String contact;
    }

    @Data
    @ToString
    public static class Order {
        @Getter
        private String order_id;
        private List<Item> items;

        @Data
        @ToString
        public static class Item {
            private String food_id;
            private String title;
            private String price;
            private int quantity;
            private String order_status;
            private String payment_status;
        }
    }

    private long timestamp;
}
