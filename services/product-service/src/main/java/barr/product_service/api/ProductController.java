package barr.product_service.api;

import barr.product_service.domain.model.Part;
import barr.product_service.domain.model.BomItem;
import barr.product_service.domain.model.Operation;
import barr.product_service.domain.repository.PartRepository;
import barr.product_service.domain.repository.BomItemRepository;
import barr.product_service.domain.repository.OperationRepository;
import lombok.RequiredArgsConstructor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/")
@RequiredArgsConstructor
public class ProductController {

    @Autowired
    private final PartRepository partRepository;

    @Autowired
    private final BomItemRepository bomItemRepository;

    @Autowired
    private final OperationRepository operationRepository;

    @GetMapping("/parts")
    public List<Part> getAllParts() {
        return partRepository.findAll();
    }

    @GetMapping("/parts/{id}")
    // @PreAuthorize("hasAnyRole('MES-ADMIN','MES-ENGINEER')")
    public ResponseEntity<Part> getPart(@PathVariable UUID id) {
        return partRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // Get child parts for this parent
    @GetMapping("/parts/{id}/bom")
    // @PreAuthorize("hasAnyRole('MES-ADMIN','MES-ENGINEER')")
    public ResponseEntity<List<Part>> getBomChildren(@PathVariable UUID id) {
        if (!partRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        List<UUID> childIds = bomItemRepository.findByParentPartId(id).stream()
                .map(BomItem::getChildPartId)
                .collect(Collectors.toList());

        List<Part> children = childIds.isEmpty()
                ? Collections.emptyList()
                : partRepository.findAllById(childIds);

        return ResponseEntity.ok(children);
    }

    @GetMapping("/bom")
    // @PreAuthorize("hasAnyRole('MES-ADMIN','MES-ENGINEER')")
    public List<BomItem> getAllBomEdges() {
        return bomItemRepository.findAll();
    }

    @GetMapping("/bom/{parentId}")
    // @PreAuthorize("hasAnyRole('MES-ADMIN','MES-ENGINEER')")
    public List<BomItem> getBomEdgesForParent(@PathVariable UUID parentId) {
        return bomItemRepository.findByParentPartId(parentId);
    }

    // Get the operation for this part (if it exists)
    @GetMapping("/parts/{id}/operation")
    // @PreAuthorize("hasAnyRole('MES-ADMIN','MES-ENGINEER')")
    public ResponseEntity<Optional<Operation>> getPartOperation(@PathVariable UUID id) {
        if (!partRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }

        Optional<Operation> operation = operationRepository.findByPartId(id);
        return ResponseEntity.ok(operation);
    }

    @DeleteMapping("/parts/{id}")
    // @PreAuthorize("hasAnyRole('MES-ADMIN','MES-ENGINEER')")
    public ResponseEntity<Void> deletePart(@PathVariable("id") UUID id) {

        if (!partRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }

        // dont actually delete, just ok
        // partRepository.deleteById(id);

        return ResponseEntity.ok().build();
    }
}
