package com.proyecto.apartahotel.sispart.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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

	@Override
	public Page<TipoRecibo> findAll(Pageable pageable) {
		
		return tipoReciboRepository.findAll(pageable);
	}

	@Override
	public void save(TipoRecibo tipoRecibo) {
	
		tipoReciboRepository.save(tipoRecibo);
	}

	@Override
	public TipoRecibo findByCodTipoRecibo(Long codTipoRecibo) {
		
		return tipoReciboRepository.findById(codTipoRecibo).orElse(null);
	}

	@Override
	public boolean existsById(Long codTipoRecibo) {
		
		return tipoReciboRepository.existsById(codTipoRecibo);
	}

	@Override
	public void delete(Long codTipoRecibo) {
		tipoReciboRepository.deleteById(codTipoRecibo);
		
	}

}
