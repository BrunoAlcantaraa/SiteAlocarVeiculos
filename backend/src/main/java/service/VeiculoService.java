package service;

import dto.VeiculoCadastroDTO;
import entity.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import repository.*;

import java.math.BigDecimal;
import java.text.Normalizer;

@Service
@RequiredArgsConstructor
public class VeiculoService {
    //declaração dos repositorios
    private final VeiculoRepository veiculoRepository;
    private final ModeloRepository modeloRepository;
    private final MarcaRepository marcaRepository;
    private final StatusVeiculoRepository statusVeiculoRepository;
    private final CombustivelRepository combustivelRepository;

    public void Cadastro(VeiculoCadastroDTO dto){
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
        veiculoRepository.save(veiculo); //da insert
    }
}
