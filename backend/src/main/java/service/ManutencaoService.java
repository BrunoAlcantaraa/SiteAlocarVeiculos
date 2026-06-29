package service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import repository.ManutencaoRepository;
import repository.StatusVeiculoRepository;

@Service
@RequiredArgsConstructor
public class ManutencaoService {
    //decaração dos repositorios
    private final StatusVeiculoRepository statusVeiculoRepository;
    private final ManutencaoRepository manutencaoRepository;
}
