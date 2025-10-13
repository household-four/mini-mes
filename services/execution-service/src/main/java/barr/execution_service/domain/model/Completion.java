package barr.execution_service.domain.model;

import lombok.*;
import org.springframework.hateoas.RepresentationModel;
import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "completions", indexes = {
        @Index(name = "ix_completions_wo", columnList = "wo_id")
})
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
@JsonInclude(JsonInclude.Include.NON_EMPTY)
@Getter
@Setter
public class Completion extends RepresentationModel<Completion> {

    @Id
    @Column(name = "completion_id", nullable = false, updatable = false)
    private UUID completionId;

    @Column(name = "wo_id", nullable = false)
    private UUID woId;

    @Column(name = "worker_id", nullable = false)
    private UUID workerId;

    @Column(name = "station_id", nullable = false)
    private UUID stationId;

    @Column(name = "completion_time", nullable = false)
    private LocalDateTime completionTime;

    @PrePersist
    void prePersist() {
        if (completionId == null) {
            completionId = UUID.randomUUID();
        }
        if (completionTime == null) {
            completionTime = LocalDateTime.now();
        }
    }
}
