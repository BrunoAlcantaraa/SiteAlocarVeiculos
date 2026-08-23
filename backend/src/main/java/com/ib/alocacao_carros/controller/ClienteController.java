package com.ib.alocacao_carros.controller;

import com.ib.alocacao_carros.dto.ClienteCadastroDTO;
import com.ib.alocacao_carros.dto.ClienteEdicaoDTO;
import com.ib.alocacao_carros.dto.ClienteRetornoDTO;
import com.ib.alocacao_carros.dto.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.ib.alocacao_carros.service.ClienteService;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/Clientes")
public class ClienteController {
    @Autowired
    private ClienteService clienteService;

    @PostMapping("/Cadastrar")
    public ResponseEntity<ApiResponce<Void>> cadastrarCliente(
            @RequestBody ClienteCadastroDTO dto) {

        clienteService.Cadastrar(dto);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new ApiResponce<>(
                        true,
                        "Cliente cadastrado com sucesso",
                        null,
                        LocalDateTime.now()
                        )
                );
    }

    @PutMapping("/Atualizar")
    public ResponseEntity<ApiResponce<Void>> atualizarCliente(
            @RequestBody ClienteEdicaoDTO dto){

        clienteService.Editar(dto);

        return ResponseEntity.status(HttpStatus.OK)
                .body(new ApiResponce<>(
                        true,
                        "Cliente editado com sucesso",
                        null,
                        LocalDateTime.now()
                ));
    }

    @GetMapping("/Listar")
    public ResponseEntity<ApiResponce<List<ClienteRetornoDTO>>> listarCliente(){
        List<ClienteRetornoDTO> clientes = clienteService.Listar();

        return ResponseEntity.ok(
                new ApiResponce<>(
                        true,
                        "Clientes listados com sucesso",
                        clientes,
                        LocalDateTime.now()
                )
        );
    }

    @GetMapping("/teste")
    public String teste() {
        return "API funcionando!";
    }
}
