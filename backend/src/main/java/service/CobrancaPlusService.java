package service;

import dto.CobrancaPlusCadastro;
import dto.CobrancaPlusRetornoDTO;
import dto.LocacaoRetornoDTO;
import entity.CobrancaAdicional;
import entity.Locacao;
import exception.RecursoNaoEncontrado;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import repository.CobrancaAdicionalRepository;
import repository.LocacaoRepository;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CobrancaPlusService {
    private final CobrancaAdicionalRepository cobrancaAdicionalRepository;
    private final LocacaoRepository locacaoRepository;

    private LocacaoService locacaoService;

    public CobrancaPlusRetornoDTO ConverterParaDTO(CobrancaAdicional cobrancaAdicional) {
        CobrancaPlusRetornoDTO dto = new CobrancaPlusRetornoDTO();
        LocacaoRetornoDTO lc = locacaoService.ConverterParaDTO(cobrancaAdicional.getLocacao());

        dto.setDescricao(cobrancaAdicional.getDescricaoCobranca());
        dto.setLocacaoRetornoDTO(lc);
        dto.setValor(cobrancaAdicional.getValorCobranca());

        return dto;
    }

    public void Cadastro(CobrancaPlusCadastro dto){
        CobrancaAdicional plus = new CobrancaAdicional();
        Optional<Locacao> locacao = locacaoRepository.findById(dto.getIdLocacao());

        if(locacao.isPresent()){
            plus.setDescricaoCobranca(dto.getDescricao());
            plus.setValorCobranca(dto.getValor());
            plus.setLocacao(locacao.get());
            cobrancaAdicionalRepository.save(plus);
        }else{
            throw new RecursoNaoEncontrado("Locacao não encontrada");
        }
    }

    public void Remover(Long id){
        cobrancaAdicionalRepository.deleteById(id);
    }

    public List<CobrancaPlusRetornoDTO> Listar(){
        List<CobrancaAdicional> cobrancas =  cobrancaAdicionalRepository.findAll();

        return cobrancas.stream().map(this::ConverterParaDTO).toList();
    }
}
