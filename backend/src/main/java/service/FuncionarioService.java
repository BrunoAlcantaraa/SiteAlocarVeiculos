package service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import repository.EnderecoRepository;
import repository.FuncionarioRepository;
import repository.PessoaRepository;

@Service
@RequiredArgsConstructor
public class FuncionarioService {
    //declaração dos repositorios

    private final FuncionarioRepository funcionarioRepository;
    private final EnderecoRepository enderecoRepository;
    private final PessoaRepository pessoaRepository;
}
