package com.proyecto.apartahotel.sispart.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.proyecto.apartahotel.sispart.entity.TipoRecibo;
import com.proyecto.apartahotel.sispart.repository.ITipoReciboRepository;
import com.proyecto.apartahotel.sispart.service.interfa.ITipoReciboService;

@Service
public class TipoReciboServiceImpl implements ITipoReciboService {

	@Autowired
	private ITipoReciboRepository tipoReciboRepository;

	@Override
	@Transactional(readOnly = true)
	public List<TipoRecibo> findAll() {

		return tipoReciboRepository.findAll();
	}

}
