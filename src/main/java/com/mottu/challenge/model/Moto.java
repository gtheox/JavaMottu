package com.mottu.challenge.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Entity
@Data
public class Moto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idMoto;

    @NotBlank(message = "Modelo é obrigatório")
    private String modelo;

    @NotBlank(message = "Placa é obrigatória")
    @Size(min = 7, max = 10, message = "Placa deve ter entre 7 e 10 caracteres")
    @Pattern(regexp = "[A-Z0-9-]+", message = "Placa deve conter apenas letras maiúsculas, números e hífens")
    @Column(unique = true)
    private String placa;

    @ManyToOne
    @JoinColumn(name = "idPatio", nullable = false)
    private Patio patio;
}
