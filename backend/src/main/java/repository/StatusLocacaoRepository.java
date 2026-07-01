package repository;

import entity.StatusLocacao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StatusLocacaoRepository   extends JpaRepository<StatusLocacao,Long> {
    StatusLocacao findByNome(String nome);
}
