package com.proyecto.apartahotel.sispart.service.interfa;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


import com.proyecto.apartahotel.sispart.entity.TipoHabitacion;

public interface ITipoHabitacionService {

	public List<TipoHabitacion> findAll();
	
	public Page<TipoHabitacion> findAll(Pageable pageable);

	public void save(TipoHabitacion tipoHabitacion);

	public TipoHabitacion findByCodTipoHabitacion(Long codTipoHabitacion);
	
	public boolean existsById(Long codTipoHabiatcion);

}
