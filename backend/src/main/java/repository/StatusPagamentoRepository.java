package repository;

import entity.StatusPagamento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StatusPagamentoRepository extends JpaRepository<StatusPagamento,Long> {
    StatusPagamento findByNome(String nome);
    StatusPagamento findByFormaPagamento(String formaPagamento);
}
