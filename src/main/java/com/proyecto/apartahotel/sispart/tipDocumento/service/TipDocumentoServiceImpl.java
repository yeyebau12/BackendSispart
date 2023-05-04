package com.proyecto.apartahotel.sispart.tipDocumento.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.proyecto.apartahotel.sispart.tipDocumento.entity.TipDocumento;
import com.proyecto.apartahotel.sispart.tipDocumento.repository.ITipDocumentoRepository;

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
