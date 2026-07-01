package controller;

import dto.EnderecoCadastroDTO;
import dto.PessoaCadastroDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import service.ClienteService;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/clientes")
public class ClienteController {
    @Autowired
    private ClienteService clienteService;

    @PostMapping
    public ResponseEntity<ApiResponce<Void>> cadastrarCliente(
            @RequestBody PessoaCadastroDTO pesDto, EnderecoCadastroDTO enderecoDto ) {
        clienteService.Cadastrar(pesDto, enderecoDto);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new ApiResponce<>(
                        true,
                        "Cliente cadastrado com sucesso",
                        null,
                        LocalDateTime.now()
                        )
                );
    }
}
