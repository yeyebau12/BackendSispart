package com.proyecto.apartahotel.sispart.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.proyecto.apartahotel.sispart.entity.TipoSangre;
import com.proyecto.apartahotel.sispart.repository.ITipSangreRepository;
import com.proyecto.apartahotel.sispart.service.interfa.ITipSangreService;

@Service
public class TipSangreServiceImpl implements ITipSangreService {

	@Autowired
	private ITipSangreRepository tipSangreRepository;

	@Override
	@Transactional(readOnly = true)
	public List<TipoSangre> findAll() {

		return tipSangreRepository.findAll();
	}

}
