package barr.execution_service.domain.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

import java.util.UUID;

import barr.common_domain.WorkstationType;

@Entity
@Table(name = "workstation")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Workstation {
    @Id
    @Column(name = "station_id", nullable = false, updatable = false)
    private UUID stationId;

    @Column(name = "name", nullable = false)
    private String name;

    @Enumerated(EnumType.STRING)
    @Column(name = "type", nullable = false)
    private WorkstationType type;
}