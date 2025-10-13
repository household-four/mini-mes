package barr.execution_service.api.dto;

import java.util.UUID;

import barr.common_domain.WorkOrderStatus;

public record WorkOrderDTO(
        UUID woId,
        UUID partId,
        WorkOrderStatus status) {
}
