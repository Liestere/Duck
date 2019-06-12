package TrialExam.repository;

import TrialExam.model.Duck;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestParam;

@Repository
public interface DuckRepository extends JpaRepository<Duck, Long> {
//    @Query("select s.quantity * g.price from Stock s, Guitar g where s.guitar.id = g.id and s.guitar.id = ?1")
//    public int getStockValueByGuitarId(Long id);

}
