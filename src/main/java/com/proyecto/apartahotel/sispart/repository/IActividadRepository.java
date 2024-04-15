package com.proyecto.apartahotel.sispart.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.proyecto.apartahotel.sispart.entity.Actividad;
import com.proyecto.apartahotel.sispart.entity.Empleado;
import com.proyecto.apartahotel.sispart.entity.EstadoHabitacion;
import com.proyecto.apartahotel.sispart.entity.Habitacion;

@Repository
public interface IActividadRepository extends JpaRepository<Actividad, Long> {

	
	public List<Actividad> findByEmpleado(Empleado codEmpleado);

	public boolean existsByEmpleado(Empleado empleado);
}
