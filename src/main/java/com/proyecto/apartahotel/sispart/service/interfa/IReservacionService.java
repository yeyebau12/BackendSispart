package com.proyecto.apartahotel.sispart.service.interfa;

import java.util.Date;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.proyecto.apartahotel.sispart.entity.Huesped;
import com.proyecto.apartahotel.sispart.entity.Producto;
import com.proyecto.apartahotel.sispart.entity.Reservacion;
import com.proyecto.apartahotel.sispart.entity.TipDocumento;

public interface IReservacionService {

	public List<Reservacion> findAll();

	public Page<Reservacion> findAll(Pageable pageable);
	
	public Reservacion findByTipDocumentoAndNumDocumentoAndFechaEntrada(TipDocumento tipDocumento, Long numDocumento,
			Date fechaEntrada);
	
	public Reservacion findById(Long codResrvacion);

	public void save(Reservacion reservacion);

	public void delete(Long codReservacion);

	public boolean existsByTipDocumentoAndNumDocumentoAndFechaEntrada(TipDocumento tipDocumento, Long numDocumento,Date fechaEntrada);

	public boolean existsById(Long codReservacion);

}
