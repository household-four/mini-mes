package barr.product_service.domain.model;

import lombok.*;
import org.springframework.hateoas.RepresentationModel;

import jakarta.persistence.*;
import java.io.Serializable;
import java.util.UUID;

@Entity
@Table(name = "bom_items")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
@IdClass(BomItem.BomItemId.class)
public class BomItem extends RepresentationModel<BomItem> {

    @Id
    @Column(name = "parent_part_id", nullable = false)
    private UUID parentPartId;

    @Id
    @Column(name = "child_part_id", nullable = false)
    private UUID childPartId;

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class BomItemId implements Serializable {
        private static final long serialVersionUID = 1L;

        private UUID parentPartId;
        private UUID childPartId;
    }
}