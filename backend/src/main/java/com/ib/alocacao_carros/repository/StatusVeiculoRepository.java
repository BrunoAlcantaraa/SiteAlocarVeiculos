package com.ib.alocacao_carros.repository;

import com.ib.alocacao_carros.entity.StatusVeiculo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StatusVeiculoRepository extends JpaRepository<StatusVeiculo,Integer> {
    StatusVeiculo findByNomeStatusVeiculo(String nome);
}
