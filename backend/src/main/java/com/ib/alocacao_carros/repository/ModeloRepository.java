package com.ib.alocacao_carros.repository;

import com.ib.alocacao_carros.entity.Modelo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ModeloRepository extends JpaRepository<Modelo, Integer> {
    Modelo findByNomeModelo(String nome);
    Modelo findByNomeModeloAndAnoFab(String nome, Integer anoFabricacao);
}
