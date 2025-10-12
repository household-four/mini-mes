package barr.planning_service.domain.model;

import lombok.*;
import org.springframework.hateoas.RepresentationModel;
import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.*;
import java.util.UUID;

import barr.common_domain.WorkOrderStatus;

@Entity
@Table(name = "work_orders")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class WorkOrder extends RepresentationModel<WorkOrder> {

    @Id
    @Column(name = "wo_id", nullable = false, updatable = false)
    private UUID woId;

    @Column(name = "part_id", nullable = false)
    private UUID partId;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false, length = 32)
    private WorkOrderStatus status;

    @Column(name = "parent_wo_id")
    private UUID parentWoId;

    @PrePersist
    void prePersist() {
        if (woId == null) {
            woId = UUID.randomUUID();
        }

        if (status == null) {
            status = WorkOrderStatus.OPEN;
        }
    }
}
