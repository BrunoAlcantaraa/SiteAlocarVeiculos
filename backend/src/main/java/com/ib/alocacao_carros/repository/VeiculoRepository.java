package com.ib.alocacao_carros.repository;

import com.ib.alocacao_carros.entity.Veiculo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VeiculoRepository extends JpaRepository<Veiculo,Long> {
    Veiculo findByPlaca(String  placa);
}
