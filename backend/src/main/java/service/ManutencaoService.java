package service;

import dto.ManutencaoCadastroDTO;
import dto.ManutencaoCadastroPosteriorDTO;
import entity.Manutencao;
import entity.StatusVeiculo;
import entity.Veiculo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import repository.ManutencaoRepository;
import repository.StatusVeiculoRepository;
import repository.VeiculoRepository;

import java.util.Objects;

@Service
@RequiredArgsConstructor
public class ManutencaoService {
    //decaração dos repositorios
    private final StatusVeiculoRepository statusVeiculoRepository;
    private final ManutencaoRepository manutencaoRepository;
    private final VeiculoRepository veiculoRepository;

    public void Cadastro(ManutencaoCadastroDTO dto){ //cadastro quando ele vai para manutenção
        Manutencao manutencao = new Manutencao();
        Veiculo veiculo = veiculoRepository.findByPlaca(dto.getPlacaVeiculo());

        if(veiculo == null){
            throw new RuntimeException("Veiculo nao encontrado, verifique o numero da placa e tente novamente");
        }

        manutencao.setDescricaoManutencao(dto.getDescricao());
        manutencao.setDataInicio(dto.getDataInicio());
        manutencao.setVeiculo(veiculo);
        manutencaoRepository.save(manutencao);

        veiculo.setStatusVeiculo(statusVeiculoRepository.findByNome("Em manutenção")); //já que o cadastro foi criado, então o carro entra para manutenção
        veiculoRepository.save(veiculo); //da update
    }

    public void Cadastro(ManutencaoCadastroPosteriorDTO dto){ //cadastro na volta do carro
        Manutencao manutencao = new Manutencao();
        Veiculo veiculo = veiculoRepository.findByPlaca(dto.getPlacaVeiculo());

        if(veiculo == null){
            throw new RuntimeException("Veiculo nao encontrado, verifique o numero da placa e tente novamente");
        }

        manutencao.setDescricaoManutencao(dto.getDescricao());
        manutencao.setDataFim(dto.getDataFim());
        manutencao.setDataInicio(dto.getDataInicio());
        manutencao.setValorManutencao(dto.getValor());
        manutencao.setVeiculo(veiculo);
        manutencaoRepository.save(manutencao);

        /*
        como está sendo feito um cadastro posterior a manutenção, acho necessário verificar se o carro ficará disponível
        caso ele não esteja disponivel, então é atualizado o seu status
         */
        StatusVeiculo statusVeiculo = veiculo.getStatusVeiculo();
        if(!Objects.equals(statusVeiculo.getNomeStatusVeiculo(), "Disponível")){
            veiculo.setStatusVeiculo(statusVeiculoRepository.findByNome("Disponível")); //atualiza o status
            veiculoRepository.save(veiculo); //da update
        }
    }
}
