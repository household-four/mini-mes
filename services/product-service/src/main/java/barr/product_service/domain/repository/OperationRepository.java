package barr.product_service.domain.repository;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import barr.product_service.domain.model.Operation;

@Repository
public interface OperationRepository extends JpaRepository<Operation, UUID> {
    Optional<Operation> findByPartId(UUID partId);

    boolean existsByPartId(UUID partId);
}
