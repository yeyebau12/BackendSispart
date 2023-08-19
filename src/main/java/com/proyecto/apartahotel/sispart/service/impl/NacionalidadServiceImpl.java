package com.proyecto.apartahotel.sispart.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.proyecto.apartahotel.sispart.entity.Nacionalidad;
import com.proyecto.apartahotel.sispart.repository.INacionalidadRepository;
import com.proyecto.apartahotel.sispart.service.interfa.INacionalidadService;

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
