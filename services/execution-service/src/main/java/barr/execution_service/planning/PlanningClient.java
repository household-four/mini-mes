package barr.execution_service.planning;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

import barr.execution_service.api.dto.WorkOrderDTO;

import java.util.UUID;

@Component
@RequiredArgsConstructor
public class PlanningClient {
    private final RestClient planningRestClient;

    @Value("http://planning-service:8082")
    private String baseUrl;

    public WorkOrderDTO getWorkOrder(UUID woId) {
        return planningRestClient.get()
                .uri("/api/work-orders/{woId}", woId)
                .retrieve()
                .body(WorkOrderDTO.class);
    }

    public void closeWorkOrder(UUID woId) {
        planningRestClient.post()
                .uri("/api/work-orders/{woId}/close", woId)
                .retrieve()
                .toBodilessEntity();
    }
}
