package com.proyecto.apartahotel.sispart.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.proyecto.apartahotel.sispart.entity.Huesped;
import com.proyecto.apartahotel.sispart.entity.TipDocumento;
import com.proyecto.apartahotel.sispart.repository.IHuespedRepository;
import com.proyecto.apartahotel.sispart.service.interfa.IHuespedService;

@Service
public class HuespedServiceImpl implements IHuespedService {

	@Autowired
	private IHuespedRepository huespedRepository;

	@Override
	@Transactional(readOnly = true)
	public List<Huesped> findAll() {

		return huespedRepository.findAll();
	}
	
	@Override
	@Transactional(readOnly = true)
	public Page<Huesped> findAll(Pageable pageable) {
		
		return huespedRepository.findAll(pageable);
	}

	@Override
	@Transactional(readOnly = true)
	public Huesped getOne(Long codHuesped) {

		return huespedRepository.findById(codHuesped).orElse(null);
	}

	@Override
	@Transactional(readOnly = true)
	public Huesped getByTipoDocumentoAndNumDocumento(TipDocumento tipoDocumento, Long numDocumento) {

		return huespedRepository.findByTipoDocumentoAndNumDocumento(tipoDocumento, numDocumento).orElse(null);
	}

	@Override
	public void save(Huesped huesped) {

		huespedRepository.save(huesped);

	}

	@Override
	public void delete(Long codHuesped) {

		huespedRepository.deleteById(codHuesped);

	}

	@Override
	public void deleteByTipoDocumentoNumDocumento(TipDocumento tipoDocumento, Long numDocumento) {

		huespedRepository.deleteByTipoDocumentoAndNumDocumento(tipoDocumento, numDocumento);
	}

	@Override
	public boolean existsById(Long codHuesped) {

		return huespedRepository.existsById(codHuesped);
	}

	@Override
	public boolean existsByTipoDocumentoAndNumDocumento(TipDocumento tipDocumento, Long numDocumento) {

		return huespedRepository.existsByTipoDocumentoAndNumDocumento(tipDocumento, numDocumento);
	}





}
