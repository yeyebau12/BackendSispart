package com.proyecto.apartahotel.sispart.service.interfa;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.proyecto.apartahotel.sispart.entity.Empleado;
import com.proyecto.apartahotel.sispart.entity.TipDocumento;

public interface IEmpleadoService {

	public List<Empleado> findAll();
	
	public Page<Empleado> findAll(Pageable pageable);

	public Empleado findByTipDocumentoAndNumDocumento(TipDocumento tipDocumento, Long numDocumento);

	public Empleado findByCodEmpleado(Long codEmpleado);

	public void save(Empleado empleado);

	public void delete(Long codEmpleado);

	public boolean exitsTipDocumentoAndNumDocumento(TipDocumento tipDocumento, Long numDocumento);

	public boolean existsById(Long codEmpleado);
}
