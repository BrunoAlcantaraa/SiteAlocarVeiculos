package service;

import dto.*;
import entity.Cliente;
import entity.Pessoa;
import exception.DadosInvalidos;
import exception.RecursoNaoEncontrado;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import repository.*;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ClienteService {
    //declaração dos repositorios utilizados

    private final ClienteRepository clienteRepository;
    private final TelefoneRepository telefoneRepository;
    private final PessoaRepository pessoaRepository;
    private final FuncionarioRepository funcionarioRepository;
    private final EnderecoRepository enderecoRepository;
    private PessoaService pessoaService;

    public void Cadastrar(ClienteCadastroDTO dto) {
        Pessoa pessoa = new Pessoa();

        PessoaCadastroDTO pesDto = dto.getPessoa();
        EnderecoCadastroDTO enderecoDto = dto.getEndereco();

        if(funcionarioRepository.findByPessoaCPF(pesDto.getCpf()) != null){ //caso algum funcionario queira ser cliente, não duplica a pessoa
            pessoa = pessoaRepository.findByCPF(pesDto.getCpf());
        }else { //caso não seja um funcionário, segue o fluxo normal
            pessoaService.VerificarDados(pesDto.getCpf(),pesDto.getEmail(),pesDto.getDataNascimento());

            pessoa = pessoaService.Cadastro(pesDto,enderecoDto);
        }

        Cliente cliente = new Cliente();
        cliente.setPessoa(pessoa); //define a fk de cliente
        clienteRepository.save(cliente); //da o insert
    }

    public void Editar(ClienteEdicaoDTO dto) {
        PessoaEdicaoDTO pesDto = dto.getPessoa();
        EnderecoCadastroDTO enderecoDto = dto.getEndereco();
        TelefoneEdicaoDTO telefoneDto = dto.getTelefone();

        Optional<Cliente> cliente = clienteRepository.findById(dto.getId());

        if(cliente.isPresent()){
            Pessoa pes = cliente.get().getPessoa();
            pessoaService.Edicao(pes,pesDto,enderecoDto,telefoneDto);
        }else{
            throw new RecursoNaoEncontrado("Cliente não encontrado");
        }
    }
}
