package barr.execution_service.api;

import lombok.RequiredArgsConstructor;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import barr.execution_service.api.dto.CloseWorkOrderRequestDTO;
import barr.execution_service.domain.model.Completion;

import java.util.*;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class ExecutionController {

    private final ExecutionService executionService;

    @PostMapping("/execution/complete")
    public ResponseEntity<Completion> completeWorkOrder(
            @Validated @RequestBody CloseWorkOrderRequestDTO request) {
        Completion completion = executionService.completeWorkOrder(request.workOrderId(), request.workerId());
        return ResponseEntity.ok(completion);
    }

    @GetMapping("/execution/completions")
    public ResponseEntity<List<Completion>> getCompletions() {
        List<Completion> completions = executionService.getAllCompletions();
        return ResponseEntity.ok(completions);
    }

}
