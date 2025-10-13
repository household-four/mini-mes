package barr.execution_service.domain.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import barr.execution_service.domain.model.Workstation;

@Repository
public interface WorkstationRepository extends JpaRepository<Workstation, UUID> {

}
