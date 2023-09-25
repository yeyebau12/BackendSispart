package com.proyecto.apartahotel.sispart.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.proyecto.apartahotel.sispart.entity.Huesped;
import com.proyecto.apartahotel.sispart.entity.RecibosPublicos;
import com.proyecto.apartahotel.sispart.repository.IReciboPublicoRepository;
import com.proyecto.apartahotel.sispart.service.interfa.IRecibosPublicosService;

@Service
public class ReciboPublicoServiceImpl implements IRecibosPublicosService {

	@Autowired
	private IReciboPublicoRepository reciboPublicoRepository;

	@Override
	@Transactional(readOnly = true)
	public List<RecibosPublicos> findAll() {

		return reciboPublicoRepository.findAll();
	}
	
	@Override
	public Page<RecibosPublicos> findAll(Pageable pageable) {
		
		return reciboPublicoRepository.findAll(pageable);
	}

	@Override
	@Transactional(readOnly = true)
	public RecibosPublicos findById(Long codRecibo) {

		return reciboPublicoRepository.findById(codRecibo).orElse(null);
	}

	@Override
	@Transactional
	public void save(RecibosPublicos reciboPublico) {

		reciboPublicoRepository.save(reciboPublico);

	}

	@Override
	@Transactional
	public void delete(Long codRecibo) {

		reciboPublicoRepository.deleteById(codRecibo);

	}

	@Override
	@Transactional
	public boolean existsById(Long codRecibo) {

		return reciboPublicoRepository.existsById(codRecibo);
	}



}
