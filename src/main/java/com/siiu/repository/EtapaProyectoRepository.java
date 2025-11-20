package com.siiu.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.siiu.entity.EtapaProyecto;

public interface EtapaProyectoRepository extends JpaRepository<EtapaProyecto, Long>{
	List<EtapaProyecto> findByCodigoProyectoOrderByOrden(String codigoProyecto);
}
