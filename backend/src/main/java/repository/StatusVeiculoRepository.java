package repository;

import entity.StatusVeiculo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StatusVeiculoRepository extends JpaRepository<StatusVeiculo,Long> {
    StatusVeiculo findByNome(String nome);
}
