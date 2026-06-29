package service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import repository.ClienteRepository;
import repository.EnderecoRepository;
import repository.PessoaRepository;
import repository.TelefoneRepository;

@Service
@RequiredArgsConstructor
public class ClienteService {
    //declaração dos repositorios utilizados

    private final ClienteRepository clienteRepository;
    private final TelefoneRepository telefoneRepository;
    private final PessoaRepository pessoaRepository;
    private final EnderecoRepository enderecoRepository;
}
