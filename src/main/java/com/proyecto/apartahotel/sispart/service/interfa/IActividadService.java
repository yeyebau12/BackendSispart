package com.proyecto.apartahotel.sispart.service.interfa;

import java.util.List;

import com.proyecto.apartahotel.sispart.entity.Actividad;
import com.proyecto.apartahotel.sispart.entity.Empleado;

public interface IActividadService {

	public List<Actividad> findAll();

	public void save(Actividad actividad);

	public void delete(Long codActividad);

	public Actividad findByEmpleado(Empleado empleado);

	public Actividad findByCodActividad(Long codActividad);

	public boolean existsByEmpleado(Empleado empleado);

	public boolean existsByCodActividad(Long codActividad);

}
