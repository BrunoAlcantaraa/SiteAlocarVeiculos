package repository;

import entity.Modelo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ModeloRepository extends JpaRepository<Modelo, Long> {
    Modelo findByNome(String nome);
    Modelo findByNomeAndAnoFab(String nome, Integer anoFabricacao);
}
