package barr.execution_service.events.source;

import java.util.concurrent.ExecutionException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class WorkOrderEventProducer {
    private static final String TOPIC = "work-orders";

    private final KafkaTemplate<String, String> kafkaTemplate;
    private static final Logger log = LoggerFactory.getLogger(WorkOrderEventProducer.class);

    public WorkOrderEventProducer(KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendTestEvent(String payload) {
        log.info("➡️ Sending Kafka event: '{}'' to topic '{}'", payload, TOPIC);
        kafkaTemplate.send(TOPIC, payload);
    }
}
