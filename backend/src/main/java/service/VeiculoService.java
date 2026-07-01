package service;

import dto.VeiculoCadastroDTO;
import dto.VeiculoEdicaoDTO;
import entity.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import repository.*;

import java.math.BigDecimal;
import java.text.Normalizer;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class VeiculoService {
    //declaração dos repositorios
    private final VeiculoRepository veiculoRepository;
    private final ModeloRepository modeloRepository;
    private final MarcaRepository marcaRepository;
    private final StatusVeiculoRepository statusVeiculoRepository;
    private final CombustivelRepository combustivelRepository;

    private FuncionarioService funcionarioService;

    public void Cadastro(VeiculoCadastroDTO dto){
        if(!funcionarioService.VerificarPermissao(dto.getIdFunc())) throw new RuntimeException("Ação não autorizada");
        Marca marca = new Marca();
        Modelo modelo = new Modelo();

        //tira os acentos e coloca em minusculo
        String nomeMarca = Normalizer.normalize(dto.getMarca(), Normalizer.Form.NFD).toLowerCase();
        String nomeModelo = Normalizer.normalize(dto.getModelo(), Normalizer.Form.NFD).toLowerCase();

        if(marcaRepository.findByNome(nomeMarca) == null){ //caso a marca não existe no banco, ele a cadastra
            marca.setNomeMarca(nomeMarca);
            marcaRepository.save(marca); //da insert na marca nova
        }else if(modeloRepository.findByNomeAndAnoFab(nomeModelo, dto.getAnoFabricacao()) == null){ //caso não tenha esse modelo no banco
            modelo.setNomeModelo(nomeModelo);
            modelo.setAnoFab(dto.getAnoFabricacao());
            modelo.setValorDiario(new BigDecimal("0")); //define por padrão o valor de 0
            modelo.setMarca(marcaRepository.findByNome(nomeMarca));
            modeloRepository.save(modelo); //da o insert
        }else{ //caso ja exista o modelo e a marca
            modelo = modeloRepository.findByNomeAndAnoFab(nomeModelo, dto.getAnoFabricacao());
        }

        //cria o objeto e pega os dois objetos necessários
        Veiculo veiculo = new Veiculo();
        Combustivel combustivel = combustivelRepository.findByNome(dto.getCombustivel());
        StatusVeiculo statusVeiculo = statusVeiculoRepository.findByNome("Disponível"); //ao cadastrar, o carro vai direto para disponível

        //define todos os atributos
        veiculo.setModelo(modelo);
        veiculo.setRenavam(dto.getRenavam());
        veiculo.setPlaca(dto.getPlaca());
        veiculo.setCombustivel(combustivel);
        veiculo.setStatusVeiculo(statusVeiculo);
        veiculo.setCor(dto.getCor());
        veiculo.setKmsAtual(dto.getKmAtual());
        veiculo.setTipoVeiculo(dto.getTipo());
        veiculo.setUrlImagem(dto.getUrlImagem());
        veiculoRepository.save(veiculo); //da insert
    }

    public void Editar(VeiculoEdicaoDTO dto){
        if(funcionarioService.VerificarPermissao(dto.getIdFunc())) throw new RuntimeException("Ação não autorizada");

        Optional<Veiculo> veiculo = veiculoRepository.findById(dto.getIdVeiculo());

        if(veiculo.isPresent()){
            StatusVeiculo status = statusVeiculoRepository.findByNome(dto.getStatus());

            veiculo.get().setPlaca(dto.getPlaca());
            veiculo.get().setCor(dto.getCor());
            veiculo.get().setKmsAtual(dto.getKmAtual());
            veiculo.get().setTipoVeiculo(dto.getTipo());
            veiculo.get().setUrlImagem(dto.getUrlImagem());
            veiculo.get().setStatusVeiculo(status);
            veiculoRepository.save(veiculo.get());

        }else{
            throw new RuntimeException("Veiculo não encontrado, verifique o id");
        }
    }

    public void Remover(Long id){
        veiculoRepository.deleteById(id); //da delete com base no id
    }
}
