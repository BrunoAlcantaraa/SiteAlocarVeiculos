package com.ib.alocacao_carros.service;

import com.ib.alocacao_carros.dto.EnderecoCadastroDTO;
import com.ib.alocacao_carros.entity.Cidade;
import com.ib.alocacao_carros.entity.Endereco;
import com.ib.alocacao_carros.entity.Estado;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import com.ib.alocacao_carros.repository.EnderecoRepository;
import com.ib.alocacao_carros.repository.CidadeRepository;
import com.ib.alocacao_carros.repository.EstadoRepository;

@Service
@RequiredArgsConstructor
public class EnderecoService {
    private final EnderecoRepository enderecoRepository;
    private final EstadoRepository estadoRepository;
    private final CidadeRepository cidadeRepository;

    public EnderecoCadastroDTO ConverterParaDTO(Endereco endereco){
        EnderecoCadastroDTO dto = new EnderecoCadastroDTO();

        dto.setRua(endereco.getRua());
        dto.setNumero(endereco.getNumero());
        dto.setComplemento(endereco.getComplemento());
        dto.setBairro(endereco.getBairro());
        dto.setCep(endereco.getCEP());
        dto.setCidade(endereco.getCidade().getNomeCidade());
        dto.setEstado(endereco.getCidade().getEstado().getNomeEstado());

        return dto;
    }

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
