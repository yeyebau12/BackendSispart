package com.proyecto.apartahotel.sispart.empleado.service;

import java.util.List;

import com.proyecto.apartahotel.sispart.empleado.entity.Empleado;
import com.proyecto.apartahotel.sispart.tipDocumento.entity.TipDocumento;

public interface IEmpleadoService {

	public List<Empleado> findAll();

	public Empleado findByTipDocumentoAndNumDocumento(TipDocumento tipDocumento, Long numDocumento);

	public Empleado findByCodEmpleado(Long codEmpleado);

	public void save(Empleado empleado);

	public void delete(Long codEmpleado);

	public boolean exitsTipDocumentoAndNumDocumento(TipDocumento tipDocumento, Long numDocumento);

	public boolean existsById(Long codEmpleado);
}
