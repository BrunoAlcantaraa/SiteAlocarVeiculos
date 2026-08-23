package com.ib.alocacao_carros.service;

import com.ib.alocacao_carros.dto.*;
import com.ib.alocacao_carros.entity.*;
import com.ib.alocacao_carros.repository.*;
import com.ib.alocacao_carros.exception.AcaoInvalida;
import com.ib.alocacao_carros.exception.AutorizacaoNegada;
import com.ib.alocacao_carros.exception.DadosInvalidos;
import com.ib.alocacao_carros.exception.RecursoNaoEncontrado;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
@RequiredArgsConstructor
public class LocacaoService {
    //declaracao dos repositories
    private final ClienteRepository clienteRepository;
    private final VeiculoRepository veiculoRepository;
    private final LocacaoRepository locacaoRepository;
    private final StatusLocacaoRepository statusLocacaoRepository;
    private final FuncionarioRepository funcionarioRepository;
    private final StatusVeiculoRepository statusVeiculoRepository;

    private FuncionarioService funcionarioService;
    private ClienteService clienteService;
    private VeiculoService veiculoService;
    private FuncionarioService funcFuncionarioService;

    public LocacaoRetornoDTO ConverterParaDTO(Locacao locacao) {
        LocacaoRetornoDTO dto = new LocacaoRetornoDTO();
        ClienteRetornoDTO cli = clienteService.ConverterParaDTO(locacao.getCliente());
        FuncionarioRetornoDTO func = funcionarioService.ConverterParaDTO(locacao.getFuncionario());
        VeiculoRetornoDTO ve = veiculoService.ConverterParaDTO(locacao.getVeiculo());

        dto.setCliente(cli);
        dto.setFuncionario(func);
        dto.setStatus(locacao.getStatusLocacao().getNomeStatusLocacao());
        dto.setVeiculo(ve);
        dto.setDataSaida(locacao.getDataSaida());
        dto.setDataRetornoPrevista(locacao.getDataRetornoPrevisto());
        dto.setDataRetornoReal(locacao.getDataRetornoReal());
        dto.setKmSaida(locacao.getKmsSaida());
        dto.setKmRetorno(locacao.getKmsRetorno());
        dto.setCobrancas(locacao.getCobrancaPlus());

        return dto;
    }

    public void Cadastro(LocacaoCadastroDTO dto) {
        Locacao locacao = new Locacao();
        Cliente cliente = clienteRepository.findByPessoa_CPF(dto.getCpfCliente());
        Funcionario func = funcionarioRepository.findByPessoa_CPF(dto.getCpfFuncionario());
        Veiculo veiculo = veiculoRepository.findByPlaca(dto.getPlacaVeiculo());
        StatusLocacao statusLocacao = statusLocacaoRepository.findByNomeStatusLocacao("Em Aberto");

        if (veiculo == null){
            throw new RecursoNaoEncontrado("Veiculo não encontrado, verifique a placa digitada e tente novamente");
        }else if(cliente == null){
            throw new RecursoNaoEncontrado("Cliente não encontrado, verifique os dados informados");
        }else if(func == null){
            throw new RecursoNaoEncontrado("Funcionário não encontrado, verifique os dados informados");
        }

        //assinala os atributos
        locacao.setDataSaida(dto.getDataSaida());
        locacao.setDataRetornoPrevisto(dto.getDataRetornoPrevista());
        locacao.setKmsSaida(dto.getKmSaida());
        locacao.setVeiculo(veiculo);
        locacao.setCliente(cliente);
        locacao.setFuncionario(func);
        locacao.setStatusLocacao(statusLocacao);
        locacaoRepository.save(locacao); //da insert

        StatusVeiculo statusVeiculo = statusVeiculoRepository.findByNomeStatusVeiculo("Alugado");
        veiculo.setStatusVeiculo(statusVeiculo); //atualiza o status do veiculo
        veiculoRepository.save(veiculo); //da update no veiculo
    }

