package com.proyecto.apartahotel.sispart.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.proyecto.apartahotel.sispart.entity.Habitacion;
import com.proyecto.apartahotel.sispart.entity.Huesped;
import com.proyecto.apartahotel.sispart.entity.Reservacion;
import com.proyecto.apartahotel.sispart.entity.TipDocumento;
import com.proyecto.apartahotel.sispart.repository.IHabitacionesRepository;
import com.proyecto.apartahotel.sispart.repository.IReservacionRepository;
import com.proyecto.apartahotel.sispart.service.interfa.IReservacionService;

@Service
public class ReservacionServiceImpl implements IReservacionService {

	@Autowired
	private IReservacionRepository reservacionRepository;

	@Autowired
	private IHabitacionesRepository habitacionRepository;

	@Override
	public List<Reservacion> findAll() {

		return reservacionRepository.findAll();
	}

	@Override
	public Page<Reservacion> findAll(Pageable pageable) {

		return reservacionRepository.findAll(pageable);
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

	@Override
	public Reservacion findByTipDocumentoAndNumDocumentoAndFechaEntrada(TipDocumento tipDocumento, Long numDocumento,
			Date fechaEntrada) {

		return reservacionRepository.findByTipoDocumentoAndNumDocumentoAndFechaEntrada(tipDocumento, numDocumento,
				fechaEntrada);
	}

	@Override
	public boolean existsByTipDocumentoAndNumDocumentoAndFechaEntrada(TipDocumento tipDocumento, Long numDocumento,
			Date fechaEntrada) {

		return reservacionRepository.existsByTipoDocumentoAndNumDocumentoAndFechaEntrada(tipDocumento, numDocumento,
				fechaEntrada);
	}



}
