package repository;

import entity.Funcionario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FuncionarioRepository extends JpaRepository<Funcionario, Long> {
    Funcionario findByPessoaCPF(String cpf); //pesquisa o funcionário pelo cpf dele
    Funcionario findByUsername(String username);
}
