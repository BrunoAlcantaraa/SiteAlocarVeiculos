package repository;

import entity.CobrancaAdicional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CobrancaAdicionalRepository extends JpaRepository<CobrancaAdicional, Long> {
}
