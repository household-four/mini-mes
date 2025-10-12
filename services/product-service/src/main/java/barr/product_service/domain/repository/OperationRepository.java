package barr.product_service.domain.repository;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import barr.product_service.domain.model.Operation;

@Repository
public interface OperationRepository extends CrudRepository<Operation, UUID> {
    Optional<Operation> findByPartId(UUID partId);

    boolean existsByPartId(UUID partId);
}
