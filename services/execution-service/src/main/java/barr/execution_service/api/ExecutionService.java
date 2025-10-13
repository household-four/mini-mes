package barr.execution_service.api;

import barr.common_domain.WorkstationType;
import barr.execution_service.api.dto.OperationDTO;
import barr.execution_service.api.dto.WorkOrderDTO;
import barr.execution_service.domain.model.*;
import barr.execution_service.domain.repository.*;
import barr.execution_service.planning.PlanningClient;
import barr.execution_service.product.ProductClient;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional
public class ExecutionService {

        private final WorkerRepository workerRepository;
        private final CompletionRepository completionRepository;
        private final WorkstationRepository workstationRepository;
        private final PlanningClient planningClient;
        private final ProductClient productClient;

        public Completion completeWorkOrder(UUID woId, UUID workerId) {
                Worker worker = workerRepository.findById(workerId)
                                .orElseThrow(() -> new EntityNotFoundException("Worker not found: " + workerId));

                Workstation workstation = workstationRepository.findById(worker.getStationId())
                                .orElseThrow(() -> new EntityNotFoundException(
                                                "Workstation not found: " + worker.getStationId()));

                WorkOrderDTO wo = planningClient.getWorkOrder(woId);
                if (wo == null) {
                        throw new EntityNotFoundException("Work order not found: " + woId);
                }

                OperationDTO op = productClient.getOperationByPart(wo.partId());
                if (op == null) {
                        throw new EntityNotFoundException("Operation not found for part: " + wo.partId());
                }

                WorkstationType requiredType = op.workstationType();
                if (!workstation.getType().equals(requiredType)) {
                        throw new IllegalStateException(
                                        "Workstation type " + workstation.getType() + " does not match required "
                                                        + requiredType);
                }

                // todo: check if all predecessors are completed

                Completion completion = Completion.builder()
                                .woId(woId)
                                .workerId(workerId)
                                .stationId(worker.getStationId())
                                .build();
                Completion saved = completionRepository.save(completion);

                planningClient.closeWorkOrder(woId);

                return saved;
        }

        public List<Completion> getAllCompletions() {
                return completionRepository.findAll();
        }
}
