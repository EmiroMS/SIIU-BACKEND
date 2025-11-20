package com.siiu.dto;

import java.util.Date;

import lombok.Data;

@Data
public class EditarCompromisoDTO {
    private Date fechaEstimada;
    private String notas;
    private String usuario;
}
