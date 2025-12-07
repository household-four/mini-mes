package barr.execution_service.api;

import lombok.RequiredArgsConstructor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import barr.execution_service.api.dto.CloseWorkOrderRequestDTO;
import barr.execution_service.domain.model.Completion;
import barr.execution_service.events.source.WorkOrderEventProducer;

import java.util.*;

@RestController
@RequestMapping("/")
public class ExecutionController {

    private final ExecutionService executionService;

    private final WorkOrderEventProducer producer;

    public ExecutionController(ExecutionService executionService,
            WorkOrderEventProducer producer) {
        this.executionService = executionService;
        this.producer = producer;
    }

    @PostMapping("/execution/complete")
    public ResponseEntity<Completion> completeWorkOrder(
            @Validated @RequestBody CloseWorkOrderRequestDTO request) {
        producer.sendTestEvent(
                "Execution Service recieved work order completion request for work order ID: " + request.workOrderId());
        Completion completion = executionService.completeWorkOrder(request.workOrderId(), request.workerId());
        return ResponseEntity.ok(completion);
    }

    @GetMapping("/execution/completions")
    public ResponseEntity<List<Completion>> getCompletions() {
        List<Completion> completions = executionService.getAllCompletions();
        return ResponseEntity.ok(completions);
    }

}
