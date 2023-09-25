package com.proyecto.apartahotel.sispart.service.interfa;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.proyecto.apartahotel.sispart.entity.Huesped;
import com.proyecto.apartahotel.sispart.entity.RecibosPublicos;

public interface IRecibosPublicosService {

	public List<RecibosPublicos> findAll();
	
	public Page<RecibosPublicos> findAll(Pageable pageable);

	public RecibosPublicos findById(Long codRecibo);

	public void save(RecibosPublicos reciboPublico);

	public void delete(Long codRecibo);

	public boolean existsById(Long codRecibo);
}
