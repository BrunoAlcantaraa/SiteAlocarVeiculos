package com.ib.alocacao_carros.service;

import com.ib.alocacao_carros.dto.EstadoDTO;
import com.ib.alocacao_carros.entity.Estado;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import com.ib.alocacao_carros.repository.EstadoRepository;

import java.util.List;

@Service
@AllArgsConstructor
public class EstadoService {
    private EstadoRepository estadoRepository;

    public EstadoDTO ConverterParaDTO(Estado estado) {
        EstadoDTO dto = new EstadoDTO();
        dto.setNome(estado.getNomeEstado());
        dto.setSigla(estado.getSigla());
        return dto;
    }

    public List<EstadoDTO> Listar() {
        List<Estado> estados = estadoRepository.findAll();

        return estados.stream().map(this::ConverterParaDTO).toList();
    }
}
