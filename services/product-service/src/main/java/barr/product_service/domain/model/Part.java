package barr.product_service.domain.model;

import lombok.*;
import org.springframework.hateoas.RepresentationModel;

import jakarta.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "parts")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class Part extends RepresentationModel<Part> {

    @Id
    @Column(name = "part_id", nullable = false, updatable = false)
    private UUID partId;

    @Column(nullable = false)
    private String description;

    @Enumerated(EnumType.STRING)
    @Column(name = "part_type", nullable = false, length = 32)
    private PartType partType;

    @PrePersist
    void prePersist() {
        if (partId == null) {
            partId = UUID.randomUUID();
        }
    }
}