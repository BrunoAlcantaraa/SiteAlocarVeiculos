package service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import repository.*;

@Service
@RequiredArgsConstructor
public class LocacaoService {
    //declaracao dos repositories
    private final ClienteRepository clienteRepository;
    private final VeiculoRepository veiculoRepository;
    private final LocacaoRepository locacaoRepository;
    private final StatusLocacaoRepository statusLocacaoRepository;
    private final FuncionarioRepository funcionarioRepository;
}
