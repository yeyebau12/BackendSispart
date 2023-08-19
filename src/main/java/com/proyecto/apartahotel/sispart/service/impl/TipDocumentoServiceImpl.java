package com.proyecto.apartahotel.sispart.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.proyecto.apartahotel.sispart.entity.TipDocumento;
import com.proyecto.apartahotel.sispart.repository.ITipDocumentoRepository;
import com.proyecto.apartahotel.sispart.service.interfa.ITipDocumentoService;

@Service
public class TipDocumentoServiceImpl implements ITipDocumentoService {

	@Autowired
	private ITipDocumentoRepository tipDocumentoRepository;

	@Override
	@Transactional(readOnly = true)
	public List<TipDocumento> findAll() {
		
		return tipDocumentoRepository.findAll();
	}

}
