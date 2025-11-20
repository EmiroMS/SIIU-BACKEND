package com.siiu.entity;

import lombok.Data;

import java.time.LocalDate;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Entity
@Table(name = "SIIU_PROYECTO")
public class SiiuProyecto {
@Id
@Column(name = "CODIGO", length = 20)
private String codigo;


@Column(name = "ESTADO", length = 31)
private String estado;


@Column(name = "NOMBRE_CORTO", length = 200)
private String nombreCorto;


@Column(name = "DURACION")
private Integer duracion; // meses


@Column(name = "FECHA_APROBACION_RECHAZO")
private LocalDate fechaAprobacionRechazo;
}