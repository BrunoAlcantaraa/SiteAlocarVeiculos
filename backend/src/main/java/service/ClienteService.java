package service;

import entity.Cliente;
import entity.Endereco;
import entity.Pessoa;
import entity.Telefone;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import repository.*;
import dto.ClienteCadastroDTO;
import dto.EnderecoCadastroDTO;

@Service
@RequiredArgsConstructor
public class ClienteService {
    //declaração dos repositorios utilizados

    private final ClienteRepository clienteRepository;
    private final TelefoneRepository telefoneRepository;
    private final PessoaRepository pessoaRepository;
    private final FuncionarioRepository funcionarioRepository;
    private final EnderecoRepository enderecoRepository;
    private EnderecoService enderecoService;

    public boolean VerificarDados(ClienteCadastroDTO dto){ //verificar se vai ser necessário

        return true;
    }

    public void Cadastrar(ClienteCadastroDTO clienteDto, EnderecoCadastroDTO enderecoDto){
        Pessoa pessoa = new Pessoa();

        if(funcionarioRepository.findByPessoaCPF(clienteDto.getCpf()) != null){ //caso algum funcionario queira ser cliente, não duplica a pessoa
            pessoa = pessoaRepository.findByCPF(clienteDto.getCpf());

        }else { //caso não seja um funcionário, segue o fluxo normal

            Endereco endereco = enderecoService.cadastrar(enderecoDto); //da insert no endereco e retorna para usar como FK

            //salva todas as informações básicas
            pessoa.setNomePessoa(clienteDto.getNome());
            pessoa.setCPF(clienteDto.getCpf());
            pessoa.setEmail(clienteDto.getEmail());
            pessoa.setDataNascimento(clienteDto.getDataNascimento());
            pessoa.setEndereco(endereco);

            String Sexo = clienteDto.getSexo(); //só para não ficar copiando o getsexo

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
        telefone.setNumeroTel(clienteDto.getTelefone());
        telefone.setPessoa(pessoa);
        telefoneRepository.save(telefone); //da insert

        Cliente cliente = new Cliente();
        cliente.setPessoa(pessoa); //define a fk de cliente
        clienteRepository.save(cliente); //da o insert
    }
}
