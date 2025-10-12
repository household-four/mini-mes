package barr.product_service.domain.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import barr.product_service.domain.model.Part;

@Repository
public interface PartRepository extends JpaRepository<Part, UUID> {

}
