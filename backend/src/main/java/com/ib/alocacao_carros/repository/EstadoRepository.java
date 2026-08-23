package com.ib.alocacao_carros.repository;

import com.ib.alocacao_carros.entity.Estado;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EstadoRepository extends JpaRepository<Estado, Integer> {
    Estado findByNomeEstado(String nome);
}
