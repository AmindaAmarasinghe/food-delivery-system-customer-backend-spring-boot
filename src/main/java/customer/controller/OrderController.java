package customer.controller;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import customer.entity.Order;
import customer.entity.OrderedItem;
import dto.*;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.List;
import customer.entity.Customer;
import customer.service.*;

@RestController
@RequestMapping(value="/api/v1")
public class OrderController {

    private final CustomerService customerService;
    private final MailService mailService;
    private final KafkaSender kafkaSender;
    private final KafkaConsumer kafkaConsumer;

    public OrderController(CustomerService customerService,MailService mailService,KafkaSender kafkaSender,KafkaConsumer kafkaConsumer) {
        this.customerService = customerService;
        this.mailService = mailService;
        this.kafkaSender = kafkaSender;
        this.kafkaConsumer = kafkaConsumer;
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @PostMapping(value="/setOrder")
    public int placeOrder(@RequestBody OrderDto orderDto) {
        System.out.println(orderDto.toString());
        Order order = new Order();
        order.setRestaurant_id(orderDto.getRestaurant_id());
        order.setCustomer(this.customerService.findByCustomerID(orderDto.getCustomer_id()));
        return this.customerService.saveOrder(order).getOrder_id();
        //add entry to order table
    }
    @CrossOrigin(origins = "http://localhost:3000")
    @PostMapping(value="/setOrderedItem")
    public OrderedItem placeOrderedItem(@RequestBody OrderedItemDto orderedItemDto) {
        OrderedItem orderedItem = new OrderedItem();
        orderedItem.setOrder_status(orderedItemDto.getOrder_status());
        orderedItem.setFood_id(orderedItemDto.getFood_id());
        orderedItem.setPayment_status(orderedItemDto.getPayment_status());
        orderedItem.setQuantity(orderedItemDto.getQuantity());
        orderedItem.setTitle(orderedItemDto.getTitle());
        orderedItem.setPrice(orderedItemDto.getPrice());
        orderedItem.setOrder(this.customerService.findByOrderID(orderedItemDto.getOrder_id()));

        return this.customerService.saveOrderedItem(orderedItem);
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @PostMapping(value="/placeOrder")
    public void placeOrder(@RequestBody OrderRequest orderRequest) {
        ProcessedOrderRequest processedOrderRequest = new ProcessedOrderRequest();
        ObjectMapper objectMapper = new ObjectMapper();
        System.out.printf("%s",orderRequest.toString());
        try {

            dto.Customer customer = objectMapper.readValue(String.valueOf(orderRequest.getCustomer()), dto.Customer.class);

            JsonNode jsonArray = objectMapper.readTree(String.valueOf(orderRequest.getOrders().getOrders()));
            List<dto.ProcessedOrderItem> orderList = new ArrayList<>();
            System.out.printf("%s",jsonArray);
            for (JsonNode element : jsonArray) {
                dto.OrderItem order = objectMapper.treeToValue(element, dto.OrderItem.class);
                dto.ProcessedOrderItem processedOrderItem = new ProcessedOrderItem();
                processedOrderItem.setOrder_status(order.getOrder_status());
                processedOrderItem.setPrice(order.getPrice());
                processedOrderItem.setFood_id(order.getFood_id());
                processedOrderItem.setQuantity(order.getQuantity());
                processedOrderItem.setPayment_status(order.getPayment_status());
                processedOrderItem.setTitle(order.getTitle());
                orderList.add(processedOrderItem);
            }

            // Printing the converted list
            for (dto.ProcessedOrderItem order : orderList) {
                //System.out.println(order.getId() + ": " + order.getName());
            }


            processedOrderRequest.setRestaurant_id(orderRequest.getRestaurant_id());
            processedOrderRequest.setCustomer(customer);
            ProcessedOrderWrapper processedOrderWrapper = new ProcessedOrderWrapper();
            processedOrderWrapper.setOrder_id(orderRequest.getOrders().getOrder_id());
            processedOrderWrapper.setItems(orderList);
            processedOrderRequest.setOrders(processedOrderWrapper);

        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println(processedOrderRequest.toString());
        this.kafkaSender.send("newOrder", processedOrderRequest);

    }

    @CrossOrigin(origins = "http://localhost:3000")
    @GetMapping("/getStatus")
    public ResponseEntity<List<dto.DeliveryStatus>> getAllMessages() {
        return ResponseEntity.ok(kafkaConsumer.getMessages());
    }
    @CrossOrigin(origins = "http://localhost:3000")
    @PostMapping("/setFeedback")
    public void giveFeedback(@RequestBody DeliveryCompletion deliveryCompletion) {
        System.out.println(deliveryCompletion.toString());
        kafkaSender.sendFeedback("deliveryCompletion", deliveryCompletion);
    }
}
