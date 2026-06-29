package repository;

import entity.Combustivel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CombustivelRepository extends JpaRepository<Combustivel, Long> {

    Combustivel findByNome(String nome);
}
