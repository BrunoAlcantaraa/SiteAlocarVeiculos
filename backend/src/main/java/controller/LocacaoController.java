package controller;

import dto.LocacaoCadastroDTO;
import dto.LocacaoConclusaoDTO;
import dto.LocacaoEdicaoDTO;
import dto.LocacaoRetornoDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import service.LocacaoService;

import java.time.LocalDateTime;
import java.util.List;

@RestController
public class LocacaoController {
    @Autowired
    private LocacaoService locacaoService;

    @PostMapping
    public ResponseEntity<ApiResponce<Void>> CadastrarLocacao(
            @RequestBody LocacaoCadastroDTO dto){

        locacaoService.Cadastro(dto);

        return ResponseEntity.status(HttpStatus.CREATED).body(
                new ApiResponce<>(
                        true,
                        "Locacao Criada com sucesso",
                        null,
                        LocalDateTime.now()
                )
        );
    }

    @PutMapping
    public ResponseEntity<ApiResponce<Void>> ConcluirLocacao(
            @RequestBody LocacaoConclusaoDTO dto){

        locacaoService.Concluir(dto);

        return  ResponseEntity.status(HttpStatus.OK).body(
                new ApiResponce<>(
                        true,
                        "Locacao concluida com sucesso",
                        null,
                        LocalDateTime.now()
                )
        );
    }

    @PutMapping
    public ResponseEntity<ApiResponce<Void>> AlterarLocacao(
            @RequestBody LocacaoEdicaoDTO dto){

        locacaoService.Editar(dto);

        return  ResponseEntity.status(HttpStatus.OK).body(
                new ApiResponce<>(
                        true,
                        "Locacao Alterada com sucesso",
                        null,
                        LocalDateTime.now()
                )
        );
    }

    @DeleteMapping
    public ResponseEntity<ApiResponce<Void>> ExcluirLocacao(
            @RequestBody Long id){

        locacaoService.Cancelar(id);

        return  ResponseEntity.status(HttpStatus.OK).body(
                new ApiResponce<>(
                        true,
                        "Locacao cancelada com sucesso",
                        null,
                        LocalDateTime.now()
                )
        );
    }

    @GetMapping
    public ResponseEntity<ApiResponce<List<LocacaoRetornoDTO>>> ListarLocacoes(){
        List<LocacaoRetornoDTO> locacoes = locacaoService.Listar();

        return ResponseEntity.status(HttpStatus.OK).body(
                new ApiResponce<>(
                        true,
                        "Locacoes listadas com sucesso",
                        locacoes,
                        LocalDateTime.now()
                )
        );
    }
}
