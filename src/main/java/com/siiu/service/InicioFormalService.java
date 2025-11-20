package com.siiu.service;

import com.siiu.dto.InicioFormalDto;

public interface InicioFormalService {
InicioFormalDto getFormulario(String codigoProyecto);
InicioFormalDto guardarInicioFormal(InicioFormalDto dto);
byte[] descargarDocumentoAprobacion(String codigoProyecto);
}