package service;

import dto.PessoaCadastroDTO;
import dto.PessoaEdicaoDTO;
import dto.TelefoneEdicaoDTO;
import entity.Cliente;
import entity.Pessoa;
import exception.DadosInvalidos;
import exception.RecursoNaoEncontrado;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import repository.*;
import dto.EnderecoCadastroDTO;

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

    public void Cadastrar(PessoaCadastroDTO pesDto, EnderecoCadastroDTO enderecoDto){
        Pessoa pessoa = new Pessoa();

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

    public void Editar(Long id, PessoaEdicaoDTO pesDto, EnderecoCadastroDTO enderecoDto, TelefoneEdicaoDTO telefoneDto){
        Optional<Cliente> cliente = clienteRepository.findById(id);

        if(cliente.isPresent()){
            Pessoa pes = cliente.get().getPessoa();
            pessoaService.Edicao(pes,pesDto,enderecoDto,telefoneDto);
        }else{
            throw new RecursoNaoEncontrado("Cliente não encontrado");
        }
    }
}
