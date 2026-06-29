package service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import repository.MarcaRepository;
import repository.ModeloRepository;
import repository.StatusVeiculoRepository;
import repository.VeiculoRepository;

@Service
@RequiredArgsConstructor
public class VeiculoService {
    //declaração dos repositorios
    private final VeiculoRepository veiculoRepository;
    private final ModeloRepository modeloRepository;
    private final MarcaRepository marcaRepository;
    private final StatusVeiculoRepository statusVeiculoRepository;
}
