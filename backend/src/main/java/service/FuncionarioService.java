package service;

import dto.EnderecoCadastroDTO;
import dto.FuncionarioCadastroDTO;
import entity.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import repository.*;

@Service
@RequiredArgsConstructor
public class FuncionarioService {
    //declaração dos repositorios

    private final FuncionarioRepository funcionarioRepository;
    private final EnderecoRepository enderecoRepository;
    private final PessoaRepository pessoaRepository;
    private final ClienteRepository clienteRepository;
    private final TelefoneRepository telefoneRepository;
    private final CargoRepository cargoRepository;
    private EnderecoService enderecoService;

    public void Cadastrar(FuncionarioCadastroDTO funcDto, EnderecoCadastroDTO enderecoDto) {
        Pessoa pessoa = new Pessoa();

        if(clienteRepository.findByPessoaCPF(funcDto.getCpf()) != null){
            pessoa = pessoaRepository.findByCPF(funcDto.getCpf()); //impede que o código tente duplicar a pessoa
        }else{ //caso não seja um cliente
            Endereco endereco = enderecoService.cadastrar(enderecoDto); //da insert no endereco e retorna para usar como FK

            //salva todas as informações básicas
            pessoa.setNomePessoa(funcDto.getNome());
            pessoa.setCPF(funcDto.getCpf());
            pessoa.setEmail(funcDto.getEmail());
            pessoa.setDataNascimento(funcDto.getDataNascimento());
            pessoa.setEndereco(endereco);

            String Sexo = funcDto.getSexo(); //só para não ficar copiando o getsexo

            //converte o valor de sexo para boolean
            if (Sexo == null) {
                pessoa.setSexo(null); //opção: prefiro não responder
            } else if (Sexo.equals("M")) {
                pessoa.setSexo(true); //opção: masculino
            } else if (Sexo.equals("F")) {
                pessoa.setSexo(false); //opção: feminino
            }

            pessoaRepository.save(pessoa); //da insert
        }

        //faz o cadastro do telefone
        Telefone telefone = new Telefone();
        telefone.setNumeroTel(funcDto.getTelefone());
        telefone.setPessoa(pessoa);
        telefoneRepository.save(telefone); //da insert

        Cargo cargo = cargoRepository.findByNome(funcDto.getCargo()); //pesquisa o cargo

        Funcionario funcionario = new Funcionario();
        funcionario.setPessoa(pessoa);
        funcionario.setSalario(funcDto.getSalario());
        funcionario.setCargo(cargo);
    }
}
