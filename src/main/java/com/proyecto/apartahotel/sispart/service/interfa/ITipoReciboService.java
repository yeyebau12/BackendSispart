package com.proyecto.apartahotel.sispart.service.interfa;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


import com.proyecto.apartahotel.sispart.entity.TipoRecibo;



public interface ITipoReciboService {
	
	public List<TipoRecibo> findAll();
	
	public Page<TipoRecibo> findAll(Pageable pageable);

	public void save(TipoRecibo tipoRecibo);

	public TipoRecibo findByCodTipoRecibo(Long codTipoRecibo);
	
	public boolean existsById(Long codTipoRecibo);
	
	public void delete(Long codTipoRecibo);
}
