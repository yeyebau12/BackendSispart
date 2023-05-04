package com.proyecto.apartahotel.sispart.nacionalidad.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.proyecto.apartahotel.sispart.nacionalidad.entity.Nacionalidad;
import com.proyecto.apartahotel.sispart.nacionalidad.repository.INacionalidadRepository;

@Service
public class NacionalidadServiceImpl implements INacionalidadService {

	@Autowired
	private INacionalidadRepository nacionalidadRepository;

	@Override
	@Transactional(readOnly = true)
	public List<Nacionalidad> findAll() {

		return nacionalidadRepository.findAll();
	}

}
