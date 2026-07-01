package service;

import dto.EnderecoCadastroDTO;
import entity.Cidade;
import entity.Endereco;
import entity.Estado;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import repository.EnderecoRepository;
import repository.CidadeRepository;
import repository.EstadoRepository;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class EnderecoService {
    private final EnderecoRepository enderecoRepository;
    private final EstadoRepository estadoRepository;
    private final CidadeRepository cidadeRepository;

    public Endereco cadastrar(EnderecoCadastroDTO dto){
        Endereco endereco = new Endereco();
        Estado estado = estadoRepository.findByNome(dto.getEstado()); //pesquisa um estado para evitar pegar a cidade errada
        Cidade cidade = cidadeRepository.findByNomeAndEstate(dto.getCidade(),estado); //pesquisa a cidade para definir a FK

        //definição dos atributos
        endereco.setRua(dto.getRua());
        endereco.setNumero(dto.getNumero());
        endereco.setBairro(dto.getBairro());
        endereco.setCEP(dto.getCep());
        endereco.setComplemento(dto.getComplemento());
        endereco.setCidade(cidade); //FK

        enderecoRepository.save(endereco); //da o insert
        return endereco; //retorna para o cliente service
    }

    public Endereco Editar(Endereco endereco, EnderecoCadastroDTO dto) {
        Estado estado = estadoRepository.findByNome(dto.getEstado());
        Cidade cid = cidadeRepository.findByNomeAndEstate(dto.getCidade(),estado);

        //definição dos atributos
        endereco.setRua(dto.getRua());
        endereco.setNumero(dto.getNumero());
        endereco.setBairro(dto.getBairro());
        endereco.setCEP(dto.getCep());
        endereco.setComplemento(dto.getComplemento());
        endereco.setCidade(cid); //FK

        enderecoRepository.save(endereco); //da o insert
        return endereco; //retorna para o cliente service
    }
}
