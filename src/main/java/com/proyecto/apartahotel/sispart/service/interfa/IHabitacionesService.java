package com.proyecto.apartahotel.sispart.service.interfa;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.proyecto.apartahotel.sispart.entity.Habitacion;


public interface IHabitacionesService {

	public List<Habitacion> findAll();

	public Page<Habitacion> findAll(Pageable pageable);
	
	public Habitacion findByNumHabitacion(Integer numHabitacion);

	public Habitacion findByCodHabitacion(Long codHabitacion);

	public void save(Habitacion habitacion);

	public void delete(Long codHabitacion);

	public boolean exitsNumHabitacion(Integer numHabitacion);

	public boolean existsById(Long codHabitacion);

}
