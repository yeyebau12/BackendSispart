package com.proyecto.apartahotel.sispart.habitaciones.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.proyecto.apartahotel.sispart.facturacion.entity.Factura;
import com.proyecto.apartahotel.sispart.facturacion.repository.FacturaRepository;
import com.proyecto.apartahotel.sispart.habitaciones.entity.Habitacion;
import com.proyecto.apartahotel.sispart.habitaciones.repository.IHabitacionesRepository;

@Service
public class HabitacionesServiceImpl implements IHabitacionesService {

	@Autowired
	private IHabitacionesRepository habitacionRepository;

	@Override
	public List<Habitacion> findAll() {

		return habitacionRepository.findAll();
	}

	@Override
	public Habitacion findByNumHabitacion(Integer numHabitacion) {

		return habitacionRepository.findByNumHabitacion(numHabitacion).orElse(null);
	}

	@Override
	public Habitacion findByCodHabitacion(Long codHabitacion) {
		return habitacionRepository.findById(codHabitacion).orElse(null);
	}

	@Override
	public void save(Habitacion habitacion) {

		habitacionRepository.save(habitacion);
	}

	@Override
	public void delete(Long codHabitacion) {

		habitacionRepository.deleteById(codHabitacion);
	}

	@Override
	public boolean exitsNumHabitacion(Integer numHabitacion) {

		return habitacionRepository.existsByNumHabitacion(numHabitacion);
	}

	@Override
	public boolean existsById(Long codHabitacion) {

		return habitacionRepository.existsById(codHabitacion);
	}

}
