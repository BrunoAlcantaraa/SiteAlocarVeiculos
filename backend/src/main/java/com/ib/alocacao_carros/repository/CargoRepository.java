package com.ib.alocacao_carros.repository;

import com.ib.alocacao_carros.entity.Cargo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CargoRepository extends JpaRepository<Cargo, Integer> {

    Cargo findByNomeCargo(String nome);
}
