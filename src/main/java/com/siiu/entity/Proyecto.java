package com.siiu.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@Table(name = "SIIU_PROYECTO", schema = "BUPP")
public class Proyecto {

    @Id
    @Column(name = "CODIGO", length = 20)
    private String codigo;

    @Column(name = "CONVOCATORIA")
    private Integer convocatoria;

    @Column(name = "MODALIDAD_CONVOCATORIA")
    private Integer modalidadConvocatoria;

    @Column(name = "PROCESO_SELECCION")
    private Long procesoSeleccion;

    @Column(name = "SUBTIPO_PROYECTO")
    private Integer subtipoProyecto;

    @Column(name = "ETAPA_ACTUAL")
    private Integer etapaActual;

    @Column(name = "INSTANCIA_ADMTIVA_ACTUAL")
    private Integer instanciaAdmtivaActual;

    @Column(name = "CENTRO_GESTION")
    private Integer centroGestion;

    @Temporal(TemporalType.DATE)
    @Column(name = "FECHA_ENVIO_CENTRO")
    private Date fechaEnvioCentro;

    @Column(name = "RESPONSABLE", length = 16)
    private String responsable;

    @Column(name = "SELECTOR_RESPONSABLE", length = 1)
    private String selectorResponsable;

    @Column(name = "SECCIONAL", length = 8)
    private String seccional;

    @Column(name = "SELECTOR_SECCIONAL", length = 1)
    private String selectorSeccional;

    @Column(name = "ESTADO", length = 31)
    private String estado;

    @Temporal(TemporalType.DATE)
    @Column(name = "FECHA_APROBACION_RECHAZO")
    private Date fechaAprobacionRechazo;

    @Temporal(TemporalType.DATE)
    @Column(name = "FECHA_REGISTRO")
    private Date fechaRegistro;

    @Column(name = "NOMBRE_CORTO", length = 200)
    private String nombreCorto;

    @Column(name = "NOMBRE_COMPLETO", length = 600)
    private String nombreCompleto;

    @Column(name = "PALABRAS_CLAVES", length = 200)
    private String palabrasClaves;

    @Column(name = "DURACION")
    private Integer duracion;

    @Column(name = "LUGAR_EJECUCION", length = 100)
    private String lugarEjecucion;

    @Column(name = "MONEDA", length = 3)
    private String moneda;

    @Column(name = "PENDIENTE_AJUSTE_PPTO")
    private Integer pendienteAjustePpto;

    @Column(name = "PERIODO_CRONOGRAMA", length = 6)
    private String periodoCronograma;

    @Column(name = "NOTIFICADO_RP", length = 1)
    private String notificadoRp;

    @Column(name = "ES_ELEGIBLE_JURADO")
    private Integer esElegibleJurado;

    @Column(name = "CLASE_PROYECTO")
    private Integer claseProyecto;

    @Column(name = "NIVEL_PROYECTO")
    private Integer nivelProyecto;

    @Column(name = "SUBNIVEL_PROYECTO")
    private Integer subnivelProyecto;

    @Column(name = "TIPO_PROYECTO_MACRO")
    private Integer tipoProyectoMacro;

    @Column(name = "REQUIERE_AVAL_BIOETICA")
    private Integer requiereAvalBioetica;

    @Column(name = "COMITE_BIOETICA")
    private Integer comiteBioetica;

    @Column(name = "PENDIENTE_REVISION_CENTRO", length = 1)
    private String pendienteRevisionCentro;
}
