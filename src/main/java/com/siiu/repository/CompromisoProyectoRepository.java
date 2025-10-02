package com.siiu.repository;

import com.siiu.entity.CompromisoProyecto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CompromisoProyectoRepository extends JpaRepository<CompromisoProyecto, Long> {

    List<CompromisoProyecto> findByProyecto(String proyecto);
}