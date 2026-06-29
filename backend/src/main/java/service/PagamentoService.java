package service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import repository.LocacaoRepository;
import repository.PagamentoRepository;
import repository.StatusPagamentoRepository;

@Service
@RequiredArgsConstructor
public class PagamentoService {
    //declaração dos repositorios
    private final PagamentoRepository pagamentoRepository;
    private final LocacaoRepository locacaoRepository;
    private final StatusPagamentoRepository statusPagamentoRepository;
}
