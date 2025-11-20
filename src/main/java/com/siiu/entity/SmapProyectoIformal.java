package com.siiu.entity;

import java.time.LocalDate;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "SMAP_PROYECTO_IFORMAL")
public class SmapProyectoIformal {
@Id
@Column(name = "ID")
private Long id;


@Column(name = "CODIGO_PROYECTO", length = 20)
private String codigoProyecto;


@Column(name = "DURACION")
private Integer duracion;


@Column(name = "FECHA_INICIO")
private LocalDateTime fechaInicio;


@Column(name = "FECHA_FINALIZACION")
private LocalDateTime fechaFinalizacion;


@Column(name = "NOTIFICADO_IF", length = 1)
private String notificadoIf;


@Column(name = "USUARIO_CREA", length = 20)
private String usuarioCrea;


@Column(name = "FECHA_CREA")
private LocalDateTime fechaCrea;


@Column(name = "ESTADO", length = 20)
private String estado;


@Column(name = "CODIGO_APROB_ACTA_CODI", length = 20)
private String codigoAprobActaCodi;


@Column(name = "FECHA_APROB_PROY_CODI")
private LocalDate fechaAprobProyCodi;
}