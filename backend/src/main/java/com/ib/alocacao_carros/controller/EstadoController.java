package com.ib.alocacao_carros.controller;

import com.ib.alocacao_carros.dto.EstadoDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.ib.alocacao_carros.service.EstadoService;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/Estados")
public class EstadoController {
    @Autowired
    private EstadoService estadoService;

    @GetMapping
    public ResponseEntity<ApiResponce<List<EstadoDTO>>> listarEstados(){
        List<EstadoDTO> estados = estadoService.Listar();

        return ResponseEntity.status(HttpStatus.OK).body(
                new ApiResponce<>(
                        true,
                        "Estados listados com sucesso",
                        estados,
                        LocalDateTime.now()
                )
        );
    }
}
