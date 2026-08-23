package com.ib.alocacao_carros.repository;

import com.ib.alocacao_carros.entity.Funcionario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FuncionarioRepository extends JpaRepository<Funcionario, Integer> {
    Funcionario findByPessoa_CPF(String cpf); //pesquisa o funcionário pelo cpf dele
    Funcionario findByUsername(String username);
}
