package barr.execution_service.api.dto;

import java.util.UUID;

import barr.common_domain.WorkstationType;

public record OperationDTO(
        UUID operationId,
        UUID partId,
        WorkstationType workstationType) {
}
