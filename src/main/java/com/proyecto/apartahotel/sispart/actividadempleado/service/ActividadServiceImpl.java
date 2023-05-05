package com.proyecto.apartahotel.sispart.actividadempleado.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.proyecto.apartahotel.sispart.actividadempleado.entity.Actividad;
import com.proyecto.apartahotel.sispart.actividadempleado.repository.ActividadRepository;
import com.proyecto.apartahotel.sispart.empleado.entity.Empleado;

@Service
public class ActividadServiceImpl implements IActividadService {

	@Autowired
	private ActividadRepository actividadRepository;

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
	public Actividad findByEmpleado(Empleado empleado) {

		return actividadRepository.findByEmpleado(empleado).orElse(null);
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
