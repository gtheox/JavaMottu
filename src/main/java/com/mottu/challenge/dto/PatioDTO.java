package com.mottu.challenge.dto;

import lombok.Data;

@Data
public class PatioDTO {

    private Long idPatio;
    private String localizacao;
    private Integer capacidade;

    // Construtor adicionado
    public PatioDTO(Long idPatio, String localizacao, Integer capacidade) {
        this.idPatio = idPatio;
        this.localizacao = localizacao;
        this.capacidade = capacidade;
    }
}
