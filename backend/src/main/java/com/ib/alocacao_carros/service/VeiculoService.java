package com.ib.alocacao_carros.service;

import com.ib.alocacao_carros.dto.VeiculoCadastroDTO;
import com.ib.alocacao_carros.dto.VeiculoEdicaoDTO;
import com.ib.alocacao_carros.dto.VeiculoRetornoDTO;
import com.ib.alocacao_carros.entity.*;
import com.ib.alocacao_carros.repository.*;
import com.ib.alocacao_carros.exception.AutorizacaoNegada;
import com.ib.alocacao_carros.exception.RecursoNaoEncontrado;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.text.Normalizer;
import java.util.List;
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

    public VeiculoRetornoDTO ConverterParaDTO(Veiculo veiculo){
        VeiculoRetornoDTO dto = new VeiculoRetornoDTO();

        dto.setCor(veiculo.getCor());
        dto.setPlaca(veiculo.getPlaca());
        dto.setAnoFabricacao(veiculo.getModelo().getAnoFab());
        dto.setKmAtual(veiculo.getKmsAtual());
        dto.setRenavam(veiculo.getRenavam());
        dto.setTipo(veiculo.getTipoVeiculo());
        dto.setModelo(veiculo.getModelo().getNomeModelo());
        dto.setMarca(veiculo.getModelo().getMarca().getNomeMarca());
        dto.setCombustivel(veiculo.getCombustivel().getNomeCombustivel());
        dto.setUrlImagem(veiculo.getUrlImagem());

        return dto;
    }

    public void Cadastro(VeiculoCadastroDTO dto){
        if(!funcionarioService.VerificarPermissao(dto.getIdFunc())) throw new AutorizacaoNegada("Ação não autorizada");
        Marca marca = new Marca();
        Modelo modelo = new Modelo();

        //tira os acentos e coloca em minusculo
        String nomeMarca = Normalizer.normalize(dto.getMarca(), Normalizer.Form.NFD).toLowerCase();
        String nomeModelo = Normalizer.normalize(dto.getModelo(), Normalizer.Form.NFD).toLowerCase();

        if(marcaRepository.findByNomeMarca(nomeMarca) == null){ //caso a marca não existe no banco, ele a cadastra
            marca.setNomeMarca(nomeMarca);
            marcaRepository.save(marca); //da insert na marca nova
        }else if(modeloRepository.findByNomeModeloAndAnoFab(nomeModelo, dto.getAnoFabricacao()) == null){ //caso não tenha esse modelo no banco
            modelo.setNomeModelo(nomeModelo);
            modelo.setAnoFab(dto.getAnoFabricacao());
            modelo.setValorDiario(new BigDecimal("0")); //define por padrão o valor de 0
            modelo.setMarca(marcaRepository.findByNomeMarca(nomeMarca));
            modeloRepository.save(modelo); //da o insert
        }else{ //caso ja exista o modelo e a marca
            modelo = modeloRepository.findByNomeModeloAndAnoFab(nomeModelo, dto.getAnoFabricacao());
        }

        //cria o objeto e pega os dois objetos necessários
        Veiculo veiculo = new Veiculo();
        Combustivel combustivel = combustivelRepository.findByNomeCombustivel(dto.getCombustivel());
        StatusVeiculo statusVeiculo = statusVeiculoRepository.findByNomeStatusVeiculo("Disponível"); //ao cadastrar, o carro vai direto para disponível

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
        if(funcionarioService.VerificarPermissao(dto.getIdFunc())) throw new AutorizacaoNegada("Ação não autorizada");

        Optional<Veiculo> veiculo = veiculoRepository.findById(dto.getIdVeiculo());

        if(veiculo.isPresent()){
            StatusVeiculo status = statusVeiculoRepository.findByNomeStatusVeiculo(dto.getStatus());

            veiculo.get().setPlaca(dto.getPlaca());
            veiculo.get().setCor(dto.getCor());
            veiculo.get().setKmsAtual(dto.getKmAtual());
            veiculo.get().setTipoVeiculo(dto.getTipo());
            veiculo.get().setUrlImagem(dto.getUrlImagem());
            veiculo.get().setStatusVeiculo(status);
            veiculoRepository.save(veiculo.get());

        }else{
            throw new RecursoNaoEncontrado("Veiculo não encontrado, verifique o id");
        }
    }

    public void Remover(Integer id){
        if(!veiculoRepository.existsById(id)) throw new RecursoNaoEncontrado("Veiculo não encontrado");
        veiculoRepository.deleteById(id);
    }

    public List<VeiculoRetornoDTO> Listar(){
        List<Veiculo> veiculos = veiculoRepository.findAll();

        return veiculos.stream().map(this::ConverterParaDTO).toList();
    }
}
