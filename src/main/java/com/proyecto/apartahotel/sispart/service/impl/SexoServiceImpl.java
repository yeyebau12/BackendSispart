package com.proyecto.apartahotel.sispart.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.proyecto.apartahotel.sispart.entity.Sexo;
import com.proyecto.apartahotel.sispart.repository.ISexoRepository;
import com.proyecto.apartahotel.sispart.service.interfa.ISexoService;

@Service
public class SexoServiceImpl implements ISexoService {

	@Autowired
	private ISexoRepository sexoRepository;

	@Override
	@Transactional(readOnly = true)
	public List<Sexo> findAll() {
		
		return sexoRepository.findAll();
	}

}
