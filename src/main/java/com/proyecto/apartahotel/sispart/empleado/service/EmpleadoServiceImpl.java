package com.proyecto.apartahotel.sispart.empleado.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.proyecto.apartahotel.sispart.empleado.entity.Empleado;
import com.proyecto.apartahotel.sispart.empleado.repository.IEmpleadoRepository;
import com.proyecto.apartahotel.sispart.tipDocumento.entity.TipDocumento;

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
