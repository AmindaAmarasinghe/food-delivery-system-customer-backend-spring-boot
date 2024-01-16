package customer.service;

import dto.DeliveryCompletion;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class KafkaSender {

    private static final Logger logger = LoggerFactory.getLogger(KafkaSender.class);
    @Autowired
    private KafkaTemplate<String, Object> kafkaTemplate;
    @Autowired
    private KafkaTemplate<String, DeliveryCompletion> kafkaTemplateNew;

    public void send(String kafkaTopic, Object message) {
        System.out.printf("kafka sending %s\n", message);
        this.kafkaTemplate.send(kafkaTopic, message);
    }
    public void sendFeedback(String kafkaTopic, DeliveryCompletion message) {
        System.out.printf("kafka sending %s\n", message);
        this.kafkaTemplateNew.send(kafkaTopic, message);
    }
}