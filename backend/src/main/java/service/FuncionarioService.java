package service;

import dto.*;
import entity.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import repository.*;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class FuncionarioService {
    //declaração dos repositorios

    private final FuncionarioRepository funcionarioRepository;
    private final PessoaRepository pessoaRepository;
    private final ClienteRepository clienteRepository;
    private final CargoRepository cargoRepository;
    private PessoaService pessoaService;

    public boolean VerificarPermissao(Long id){
        Optional<Funcionario> funcionarioLogado = funcionarioRepository.findById(id);

        if(funcionarioLogado.isPresent()) {
            if(!funcionarioLogado.get().getCargo().getNomeCargo().equals("Gerente")){
                return false;
            }
        }
        return true;
    }

    public boolean verificaSenha(String user, String senha) {
        Funcionario funcionario = funcionarioRepository.findByUsername(user);
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

        if(funcionario == null) {
            return false;
        }

        return encoder.matches(senha, funcionario.getSenhaHash());
    }

    public void Cadastrar(FuncionarioCadastroDTO funcDto, EnderecoCadastroDTO enderecoDto, PessoaCadastroDTO pesDto) {
        if (!VerificarPermissao(funcDto.getIdFunc())) throw new RuntimeException("Ação não autorizada");

        Pessoa pessoa = new Pessoa();

        if (clienteRepository.findByPessoaCPF(pesDto.getCpf()) != null) {
            pessoa = pessoaRepository.findByCPF(pesDto.getCpf()); //impede que o código tente duplicar a pessoa
        } else { //caso não seja um cliente
            if(!pessoaService.VerificarDados(pesDto.getCpf(),
                    pesDto.getEmail(),
                    pesDto.getDataNascimento()))
                throw new RuntimeException("Algum dado (Cpf,Email ou Data de nascimento, está inválido");

            pessoa = pessoaService.Cadastro(pesDto, enderecoDto);
        }

            Cargo cargo = cargoRepository.findByNome(funcDto.getCargo()); //pesquisa o cargo
            Funcionario funcionario = new Funcionario();
            BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
            String senhaCript = encoder.encode(funcDto.getSenha()); //criptografa a senha
            funcionario.setPessoa(pessoa);
            funcionario.setSalario(funcDto.getSalario());
            funcionario.setUsername(funcDto.getUsername());
            funcionario.setSenhaHash(senhaCript);
            funcionario.setCargo(cargo);
            funcionarioRepository.save(funcionario); //da insert
        }

    public void Editar(FuncionarioEdicaoDTO funcDto,
                       EnderecoCadastroDTO enderecoDto,
                       PessoaEdicaoDTO pesDto,
                       TelefoneEdicaoDTO telefoneDto) {

        if(!VerificarPermissao(funcDto.getIdFuncLogado())) throw new RuntimeException("Ação não autorizada");

        Optional<Funcionario> func =  funcionarioRepository.findById(funcDto.getIdFunc());

        if(func.isPresent()) {
            Pessoa pes = func.get().getPessoa();
            pessoaService.Edicao(pes,pesDto,enderecoDto,telefoneDto); //atualiza as informações de pessoa

            BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
            String senhaHash = encoder.encode(funcDto.getSenha());
            Cargo cargo = cargoRepository.findByNome(funcDto.getCargo());
            func.get().setSalario(funcDto.getSalario());
            func.get().setUsername(funcDto.getUsername());
            func.get().setSenhaHash(senhaHash);
            func.get().setCargo(cargo);
            funcionarioRepository.save(func.get()); //da update
        }
    }
}
