package com.ib.alocacao_carros.controller;

import com.ib.alocacao_carros.dto.AdicionarValorDTO;
import com.ib.alocacao_carros.dto.PagamentoCadastroDTO;
import com.ib.alocacao_carros.dto.PagamentoCadastroPosteriorDTO;
import com.ib.alocacao_carros.dto.PagamentoRetornoDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.ib.alocacao_carros.service.PagamentoService;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/Pagamentos")
public class PagamentoController {
    @Autowired
    private PagamentoService pagamentoService;

    @PostMapping
    public ResponseEntity<ApiResponce<Void>> CadastrarPagamento(
            @RequestBody PagamentoCadastroDTO dto){

        pagamentoService.Cadastro(dto);

        return ResponseEntity.status(HttpStatus.CREATED).body(
                new ApiResponce<>(
                        true,
                        "Pagamento criado com sucesso",
                        null,
                        LocalDateTime.now()
                )
        );
    }

    @PostMapping
    public ResponseEntity<ApiResponce<Void>> CadastrarPagamentoPosterior(
            @RequestBody PagamentoCadastroPosteriorDTO dto){

        pagamentoService.CadastroPosterior(dto);

        return ResponseEntity.status(HttpStatus.CREATED).body(
                new ApiResponce<>(
                        true,
                        "Pagamento criado com sucesso",
                        null,
                        LocalDateTime.now()
                )
        );
    }

    @PutMapping
    public ResponseEntity<ApiResponce<Void>> AdicionarValor(
            @RequestBody AdicionarValorDTO dto){

        pagamentoService.AdicionarValor(dto);

        return ResponseEntity.status(HttpStatus.OK).body(
                new ApiResponce<>(
                        true,
                        "Pagamento criado com sucesso",
                        null,
                        LocalDateTime.now()
                )
        );
    }

    @GetMapping
    public ResponseEntity<ApiResponce<List<PagamentoRetornoDTO>>> ListarPagamentos(){
        List<PagamentoRetornoDTO> pagamentos = pagamentoService.Listar();

        return ResponseEntity.status(HttpStatus.OK).body(
                new ApiResponce<>(
                        true,
                        "Pagamentos listados com sucesso",
                        pagamentos,
                        LocalDateTime.now()
                )
        );
    }
}
