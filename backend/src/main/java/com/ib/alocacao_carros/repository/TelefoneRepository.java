package com.ib.alocacao_carros.repository;

import com.ib.alocacao_carros.entity.Telefone;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TelefoneRepository extends JpaRepository<Telefone,Integer> {
}
