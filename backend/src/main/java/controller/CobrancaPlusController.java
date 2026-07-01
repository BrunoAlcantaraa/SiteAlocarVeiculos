package controller;

import dto.CobrancaPlusCadastro;
import dto.CobrancaPlusRetornoDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import service.CobrancaPlusService;

import java.time.LocalDateTime;
import java.util.List;

@RestController
public class CobrancaPlusController {
    @Autowired
    private CobrancaPlusService service;

    @PostMapping
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

    @DeleteMapping
    public ResponseEntity<ApiResponce<Void>> ExcluirCobranca(
            @RequestParam Long id){

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

    @GetMapping
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
