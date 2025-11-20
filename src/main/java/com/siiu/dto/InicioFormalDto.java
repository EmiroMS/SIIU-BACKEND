package com.siiu.dto;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class InicioFormalDto {
    private String codigoProyecto;
    private Integer duracion; // meses (opcional si se toma de SIIU_PROYECTO)
    private LocalDateTime fechaInicio;
    private LocalDateTime fechaFinalizacion;
    private String usuarioCrea;
    private String codigoAprobActaCodi;
    private LocalDateTime fechaAprobacion; // fecha aprobación (lectura desde SIIU_PROYECTO)
}