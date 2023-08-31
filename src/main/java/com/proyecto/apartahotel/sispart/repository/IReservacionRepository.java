package com.proyecto.apartahotel.sispart.repository;

import java.util.Date;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.proyecto.apartahotel.sispart.entity.Huesped;
import com.proyecto.apartahotel.sispart.entity.Reservacion;
import com.proyecto.apartahotel.sispart.entity.TipDocumento;

@Repository
public interface IReservacionRepository extends JpaRepository<Reservacion, Long> {

	public Reservacion findByTipoDocumentoAndNumDocumentoAndFechaEntrada(TipDocumento tipDocumento,Long numDocumento, Date fechaEntrada);

	public boolean existsByTipoDocumentoAndNumDocumentoAndFechaEntrada(TipDocumento tipDocumento, Long numDocumento,Date fechaEntrada);
	
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
