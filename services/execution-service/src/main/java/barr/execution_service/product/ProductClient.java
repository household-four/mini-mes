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

    public OperationDTO getOperationByPart(UUID partId) {
        return productRestClient.get()
                .uri("/parts/{partId}/operation", partId)
                .retrieve()
                .body(OperationDTO.class);
    }
}
