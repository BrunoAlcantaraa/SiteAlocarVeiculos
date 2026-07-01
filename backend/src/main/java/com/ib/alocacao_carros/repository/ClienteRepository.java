package com.ib.alocacao_carros.repository;

import com.ib.alocacao_carros.entity.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long> {
    Cliente findByPessoaCPF(String cpf);
}
