package customer.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.util.JSONPObject;
import customer.entity.Order;
import dto.*;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.ArrayList;
import java.util.List;


@Slf4j
@Service
public class KafkaConsumer {
    private final List<dto.DeliveryStatus> messages = new ArrayList<>();

    @KafkaListener(topics = "orderStatusUpdate", groupId = "group_id",properties = {"spring.json.value.default.type=dto.DeliveryStatus"})
    public void consumeStringPayload(String payload){
        ObjectMapper objectMapper = new ObjectMapper();

        try {

            dto.DeliveryStatus deliveryStatus = objectMapper.readValue(String.valueOf(payload), dto.DeliveryStatus.class);
            System.out.println(deliveryStatus.toString());
            messages.add(deliveryStatus);


        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void consume(ProcessedOrderRequest payload) throws Exception{
//        System.out.println(payload.getOrders().getItems());
//
//
//        for (OrderItem item : payload.getOrders().getItems()) {
//            messages.add(item);
//        }


    }

    public List<dto.DeliveryStatus> getMessages() {
        List<dto.DeliveryStatus> currentMessages = new ArrayList<dto.DeliveryStatus>(messages);
        messages.clear();
        return currentMessages;
    }
}
