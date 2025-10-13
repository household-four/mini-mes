package barr.execution_service.api.dto;

import java.util.UUID;

public record CloseWorkOrderRequestDTO(UUID workerId, UUID workOrderId) {
}
