package com.proyecto.apartahotel.sispart.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.proyecto.apartahotel.sispart.entity.TipoHabitacion;
import com.proyecto.apartahotel.sispart.repository.ITipoHabitacionRepository;
import com.proyecto.apartahotel.sispart.service.interfa.ITipoHabitacionService;

@Service
public class TipoHabitacionServiceImpl implements ITipoHabitacionService {

	@Autowired
	private ITipoHabitacionRepository tipoHabitacionRepository;

	@Override
	@Transactional(readOnly = true)
	public List<TipoHabitacion> findAll() {

		return tipoHabitacionRepository.findAll();
	}

	@Override
	public Page<TipoHabitacion> findAll(Pageable pageable) {

		return tipoHabitacionRepository.findAll(pageable);
	}

	@Override
	public void save(TipoHabitacion tipoHabitacion) {

		tipoHabitacionRepository.save(tipoHabitacion);

	}

	@Override
	public TipoHabitacion findByCodTipoHabitacion(Long codTipoHabitacion) {

		return tipoHabitacionRepository.findById(codTipoHabitacion).orElse(null);
	}

	@Override
	public boolean existsById(Long codTipoHabiatcion) {

		return tipoHabitacionRepository.existsById(codTipoHabiatcion);
	}

}
