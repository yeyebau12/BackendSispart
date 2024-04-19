package com.proyecto.apartahotel.sispart.dto;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.validation.constraints.NotNull;

import com.proyecto.apartahotel.sispart.entity.Acompanantes;
import com.proyecto.apartahotel.sispart.entity.Factura;
import com.proyecto.apartahotel.sispart.entity.Habitacion;
import com.proyecto.apartahotel.sispart.entity.Huesped;

public class CheckInDTO {

	@NotNull
	private Date fechaEntrada;
	@NotNull
	private Date fechaSalida;

	private Huesped codHuesped;

	@NotNull
	private Habitacion codHabitacion;


	private Integer numAcompanante;

	private List<Factura> facturas;

	private List<Acompanantes> acompanante;

	public CheckInDTO() {
		this.facturas = new ArrayList<>();
		this.acompanante = new ArrayList<>();
	}

	public CheckInDTO(@NotNull Date fechaEntrada, @NotNull Date fechaSalida, Huesped codHuesped,
			@NotNull Habitacion codHabitacion, Integer numAcompanante, List<Acompanantes> acompanante) {

		this.fechaEntrada = fechaEntrada;
		this.fechaSalida = fechaSalida;
		this.codHuesped = codHuesped;
		this.codHabitacion = codHabitacion;
		this.numAcompanante = numAcompanante;
		this.acompanante = acompanante;
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

	public Huesped getCodHuesped() {
		return codHuesped;
	}

	public void setCodHuesped(Huesped codHuesped) {
		this.codHuesped = codHuesped;
	}

	public Habitacion getCodHabitacion() {
		return codHabitacion;
	}

	public void setCodHabitacion(Habitacion codHabitacion) {
		this.codHabitacion = codHabitacion;
	}

	public Integer getNumAcompanante() {
		return numAcompanante;
	}

	public void setNumAcompanante(Integer numAcompanante) {
		this.numAcompanante = numAcompanante;
	}

	public List<Factura> getFacturas() {
		return facturas;
	}

	public void setFacturas(List<Factura> facturas) {
		this.facturas = facturas;
	}

	public List<Acompanantes> getAcompanante() {
		return acompanante;
	}

	public void setAcompanante(List<Acompanantes> acompanante) {
		this.acompanante = acompanante;
	}

	public Double getTotalPersona() {

		long millisecondsPerDay = 24 * 60 * 60 * 1000; // Milisegundos por día
		long diffMilliseconds = getFechaSalida().getTime() - getFechaEntrada().getTime();
		Integer totalDias = (int) (diffMilliseconds / millisecondsPerDay);
		Double total = 0.00;

		if (getNumAcompanante() > 1) {

			total = (((getNumAcompanante() - 1) * getCodHabitacion().getNombreHabitacion().getPrecioXAcompanante())
					+ getCodHabitacion().getNombreHabitacion().getPrecioXPersona()) * totalDias;

		} else
			total = getCodHabitacion().getNombreHabitacion().getPrecioXPersona() * totalDias;

		return total;
	}
}
