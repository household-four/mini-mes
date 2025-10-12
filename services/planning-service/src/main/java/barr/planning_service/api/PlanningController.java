package barr.planning_service.api;

import barr.planning_service.domain.model.WorkOrder;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class PlanningController {

    private final PlanningService planningService;

    @PostMapping("/work-orders")
    public ResponseEntity<WorkOrder> createWorkOrder(
            @RequestParam UUID partId,
            @RequestParam(required = false) UUID parentWoId) {
        return ResponseEntity.ok(planningService.createWorkOrder(partId, parentWoId));
    }

    @GetMapping("/work-orders/open")
    public ResponseEntity<List<WorkOrder>> getOpenOrders() {
        return ResponseEntity.ok(planningService.getOpenOrders());
    }

    // @PostMapping("/work-orders/{woId}/claim")
    // public ResponseEntity<Optional<WorkOrder>> claimWorkOrder(@PathVariable UUID
    // woId) {
    // return ResponseEntity.ok(planningService.claimOrder(woId));
    // }

    @PostMapping("/work-orders/{woId}/close")
    public ResponseEntity<Optional<WorkOrder>> closeWorkOrder(@PathVariable UUID woId) {
        return ResponseEntity.ok(planningService.closeOrder(woId));
    }
}
