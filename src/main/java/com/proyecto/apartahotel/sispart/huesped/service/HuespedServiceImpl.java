package com.proyecto.apartahotel.sispart.huesped.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.proyecto.apartahotel.sispart.huesped.entity.Huesped;
import com.proyecto.apartahotel.sispart.huesped.repository.IHuespedRepository;
import com.proyecto.apartahotel.sispart.tipDocumento.entity.TipDocumento;

@Service
public class HuespedServiceImpl implements IHuespedServiceImpl {

	@Autowired
	private IHuespedRepository huespedRepository;

	@Override
	@Transactional(readOnly = true)
	public List<Huesped> findAll() {

		return huespedRepository.findAll();
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
