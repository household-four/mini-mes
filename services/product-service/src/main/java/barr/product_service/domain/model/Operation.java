package barr.product_service.domain.model;

import lombok.*;
import org.springframework.hateoas.RepresentationModel;

import com.fasterxml.jackson.annotation.JsonInclude;

import jakarta.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "operations")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class Operation extends RepresentationModel<Operation> {

    @Id
    @Column(name = "operation_id", nullable = false, updatable = false)
    private UUID operationId;

    @Column(name = "part_id", nullable = false)
    private UUID partId;

    @Enumerated(EnumType.STRING)
    @Column(name = "workstation_type", nullable = false, length = 64)
    private WorkStationType workstationType;

    @PrePersist
    void prePersist() {
        if (operationId == null) {
            operationId = UUID.randomUUID();
        }
    }
}