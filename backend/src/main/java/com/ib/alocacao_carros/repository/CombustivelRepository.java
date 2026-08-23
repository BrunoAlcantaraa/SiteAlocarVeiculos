package com.ib.alocacao_carros.repository;

import com.ib.alocacao_carros.entity.Combustivel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CombustivelRepository extends JpaRepository<Combustivel, Integer> {

    Combustivel findByNomeCombustivel(String nome);
}
