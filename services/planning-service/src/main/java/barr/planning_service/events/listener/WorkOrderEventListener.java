package barr.planning_service.events.listener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class WorkOrderEventListener {

    private static final Logger log = LoggerFactory.getLogger(WorkOrderEventListener.class);

    @KafkaListener(topics = "work-orders", groupId = "planning-service")
    public void handleWorkOrderEvent(String message) {
        log.info("Planning service Received Kafka event on 'work-orders': {}", message);
    }
}
