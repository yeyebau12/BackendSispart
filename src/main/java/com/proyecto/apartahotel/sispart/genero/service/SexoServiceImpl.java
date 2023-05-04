package com.proyecto.apartahotel.sispart.genero.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.proyecto.apartahotel.sispart.genero.entity.Sexo;
import com.proyecto.apartahotel.sispart.genero.repository.ISexoRepository;

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
