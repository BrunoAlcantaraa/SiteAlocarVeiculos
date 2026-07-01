package service;

import dto.*;
import entity.*;
import exception.AutorizacaoNegada;
import exception.DadosInvalidos;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import repository.*;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class FuncionarioService {
    //declaração dos repositorios

    private final FuncionarioRepository funcionarioRepository;
    private final PessoaRepository pessoaRepository;
    private final ClienteRepository clienteRepository;
    private final CargoRepository cargoRepository;
    private final TelefoneRepository telefoneRepository;
    private PessoaService pessoaService;
    private EnderecoService enderecoService;

    public boolean VerificarPermissao(Long id){
        Optional<Funcionario> funcionarioLogado = funcionarioRepository.findById(id);

        if(funcionarioLogado.isPresent()) {
            if(!funcionarioLogado.get().getCargo().getNomeCargo().equals("Gerente")){
                return false;
            }
        }
        return true;
    }

    public FuncionarioRetornoDTO ConverterParaDTO(Funcionario funcionario){
        FuncionarioRetornoDTO dto = new FuncionarioRetornoDTO();
        PessoaRetornoDTO pes = pessoaService.ConverterParaDto(funcionario.getPessoa());
        EnderecoCadastroDTO endr = enderecoService.ConverterParaDTO(funcionario.getPessoa().getEndereco());

        dto.setPessoa(pes);
        dto.setEndereco(endr);
        dto.setSalario(funcionario.getSalario());
        dto.setCargo(funcionario.getCargo().getNomeCargo());
        dto.setTelefones(telefoneRepository.findAll());

        return dto;
    }

    public Long verificaSenha(LoginDTO dto) {
        Funcionario funcionario = funcionarioRepository.findByUsername(dto.getUsername());
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

        if(funcionario == null) {
            return null;
        }

        if(encoder.matches(dto.getSenha(), funcionario.getSenhaHash())) {
            return funcionario.getIdFuncionario();
        }

        return null;
    }

    public void Cadastrar(FuncionarioCadastroDTO dto) {
        if (!VerificarPermissao(dto.getIdFunc())) throw new AutorizacaoNegada("Ação não autorizada");
        PessoaCadastroDTO pesDto = dto.getPessoa();
        EnderecoCadastroDTO enderecoDto = dto.getEndereco();

        Pessoa pessoa = new Pessoa();

        if (clienteRepository.findByPessoaCPF(pesDto.getCpf()) != null) {
            pessoa = pessoaRepository.findByCPF(pesDto.getCpf()); //impede que o código tente duplicar a pessoa
        } else { //caso não seja um cliente
            pessoaService.VerificarDados(pesDto.getCpf(),pesDto.getEmail(),pesDto.getDataNascimento());

            pessoa = pessoaService.Cadastro(pesDto, enderecoDto);
        }

            Cargo cargo = cargoRepository.findByNome(dto.getCargo()); //pesquisa o cargo
            Funcionario funcionario = new Funcionario();
            BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
            String senhaCript = encoder.encode(dto.getSenha()); //criptografa a senha
            funcionario.setPessoa(pessoa);
            funcionario.setSalario(dto.getSalario());
            funcionario.setUsername(dto.getUsername());
            funcionario.setSenhaHash(senhaCript);
            funcionario.setCargo(cargo);
            funcionarioRepository.save(funcionario); //da insert
        }

    public void Editar(FuncionarioEdicaoDTO funcDto) {
        if(!VerificarPermissao(funcDto.getIdFuncLogado())) throw new AutorizacaoNegada("Ação não autorizada");

        PessoaEdicaoDTO pesDto = funcDto.getPessoa();
        EnderecoCadastroDTO enderecoDto = funcDto.getEndereco();
        TelefoneEdicaoDTO telefoneDto = funcDto.getTelefone();

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

    public List<FuncionarioRetornoDTO> Listar(){
        List<Funcionario> funcionarios =  funcionarioRepository.findAll();

        return funcionarios.stream().map(this::ConverterParaDTO).toList();
    }
}
