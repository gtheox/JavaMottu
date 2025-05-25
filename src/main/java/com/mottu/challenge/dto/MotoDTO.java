package com.mottu.challenge.dto;

import lombok.Data;

@Data
public class MotoDTO {

    private Long idMoto;
    private String modelo;
    private String placa;
    private Long idPatio;

    public MotoDTO(Long idMoto, String modelo, String placa, Long idPatio) {
        this.idMoto = idMoto;
        this.modelo = modelo;
        this.placa = placa;
        this.idPatio = idPatio;
    }
}
