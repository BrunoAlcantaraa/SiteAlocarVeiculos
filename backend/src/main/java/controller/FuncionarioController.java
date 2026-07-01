package controller;

import dto.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import service.FuncionarioService;

import java.time.LocalDateTime;
import java.util.List;

@RestController
public class FuncionarioController {
    @Autowired
    private FuncionarioService funcionarioService;

    @PostMapping
    public ResponseEntity<ApiResponce<Void>> CadastrarFuncionario(
            @RequestBody FuncionarioCadastroDTO dto){

        funcionarioService.Cadastrar(dto);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new ApiResponce<>(
                        true,
                        "Funcionário cadastrado com sucesso",
                        null,
                        LocalDateTime.now()
        ));
    }

    @PutMapping
    public ResponseEntity<ApiResponce<Void>> AtualizarFuncionario(
            @RequestBody FuncionarioEdicaoDTO dto){

        funcionarioService.Editar(dto);

        return ResponseEntity.status(HttpStatus.OK)
                .body(new ApiResponce<>(
                        true,
                        "Funcionário editado com sucesso",
                        null,
                        LocalDateTime.now()
                ));
    }

    @GetMapping
    public ResponseEntity<ApiResponce<Long>> RealizarLogin(
            @RequestBody LoginDTO dto){

        Long id = funcionarioService.verificaSenha(dto);

        return  ResponseEntity.ok(
                new ApiResponce<>(
                        true,
                        "se null então o login falhou",
                        id,
                        LocalDateTime.now()
                )
        );
    }

    @GetMapping
    public ResponseEntity<ApiResponce<List<FuncionarioRetornoDTO>>> listarFuncionario(){
        List<FuncionarioRetornoDTO> funcionarios = funcionarioService.Listar();

        return ResponseEntity.ok(
                new ApiResponce<>(
                        true,
                        "Funcionarios listados com sucesso",
                        funcionarios,
                        LocalDateTime.now()
                )
        );
    }
}
