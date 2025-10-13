package barr.execution_service.product;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

import barr.execution_service.api.dto.OperationDTO;

import java.util.UUID;

@Component
@RequiredArgsConstructor
public class ProductClient {
    private final RestClient productRestClient;

    // Expose this in Product: GET /api/v1/operations/by-part/{partId}
    public OperationDTO getOperationByPart(UUID partId) {
        return productRestClient.get()
                .uri("/api/parts/{partId}/operation", partId)
                .retrieve()
                .body(OperationDTO.class);
    }
}
