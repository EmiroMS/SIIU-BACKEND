package com.siiu.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.siiu.entity.CompromisoProyecto;

@Repository
public interface CompromisoRepository extends JpaRepository<CompromisoProyecto, Long> {

    List<CompromisoProyecto> findByProyecto(String proyecto);
}
