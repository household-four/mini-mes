package barr.execution_service.domain.model;

import lombok.*;
import org.springframework.hateoas.RepresentationModel;
import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "workers")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class Worker extends RepresentationModel<Worker> {

    @Id
    @Column(name = "worker_id", nullable = false, updatable = false)
    private UUID workerId;

    @Column(name = "name", nullable = false, length = 128)
    private String name;

    @Column(name = "station_id", nullable = false)
    private UUID stationId;

    @PrePersist
    void prePersist() {
        if (workerId == null) {
            workerId = UUID.randomUUID();
        }
    }
}
