package com.proyecto.apartahotel.sispart.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.proyecto.apartahotel.sispart.entity.Huesped;
import com.proyecto.apartahotel.sispart.entity.Reservacion;
import com.proyecto.apartahotel.sispart.repository.IReservacionRepository;
import com.proyecto.apartahotel.sispart.service.interfa.IReservacionService;

@Service
public class ReservacionServiceImpl implements IReservacionService {

	@Autowired
	private IReservacionRepository reservacionRepository;

	@Override
	public List<Reservacion> findAll() {

		return reservacionRepository.findAll();
	}

	@Override
	public Reservacion findById(Long codResrvacion) {

		return reservacionRepository.findById(codResrvacion).orElse(null);
	}

	@Override
	public void save(Reservacion reservacion) {
		reservacionRepository.save(reservacion);

	}

	@Override
	public void delete(Long codReservacion) {
		reservacionRepository.deleteById(codReservacion);

	}

	@Override
	public boolean existsById(Long codReservacion) {

		return reservacionRepository.existsById(codReservacion);
	}

}
