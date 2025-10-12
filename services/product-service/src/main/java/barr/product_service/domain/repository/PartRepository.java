package barr.product_service.domain.repository;

import java.util.UUID;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import barr.product_service.domain.model.Part;

@Repository
public interface PartRepository extends CrudRepository<Part, UUID> {

}
