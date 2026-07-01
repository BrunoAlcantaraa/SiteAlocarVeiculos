package service;

import dto.CobrancaPlusCadastro;
import dto.PagamentoCadastroDTO;
import dto.PagamentoCadastroPosteriorDTO;
import entity.CobrancaAdicional;
import entity.Locacao;
import entity.Pagamento;
import entity.StatusPagamento;
import exception.AcaoInvalida;
import exception.RecursoNaoEncontrado;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import repository.CobrancaAdicionalRepository;
import repository.LocacaoRepository;
import repository.PagamentoRepository;
import repository.StatusPagamentoRepository;

import java.math.BigDecimal;
import java.time.temporal.ChronoUnit;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PagamentoService {
    //declaração dos repositorios
    private final PagamentoRepository pagamentoRepository;
    private final LocacaoRepository locacaoRepository;
    private final StatusPagamentoRepository statusPagamentoRepository;
    private CobrancaPlusService cobrancaPlusService;

    public BigDecimal CalcularValorFinal(Long idLocacao){
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
        StatusPagamento statusPagamento = statusPagamentoRepository.findByNome("Em aberto");

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
        StatusPagamento statusPagamento = statusPagamentoRepository.findByNome("Pago");

        pagamento.setFormaPagamento(dto.getFormaPagamento());
        pagamento.setValorPagamento(ValorFinal);
        locacao.ifPresent(pagamento::setLocacaoPagamento);
        pagamento.setStatusPagamento(statusPagamento);
        pagamento.setDataPagamento(dto.getDataPagamento());
        pagamentoRepository.save(pagamento);
    }

    public void AdicionarValor(Long id, CobrancaPlusCadastro dto){
        Optional<Pagamento> pagamento = pagamentoRepository.findById(id);

        if(pagamento.isPresent()){
            cobrancaPlusService.Cadastro(dto);

            BigDecimal valorAtual = pagamento.get().getValorPagamento();
            BigDecimal valorNovo = valorAtual.add(dto.getValor());
            pagamento.get().setValorPagamento(valorNovo);
            pagamentoRepository.save(pagamento.get());
        }
    }
}
