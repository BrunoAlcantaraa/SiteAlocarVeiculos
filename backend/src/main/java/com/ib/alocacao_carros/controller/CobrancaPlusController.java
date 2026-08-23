package com.ib.alocacao_carros.controller;

import com.ib.alocacao_carros.dto.CobrancaPlusCadastro;
import com.ib.alocacao_carros.dto.CobrancaPlusRetornoDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.ib.alocacao_carros.service.CobrancaPlusService;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/Cobrancas")
public class CobrancaPlusController {
    @Autowired
    private CobrancaPlusService service;

    @PostMapping("/Cadastrar")
    public ResponseEntity<ApiResponce<Void>> CadastrarCobranca(
            CobrancaPlusCadastro dto){

        service.Cadastro(dto);

        return ResponseEntity.status(HttpStatus.CREATED).body(
                new ApiResponce<>(
                        true,
                        "Cobranca criada com sucesso",
                        null,
                        LocalDateTime.now()
                )
        );
    }

    @DeleteMapping("/Excluir")
    public ResponseEntity<ApiResponce<Void>> ExcluirCobranca(
            @RequestParam Integer id){

        service.Remover(id);

        return ResponseEntity.status(HttpStatus.OK).body(
                new ApiResponce<>(
                        true,
                        "Cobranca removida com sucesso",
                        null,
                        LocalDateTime.now()
                )
        );
    }

    @GetMapping("/Listar")
    public ResponseEntity<ApiResponce<List<CobrancaPlusRetornoDTO>>> ListarCobrancas(){
        List<CobrancaPlusRetornoDTO> list = service.Listar();

        return ResponseEntity.status(HttpStatus.OK).body(
                new ApiResponce<>(
                        true,
                        "Cobrancas listadas com sucesso",
                        list,
                        LocalDateTime.now()
                )
        );
    }
}
