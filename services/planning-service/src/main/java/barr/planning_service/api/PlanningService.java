package barr.planning_service.api;

import barr.common_domain.WorkOrderStatus;
import barr.planning_service.domain.model.*;
import barr.planning_service.domain.repository.WorkOrderRepository;
import lombok.RequiredArgsConstructor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional
public class PlanningService {

    @Autowired
    private final WorkOrderRepository workOrderRepository;

    public WorkOrder createWorkOrder(UUID partId, UUID parentWoId) {
        WorkOrder wo = WorkOrder.builder()
                .partId(partId)
                .parentWoId(parentWoId)
                .status(WorkOrderStatus.OPEN)
                .build();
        return workOrderRepository.save(wo);
    }

    public List<WorkOrder> getOpenOrders() {
        return workOrderRepository.findByStatus(WorkOrderStatus.OPEN);
    }

    // public Optional<WorkOrder> claimOrder(UUID woId) {
    // return workOrderRepository.findById(woId).map(wo -> {
    // wo.setStatus(WorkOrderStatus.IN_PROGRESS);
    // return workOrderRepository.save(wo);
    // });
    // }

    public Optional<WorkOrder> closeOrder(UUID woId) {
        return workOrderRepository.findById(woId).map(wo -> {
            wo.setStatus(WorkOrderStatus.CLOSED);
            return workOrderRepository.save(wo);
        });
    }
}
