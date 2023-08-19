package com.proyecto.apartahotel.sispart.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.proyecto.apartahotel.sispart.entity.Habitacion;

@Repository
public interface IHabitacionesRepository extends JpaRepository<Habitacion, Long> {

	public boolean existsByNumHabitacion(Integer numHabitacion);

	public Optional<Habitacion> findByNumHabitacion(Integer numHabitacion);
}
