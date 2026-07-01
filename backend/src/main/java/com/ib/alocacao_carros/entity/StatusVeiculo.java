package com.ib.alocacao_carros.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "status_veiculo")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class StatusVeiculo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_status")
    private int idStatusVeiculo;

    @Column(name = "nome_status")
    private String nomeStatusVeiculo;
}
