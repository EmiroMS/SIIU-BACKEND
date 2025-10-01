package com.siiu.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.util.Date;
import lombok.Data;

@Data
@Entity
@Table(name = "SIIU_ETAPA_PROYECTO")
public class EtapaProyecto {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "etapaProyectoSeq")
    @SequenceGenerator(
        name = "etapaProyectoSeq",
        sequenceName = "SIIU_ETAPA_PROYECTO_SEQ", // nombre de la secuencia en Oracle
        allocationSize = 1 // 1 para que no salte IDs
    )
    @Column(name = "IDENTIFICADOR")
    private Long identificador;

    @Column(name = "NOMBRE", length = 100)
    private String nombre;

    @Column(name = "DESCRIPCION", length = 200)
    private String descripcion;

    @Column(name = "EJECUCION_PRESUPUESTAL")
    private Integer ejecucionPresupuestal;

    @Column(name = "DURACION")
    private Integer duracion;

    @Temporal(TemporalType.DATE)
    @Column(name = "INICIO_VIGENCIA")
    private Date inicioVigencia;

    @Temporal(TemporalType.DATE)
    @Column(name = "FIN_VIGENCIA")
    private Date finVigencia;

    @Column(name = "CODIGO_PROYECTO", length = 20)
    private String codigoProyecto;

    @Column(name = "ORDEN")
    private Integer orden;

    @Column(name = "CONSECUTIVO")
    private Integer consecutivo;

    @Column(name = "USUARIO_CREA", length = 20)
    private String usuarioCrea;

    @Column(name = "FECHA_CREA")
    private Date fechaCrea;

    @Column(name = "USUARIO_ACTUALIZA", length = 20)
    private String usuarioActualiza;

    @Column(name = "FECHA_ACTUALIZA")
    private Date fechaActualiza;

}
