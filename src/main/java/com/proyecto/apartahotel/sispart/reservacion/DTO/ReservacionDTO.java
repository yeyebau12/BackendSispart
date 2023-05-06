package com.proyecto.apartahotel.sispart.reservacion.DTO;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.Date;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

import com.proyecto.apartahotel.sispart.habitaciones.entity.Habitacion;
import com.proyecto.apartahotel.sispart.huesped.entity.Huesped;

public class ReservacionDTO {

	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "dd-MM-yyyy")
	@JsonFormat(pattern = "dd-MM-yyyy", timezone = "GMT-5")
	private Date fechaEntrada;

	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "dd-MM-yyyy")
	@JsonFormat(pattern = "dd-MM-yyyy", timezone = "GMT-5")
	private Date fechaSalida;

	private Integer numAcompañantes;

	private Habitacion habitacion;

	private Huesped huesped;

	public ReservacionDTO() {

	}

	public ReservacionDTO(Date fechaEntrada, Date fechaSalida, Integer numAcompañantes, Habitacion habitacion,
			Huesped huesped) {

		this.fechaEntrada = fechaEntrada;
		this.fechaSalida = fechaSalida;
		this.numAcompañantes = numAcompañantes;
		this.habitacion = habitacion;
		this.huesped = huesped;
	}

	public Date getFechaEntrada() {
		return fechaEntrada;
	}

	public void setFechaEntrada(Date fechaEntrada) {
		this.fechaEntrada = fechaEntrada;
	}

	public Date getFechaSalida() {
		return fechaSalida;
	}

	public void setFechaSalida(Date fechaSalida) {
		this.fechaSalida = fechaSalida;
	}

	public Integer getNumAcompañantes() {
		return numAcompañantes;
	}

	public void setNumAcompañantes(Integer numAcompañantes) {
		this.numAcompañantes = numAcompañantes;
	}

	public Habitacion getHabitacion() {
		return habitacion;
	}

	public void setHabitacion(Habitacion habitacion) {
		this.habitacion = habitacion;
	}

	public Huesped getHuesped() {
		return huesped;
	}

	public void setHuesped(Huesped huesped) {
		this.huesped = huesped;
	}

}
