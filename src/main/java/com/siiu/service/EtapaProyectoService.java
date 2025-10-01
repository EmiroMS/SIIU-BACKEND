package com.siiu.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.siiu.entity.EtapaProyecto;
import com.siiu.repository.EtapaProyectoRepository;

@Service
public class EtapaProyectoService {

	@Autowired
	private EtapaProyectoRepository repository;

	public EtapaProyecto guardar(EtapaProyecto etapa) {
		return repository.save(etapa);
	}

	public List<EtapaProyecto> listar() {
		return repository.findAll();
	}
}
