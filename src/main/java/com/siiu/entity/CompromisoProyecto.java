package com.siiu.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@Table(name = "SIIU_COMPROMISO_POR_PROYECTO", schema = "BUPP")
public class CompromisoProyecto {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "compromisoProyectoSeq")
    @SequenceGenerator(
        name = "compromisoProyectoSeq",
        sequenceName = "SIIU_COMPROMISO_PROY_SEQ", // nombre de la secuencia en Oracle
        allocationSize = 1
    )
    @Column(name = "IDENTIFICADOR")
    private Long identificador;

    @Column(name = "COMPROMISO")
    private Long compromiso;

    @Column(name = "PROYECTO", length = 20)
    private String proyecto;

    @Column(name = "ASUMIDO")
    private Integer asumido;

    @Column(name = "RESULTADO_AUTOMATICO", length = 1)
    private String resultadoAutomatico;

    @Column(name = "DESCRIPCION_PROPIO", length = 1000)
    private String descripcionPropio;

    @Column(name = "COMENTARIO", length = 2000)
    private String comentario;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "FECHA_ESTIMADA")
    private Date fechaEstimada;

    @Column(name = "USUARIO_CREA", length = 20)
    private String usuarioCrea;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "FECHA_CREA")
    private Date fechaCrea;

    @Column(name = "USUARIO_MODIFICA", length = 20)
    private String usuarioModifica;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "FECHA_MODIFICA")
    private Date fechaModifica;

    @Column(name = "NOTIFICADO_FF", length = 1)
    private String notificadoFf;

    @Column(name = "ID_TIPO")
    private Long idTipo;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "FECHA_REAL")
    private Date fechaReal;

    @Column(name = "ESTADO", length = 20)
    private String estado;

    @Column(name = "ACTA_NRO", length = 20)
    private String actaNro;

    @Temporal(TemporalType.DATE)
    @Column(name = "ACTA_FECHA")
    private Date actaFecha;

    @Column(name = "META_EN_BPP")
    private Integer metaEnBpp;
}
