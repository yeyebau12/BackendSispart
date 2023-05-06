package com.proyecto.apartahotel.sispart.reservacion.repository;

import java.util.Date;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.proyecto.apartahotel.sispart.huesped.entity.Huesped;
import com.proyecto.apartahotel.sispart.reservacion.entity.Reservacion;
import com.proyecto.apartahotel.sispart.tipDocumento.entity.TipDocumento;

@Repository
public interface IReservacionRepository extends JpaRepository<Reservacion, Long> {

	//public Optional<Reservacion> findByHuespedAndFechaEntrada(Huesped huesped, Date fechaEntrada);

	//public boolean existsByHuespedAndFechaEntrada(Huesped huesped, Date fechaEntrada);
	
	/*
	 * public Optional<Reservacion>
	 * findByTipDocumentoAndNumDocumentoAndFechaEntrada(TipDocumento tipDocumento,
	 * Long numDocumento, Date fechaEntrada);
	 * 
	 * public boolean
	 * existsByTipDocumentoAndNumDocumentoAndFechaEntrada(TipDocumento tipDocumento,
	 * Long numDocumento, Date fechaEntrada);
	 * 
	 * public void deleteByTipDocumentoAndNumDocumentoAndFechaEntrada(TipDocumento
	 * tipDocumento, Long numDocumento, Date fechaEntrada);
	 **/
}
