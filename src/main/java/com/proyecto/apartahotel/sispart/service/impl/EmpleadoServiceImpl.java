package com.proyecto.apartahotel.sispart.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.proyecto.apartahotel.sispart.entity.Empleado;
import com.proyecto.apartahotel.sispart.entity.TipDocumento;
import com.proyecto.apartahotel.sispart.repository.IEmpleadoRepository;
import com.proyecto.apartahotel.sispart.service.interfa.IEmpleadoService;

@Service
public class EmpleadoServiceImpl implements IEmpleadoService {

	@Autowired
	private IEmpleadoRepository empleadoRepository;

	@Override
	@Transactional(readOnly = true)
	public List<Empleado> findAll() {

		return empleadoRepository.findAll();
	}
	

	@Override
	@Transactional(readOnly = true)
	public Page<Empleado> findAll(Pageable pageable) {
		
		return empleadoRepository.findAll(pageable);
	}

	@Override
	@Transactional(readOnly = true)
	public Empleado findByTipDocumentoAndNumDocumento(TipDocumento tipDocumento, Long numDocumento) {

		return empleadoRepository.findByTipDocumentoAndNumDocumento(tipDocumento, numDocumento).orElse(null);
	}

	@Override
	public Empleado findByCodEmpleado(Long codEmpleado) {

		return empleadoRepository.findById(codEmpleado).orElse(null);
	}

	@Override
	@Transactional
	public void save(Empleado empleado) {

		empleadoRepository.save(empleado);

	}

	@Override
	@Transactional
	public void delete(Long codEmpleado) {

		empleadoRepository.deleteById(codEmpleado);

	}

	@Override
	public boolean exitsTipDocumentoAndNumDocumento(TipDocumento tipDocumento, Long numDocumento) {

		return empleadoRepository.existsByTipDocumentoAndNumDocumento(tipDocumento, numDocumento);
	}

	@Override
	public boolean existsById(Long codEmpleado) {

		return empleadoRepository.existsById(codEmpleado);

	}



}
