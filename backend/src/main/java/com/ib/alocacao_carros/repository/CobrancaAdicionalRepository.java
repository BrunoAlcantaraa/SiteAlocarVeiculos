package com.ib.alocacao_carros.repository;

import com.ib.alocacao_carros.entity.CobrancaAdicional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CobrancaAdicionalRepository extends JpaRepository<CobrancaAdicional, Long> {
}
