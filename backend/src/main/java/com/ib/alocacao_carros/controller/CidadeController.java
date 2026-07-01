package com.ib.alocacao_carros.controller;

import com.ib.alocacao_carros.dto.CidadeDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.ib.alocacao_carros.service.CidadeService;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/cidades")
public class CidadeController {
    @Autowired
    private CidadeService cidadeService;

    @GetMapping
    public ResponseEntity<ApiResponce<List<CidadeDTO>>> listarCidades(){
        List<CidadeDTO> cidades = cidadeService.Listar();

        return ResponseEntity.status(HttpStatus.OK).body(
                new ApiResponce<>(
                        true,
                        "Cidades listadas com sucesso",
                        cidades,
                        LocalDateTime.now()
                )
        );
    }
}
