package barr.product_service.domain.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import barr.product_service.domain.model.BomItem;

@Repository
public interface BomItemRepository extends CrudRepository<BomItem, BomItem.BomItemId> {
    List<BomItem> findByParentPartId(UUID parentPartId);

    List<BomItem> findByChildPartId(UUID childPartId);

    boolean existsByParentPartIdAndChildPartId(UUID parentPartId, UUID childPartId);
}
