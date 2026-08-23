package com.ib.alocacao_carros.service;

import com.ib.alocacao_carros.dto.*;
import com.ib.alocacao_carros.entity.CobrancaAdicional;
import com.ib.alocacao_carros.entity.Locacao;
import com.ib.alocacao_carros.entity.Pagamento;
import com.ib.alocacao_carros.entity.StatusPagamento;
import com.ib.alocacao_carros.exception.AcaoInvalida;
import com.ib.alocacao_carros.exception.RecursoNaoEncontrado;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import com.ib.alocacao_carros.repository.LocacaoRepository;
import com.ib.alocacao_carros.repository.PagamentoRepository;
import com.ib.alocacao_carros.repository.StatusPagamentoRepository;

import java.math.BigDecimal;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PagamentoService {
    //declaração dos repositorios
    private final PagamentoRepository pagamentoRepository;
    private final LocacaoRepository locacaoRepository;
    private final StatusPagamentoRepository statusPagamentoRepository;

    private CobrancaPlusService cobrancaPlusService;
    private LocacaoService locacaoService;

    public PagamentoRetornoDTO ConverterParaDTO(Pagamento pagamento) {
        PagamentoRetornoDTO dto = new PagamentoRetornoDTO();
        LocacaoRetornoDTO locacaoRetornoDTO = locacaoService.ConverterParaDTO(pagamento.getLocacaoPagamento());

        dto.setDataPagamento(pagamento.getDataPagamento());
        dto.setValorPagamento(pagamento.getValorPagamento());
        dto.setFormaPagamento(pagamento.getFormaPagamento());
        dto.setLocacao(locacaoRetornoDTO);
        dto.setStatus(pagamento.getStatusPagamento().getNomeStatusPagamento());

        return dto;
    }

    public BigDecimal CalcularValorFinal(Integer idLocacao){
        Optional<Locacao> locacao = locacaoRepository.findById(idLocacao);

        if(locacao.isPresent()){
            if(!locacao.get().getStatusLocacao().getNomeStatusLocacao().equals("Finalizada")) throw new AcaoInvalida("Locação ainda não finalizada");
            long dias = ChronoUnit.DAYS.between(
                    locacao.get().getDataSaida(),
                    locacao.get().getDataRetornoReal()
            );

            BigDecimal diaria = locacao
                    .get()
                    .getVeiculo()
                    .getModelo()
                    .getValorDiario();

            BigDecimal valorFinal = diaria.multiply(BigDecimal.valueOf(dias));
            BigDecimal extras = locacao.get()
                    .getCobrancaPlus()
                    .stream()
                    .map(CobrancaAdicional::getValorCobranca)
                    .reduce(BigDecimal.ZERO, BigDecimal::add);

            valorFinal =  valorFinal.add(extras);
            return valorFinal;
        }
        return null;
    }

    public void Cadastro(PagamentoCadastroDTO dto){
        Pagamento pagamento = new Pagamento();
        BigDecimal ValorFinal;

        ValorFinal = CalcularValorFinal(dto.getIdLocacao());
        if(ValorFinal == null) throw new RecursoNaoEncontrado("Locacao não encontrada");

        Optional<Locacao> locacao = locacaoRepository.findById(dto.getIdLocacao());
        StatusPagamento statusPagamento = statusPagamentoRepository.findByNomeStatusPagamento("Em aberto");

        pagamento.setFormaPagamento(dto.getFormaPagamento());
        pagamento.setValorPagamento(ValorFinal);
        locacao.ifPresent(pagamento::setLocacaoPagamento);
        pagamento.setStatusPagamento(statusPagamento);
        pagamentoRepository.save(pagamento);
    }

    public void CadastroPosterior(PagamentoCadastroPosteriorDTO dto){
        Pagamento pagamento = new Pagamento();
        BigDecimal ValorFinal;

        ValorFinal = CalcularValorFinal(dto.getIdLocacao());
        if(ValorFinal == null) throw new RecursoNaoEncontrado("Locacao não encontrada");

        Optional<Locacao> locacao = locacaoRepository.findById(dto.getIdLocacao());
        StatusPagamento statusPagamento = statusPagamentoRepository.findByNomeStatusPagamento("Pago");

        pagamento.setFormaPagamento(dto.getFormaPagamento());
        pagamento.setValorPagamento(ValorFinal);
        locacao.ifPresent(pagamento::setLocacaoPagamento);
        pagamento.setStatusPagamento(statusPagamento);
        pagamento.setDataPagamento(dto.getDataPagamento());
        pagamentoRepository.save(pagamento);
    }

    public void AdicionarValor(AdicionarValorDTO dto){
        CobrancaPlusCadastro CPC = dto.getCPC();
        Optional<Pagamento> pagamento = pagamentoRepository.findById(CPC.getIdLocacao());

        if(pagamento.isPresent()){
            cobrancaPlusService.Cadastro(CPC);

            BigDecimal valorAtual = pagamento.get().getValorPagamento();
            BigDecimal valorNovo = valorAtual.add(CPC.getValor());
            pagamento.get().setValorPagamento(valorNovo);
            pagamentoRepository.save(pagamento.get());
        }
    }

    public List<PagamentoRetornoDTO> Listar(){
        List<Pagamento> pagamentos =  pagamentoRepository.findAll();

        return pagamentos.stream().map(this::ConverterParaDTO).toList();
    }
}
