package com.ib.alocacao_carros.repository;

import com.ib.alocacao_carros.entity.Pessoa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PessoaRepository  extends JpaRepository<Pessoa, Integer> {
    Pessoa findByCPF(String cpf);
}
