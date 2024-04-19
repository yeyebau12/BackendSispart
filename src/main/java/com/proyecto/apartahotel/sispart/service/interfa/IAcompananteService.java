package com.proyecto.apartahotel.sispart.service.interfa;

import java.util.Date;

import com.proyecto.apartahotel.sispart.entity.TipDocumento;

public interface IAcompananteService {
	
	public boolean existsByTipoDocumentoAndNumDocumento(TipDocumento tipoDocumento, Long numDocumento);
	
	public int  calcEdad(Date fechaNacimiento);

}
