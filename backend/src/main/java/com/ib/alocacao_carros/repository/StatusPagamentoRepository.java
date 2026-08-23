package com.ib.alocacao_carros.repository;

import com.ib.alocacao_carros.entity.StatusPagamento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StatusPagamentoRepository extends JpaRepository<StatusPagamento,Integer> {
    StatusPagamento findByNomeStatusPagamento(String nome);
}
