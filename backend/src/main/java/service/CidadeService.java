package service;

import dto.CidadeDTO;
import entity.Cidade;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import repository.CidadeRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CidadeService {
    private final CidadeRepository cidadeRepository;

    public CidadeDTO converterParaDTO(Cidade cidade) {
        CidadeDTO dto = new CidadeDTO();
        dto.setNome(cidade.getNomeCidade());
        dto.setEstadoSigla(cidade.getEstado().getSigla());

        return dto;
    }

    public List<CidadeDTO> Listar(){
        List<Cidade> cidades = cidadeRepository.findAll();

        return cidades.stream().map(this::converterParaDTO).toList();
    }
}
