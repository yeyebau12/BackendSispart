package com.proyecto.apartahotel.sispart.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.proyecto.apartahotel.sispart.entity.Actividad;
import com.proyecto.apartahotel.sispart.entity.Empleado;
import com.proyecto.apartahotel.sispart.repository.IActividadRepository;
import com.proyecto.apartahotel.sispart.service.interfa.IActividadService;

@Service
public class ActividadServiceImpl implements IActividadService {

	@Autowired
	private IActividadRepository actividadRepository;

	@Override
	public List<Actividad> findAll() {

		return actividadRepository.findAll();
	}

	@Override
	public void save(Actividad actividad) {

		actividadRepository.save(actividad);

	}

	@Override
	public void delete(Long codActividad) {

		actividadRepository.deleteById(codActividad);

	}

	@Override
	public List<Actividad> findByEmpleado(Empleado codEmpleado) {

		return actividadRepository.findByEmpleado(codEmpleado);
	}

	@Override
	public boolean existsByEmpleado(Empleado empleado) {

		return actividadRepository.existsByEmpleado(empleado);
	}

	@Override
	public Actividad findByCodActividad(Long codActividad) {

		return actividadRepository.findById(codActividad).orElse(null);
	}

	@Override
	public boolean existsByCodActividad(Long codActividad) {

		return actividadRepository.existsById(codActividad);
	}

}
