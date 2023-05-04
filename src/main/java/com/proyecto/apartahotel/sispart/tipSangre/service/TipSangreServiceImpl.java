package com.proyecto.apartahotel.sispart.tipSangre.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.proyecto.apartahotel.sispart.tipSangre.entity.TipoSangre;
import com.proyecto.apartahotel.sispart.tipSangre.repository.ITipSangreRepository;

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
