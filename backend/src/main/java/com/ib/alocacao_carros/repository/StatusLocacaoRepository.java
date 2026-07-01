package com.ib.alocacao_carros.repository;

import com.ib.alocacao_carros.entity.StatusLocacao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StatusLocacaoRepository   extends JpaRepository<StatusLocacao,Long> {
    StatusLocacao findByNome(String nome);
}
