package TrialExam.repository;

import TrialExam.model.Duck;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestParam;

@Repository
public interface DuckRepository extends JpaRepository<Duck, Long>, JpaSpecificationExecutor<Duck> {
}