    public void Concluir(LocacaoConclusaoDTO dto) {
        Optional<Locacao> locacao = locacaoRepository.findById(dto.getId());

        if (locacao.isPresent()) {
            if(locacao.get().getKmsSaida() > dto.getKms()){ //verifica se a kilometragem de retorno é menor que a de saida
                throw new DadosInvalidos("Kilometragem de retorno errada, verifique e tente novamente");
            }else if(locacao.get().getDataSaida().isAfter(dto.getDataRetorno())){ //verifica se a data de retorno é anterior a de saida
                throw new DadosInvalidos("Data de retorno inválida,  verifique os dados informados e tente novamente");
            }

            StatusLocacao statusLocacao = statusLocacaoRepository.findByNomeStatusLocacao("Finalizada");

            locacao.get().setKmsRetorno(dto.getKms());
            locacao.get().setDataRetornoReal(dto.getDataRetorno());
            locacao.get().setStatusLocacao(statusLocacao); //atualiza o status
            locacaoRepository.save(locacao.get()); //da update

            Veiculo veiculo = locacao.get().getVeiculo();
            StatusVeiculo status = statusVeiculoRepository.findByNomeStatusVeiculo("Disponível");
            veiculo.setStatusVeiculo(status); //atualiza o status do veiculo
            veiculo.setKmsAtual(dto.getKms()); //atualiza a kilometragem do carro
            veiculoRepository.save(veiculo); //da update
        }else{
            throw new RecursoNaoEncontrado("Locacao não encontrada, verifica o id ai brunin");
        }
    }

    public void Editar(LocacaoEdicaoDTO dto){
        if(!funcionarioService.VerificarPermissao(dto.getIdFunc())) throw new AutorizacaoNegada("Ação não autorizada");

        Optional<Locacao> locacao = locacaoRepository.findById(dto.getId());
        Veiculo veiculoAntigo = new Veiculo();

        if (locacao.isPresent()) {
            if(locacao.get().getStatusLocacao().getNomeStatusLocacao().equals("Finalizada")){
                throw new AcaoInvalida("Locação já finalizada, não é possível editar locações finalizadas");
            }

            Veiculo veiculo = veiculoRepository.findByPlaca(dto.getPlacaVeiculo());
            if (!veiculo.getPlaca().equals(locacao.get().getVeiculo().getPlaca())) { //caso seja um carro diferente, vaia tualizar os status de cada
                veiculoAntigo = locacao.get().getVeiculo();
                veiculoAntigo.setStatusVeiculo(statusVeiculoRepository.findByNomeStatusVeiculo("Disponível")); //atualiza o status
                veiculoRepository.save(veiculoAntigo); //da update
            }

            //atualiza os valores
            locacao.get().setDataSaida(dto.getDataSaida());
            locacao.get().setDataRetornoPrevisto(dto.getDataRetornoPrevista());
            locacao.get().setKmsSaida(dto.getKms());
            locacao.get().setVeiculo(veiculo);
            locacaoRepository.save(locacao.get()); //da update

            veiculo.setStatusVeiculo(statusVeiculoRepository.findByNomeStatusVeiculo("Alugado"));
            veiculoRepository.save(veiculo); //da update
        }else{
            throw new RecursoNaoEncontrado("Locacao não encontrada, verifica o id ai brunin");
        }
    }

    public void Cancelar(Integer id){
        Optional<Locacao> locacao = locacaoRepository.findById(id);

        if(locacao.isPresent()){
            Veiculo veiculo = locacao.get().getVeiculo();
            veiculo.setStatusVeiculo(statusVeiculoRepository.findByNomeStatusVeiculo("Disponível")); //atualiza o status do carro
            veiculoRepository.save(veiculo); //da update
            locacaoRepository.deleteById(id); //da delete
        }else{
            throw new RecursoNaoEncontrado("Locacao não encontrada, verificar id");
        }
    }

    public List<LocacaoRetornoDTO> Listar(){
        List<Locacao> locacoes = locacaoRepository.findAll();

        return locacoes.stream().map(this::ConverterParaDTO).toList();
    }
}
