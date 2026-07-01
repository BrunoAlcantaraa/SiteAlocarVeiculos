package com.ib.alocacao_carros.repository;

import com.ib.alocacao_carros.entity.Cidade;
import com.ib.alocacao_carros.entity.Estado;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CidadeRepository extends JpaRepository<Cidade, Long> {

    Cidade findByNome(String nome);
    Cidade findByNomeAndEstate(String nome, Estado estado); //evita que a cidade com msm nome mas estado diferente seja mal selecionada
}
