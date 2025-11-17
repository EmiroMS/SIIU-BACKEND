package com.siiu.repository;

import com.siiu.entity.Proyecto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface ProyectoRepository extends JpaRepository<Proyecto, String> {

    @Query("SELECT p FROM Proyecto p " +
           "WHERE (:codigo IS NULL OR p.codigo = :codigo) " +
           "AND (:centro IS NULL OR CAST(p.centroGestion AS string) = :centro) " +
           "AND (:estado IS NULL OR p.estado = :estado) " +
           "AND (:convocatoria IS NULL OR CAST(p.convocatoria AS string) = :convocatoria) " +
           "AND (:proceso IS NULL OR CAST(p.procesoSeleccion AS string) = :proceso) " +
           "AND (:tipo IS NULL OR CAST(p.subtipoProyecto AS string) = :tipo)")
    List<Proyecto> filtrar(
            @Param("codigo") String codigo,
            @Param("centro") String centro,
            @Param("estado") String estado,
            @Param("convocatoria") String convocatoria,
            @Param("proceso") String proceso,
            @Param("tipo") String tipo
    );
}