package com.mottu.challenge.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import lombok.Data;

import java.util.List;

@Entity
@Data
public class Patio {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idPatio;

    @NotBlank(message = "Localização é obrigatória")
    private String localizacao;

    @Positive(message = "Capacidade deve ser maior que zero")
    private Integer capacidade;

    @OneToMany(mappedBy = "patio", cascade = CascadeType.ALL)
    private List<Moto> motos;
}
