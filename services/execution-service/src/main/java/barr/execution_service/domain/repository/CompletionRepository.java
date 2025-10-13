package barr.execution_service.domain.repository;

import barr.execution_service.domain.model.Completion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface CompletionRepository extends JpaRepository<Completion, UUID> {
    List<Completion> findByWorkerId(UUID workerId);

    List<Completion> findByWoId(UUID woId);
}
