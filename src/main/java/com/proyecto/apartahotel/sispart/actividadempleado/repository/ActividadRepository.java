package com.proyecto.apartahotel.sispart.actividadempleado.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.proyecto.apartahotel.sispart.actividadempleado.entity.Actividad;
import com.proyecto.apartahotel.sispart.empleado.entity.Empleado;

@Repository
public interface ActividadRepository extends JpaRepository<Actividad, Long> {

	public Optional<Actividad> findByEmpleado(Empleado codEmpleado);

	public boolean existsByEmpleado(Empleado empleado);
}
