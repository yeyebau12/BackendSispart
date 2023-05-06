package com.proyecto.apartahotel.sispart.reservacion.service;

import java.util.Date;
import java.util.List;

import com.proyecto.apartahotel.sispart.huesped.entity.Huesped;
import com.proyecto.apartahotel.sispart.reservacion.entity.Reservacion;

public interface IReservacionService {

	public List<Reservacion> findAll();

	//public Reservacion findByHuespedAndFechaEntrada(Huesped huesped, Date fechaEntrada);

	public Reservacion findById(Long codResrvacion);

	public void save(Reservacion reservacion);

	public void delete(Long codReservacion);

	//public boolean existsByHuespedAndFechaEntrada(Huesped huesped, Date fechaEntrada);

	public boolean existsById(Long codReservacion);

}
