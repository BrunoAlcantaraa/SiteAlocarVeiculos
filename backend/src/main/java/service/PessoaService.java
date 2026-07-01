package service;

import dto.*;
import entity.Endereco;
import entity.Pessoa;
import entity.Telefone;
import exception.DadosInvalidos;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import repository.PessoaRepository;
import repository.TelefoneRepository;

import java.time.LocalDate;
import java.time.Period;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PessoaService {
    private final PessoaRepository pessoaRepository;
    private final TelefoneRepository telefoneRepository;

    private EnderecoService enderecoService;

    public Boolean DefineSexo(String sexo){
        if(sexo == null){
            return null;
        }else if(sexo.equals("M")){
            return true;
        }else{
            return false;
        }
    }

    public String VoltaSexo(Boolean sexo){
        if(sexo == null){
            return null;
        }else if(sexo){
            return "Masculino";
        }else{
            return "Femenino";
        }
    }

    public PessoaRetornoDTO ConverterParaDto(Pessoa pessoa){
        PessoaRetornoDTO dto = new PessoaRetornoDTO();
        String sexo = VoltaSexo(pessoa.getSexo());

        dto.setNome(pessoa.getNomePessoa());
        dto.setEmail(pessoa.getEmail());
        dto.setSexo(sexo);
        dto.setDataNascimento(pessoa.getDataNascimento());
        dto.setCpf(pessoa.getCPF());

        return dto;
    }

    public void VerificarDados(String cpf, String email, LocalDate dataNascimento){
        //Verifica CPF

        if(cpf.isBlank() || cpf.length() != 11){
            throw new DadosInvalidos("CPF inválido ou vazio");
        }

        if(!email.isEmpty()){ //caso tenha email
            if(!email.contains("@")){ //caso o email não tenha @
                throw new DadosInvalidos("Email inválido");
            }
        }

        if(!LocalDate.now().isAfter(dataNascimento)){ //caso a data de nascimento seja depois do ano atual
            throw new DadosInvalidos("Data de nascimento inválida");
        }
    }

    public Pessoa Cadastro(PessoaCadastroDTO dto, EnderecoCadastroDTO dto2) {

        Pessoa pessoa = new Pessoa();
        Endereco endereco = enderecoService.cadastrar(dto2);
        Boolean sexo = DefineSexo(dto.getSexo());

        pessoa.setEndereco(endereco);
        pessoa.setNomePessoa(dto.getNome());
        pessoa.setCPF(dto.getCpf());
        pessoa.setEmail(dto.getEmail());
        pessoa.setDataNascimento(dto.getDataNascimento());
        pessoa.setSexo(sexo);
        pessoaRepository.save(pessoa); //da insert

        //faz o cadastro do telefone
        Telefone telefone = new Telefone();
        telefone.setNumeroTel(dto.getTelefone());
        telefone.setPessoa(pessoa);
        telefoneRepository.save(telefone); //da insert

        return pessoa;
    }

    public void Edicao(Pessoa pes, PessoaEdicaoDTO dto, EnderecoCadastroDTO dto2, TelefoneEdicaoDTO telefoneDto) {
        Endereco endereco = pes.getEndereco();
        Boolean sexo = DefineSexo(dto.getSexo());
        endereco = enderecoService.Editar(endereco, dto2);

        pes.setEndereco(endereco);
        pes.setNomePessoa(dto.getNome());
        pes.setEmail(dto.getEmail());
        pes.setDataNascimento(dto.getDataNascimento());
        pes.setSexo(sexo);
        pessoaRepository.save(pes); //da insert

        //atualiza o telefone
        Optional<Telefone> telefone = telefoneRepository.findById(telefoneDto.getId());
        if (telefone.isPresent()) {
            telefone.get().setNumeroTel(telefoneDto.getNumero());
            telefoneRepository.save(telefone.get()); //da insert
        }
    }
}
