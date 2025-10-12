package barr.product_service.domain.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import barr.product_service.domain.model.BomItem;

@Repository
public interface BomItemRepository extends JpaRepository<BomItem, BomItem.BomItemId> {
    List<BomItem> findByParentPartId(UUID parentPartId);

    List<BomItem> findByChildPartId(UUID childPartId);

    boolean existsByParentPartIdAndChildPartId(UUID parentPartId, UUID childPartId);
}
