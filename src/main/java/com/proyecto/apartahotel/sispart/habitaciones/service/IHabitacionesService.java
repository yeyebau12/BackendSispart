package com.proyecto.apartahotel.sispart.habitaciones.service;

import java.util.List;

import com.proyecto.apartahotel.sispart.facturacion.entity.Factura;
import com.proyecto.apartahotel.sispart.habitaciones.entity.Habitacion;

public interface IHabitacionesService {

	public List<Habitacion> findAll();

	public Habitacion findByNumHabitacion(Integer numHabitacion);

	public Habitacion findByCodHabitacion(Long codHabitacion);

	public void save(Habitacion habitacion);

	public void delete(Long codHabitacion);

	public boolean exitsNumHabitacion(Integer numHabitacion);

	public boolean existsById(Long codHabitacion);

}
