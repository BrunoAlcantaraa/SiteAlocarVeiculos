package com.ib.alocacao_carros.controller;

import com.ib.alocacao_carros.dto.VeiculoCadastroDTO;
import com.ib.alocacao_carros.dto.VeiculoEdicaoDTO;
import com.ib.alocacao_carros.dto.VeiculoRetornoDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.ib.alocacao_carros.service.VeiculoService;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/Veiculos")
public class VeiculoController {
    @Autowired
    private VeiculoService veiculoService;

    @PostMapping("/Cadastrar")
    public ResponseEntity<ApiResponce<Void>> CadastrarVeiculo(
            @RequestBody VeiculoCadastroDTO dto){

        veiculoService.Cadastro(dto);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body( new ApiResponce<>(
                        true,
                        "Veiculo criado com sucesso",
                        null,
                        LocalDateTime.now()
                )
        );
    }

    @PutMapping("/Atualizar")
    public ResponseEntity<ApiResponce<Void>> AtualizarVeiculo(
            @RequestBody VeiculoEdicaoDTO dto){

        veiculoService.Editar(dto);

        return  ResponseEntity.status(HttpStatus.OK)
                .body( new ApiResponce<>(
                        true,
                        "Veiculo editado com sucesso",
                        null,
                        LocalDateTime.now()
                ));
    }

    @DeleteMapping("/Excluir")
    public ResponseEntity<ApiResponce<Void>> ExcluirVeiculo(
            @RequestBody Integer id){

        veiculoService.Remover(id);

        return  ResponseEntity.status(HttpStatus.OK)
                .body( new ApiResponce<>(
                        true,
                        "Veiculo removido com sucesso",
                        null,
                        LocalDateTime.now()
                ));
    }

    @GetMapping("/Listar")
    public ResponseEntity<ApiResponce<List<VeiculoRetornoDTO>>> ListarVeiculos(){
        List<VeiculoRetornoDTO> veiculos = veiculoService.Listar();

        return ResponseEntity.ok(
                new ApiResponce<>(
                        true,
                        "Veiculos listados com sucesso",
                        veiculos,
                        LocalDateTime.now()
                )
        );
    }
}
