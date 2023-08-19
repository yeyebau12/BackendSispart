package com.proyecto.apartahotel.sispart.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.proyecto.apartahotel.sispart.entity.Actividad;
import com.proyecto.apartahotel.sispart.entity.Empleado;

@Repository
public interface IActividadRepository extends JpaRepository<Actividad, Long> {

	public Optional<Actividad> findByEmpleado(Empleado codEmpleado);

	public boolean existsByEmpleado(Empleado empleado);
}
