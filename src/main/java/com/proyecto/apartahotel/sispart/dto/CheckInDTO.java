package com.proyecto.apartahotel.sispart.dto;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.validation.constraints.NotNull;

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

	@NotNull
	private Integer numAdultos;

	@NotNull
	private Integer numNinos;

	private List<Factura> facturas;

	public CheckInDTO() {
		this.facturas = new ArrayList<>();
	}

	public CheckInDTO(@NotNull Date fechaEntrada, @NotNull Date fechaSalida, Huesped codHuesped,
			@NotNull Habitacion codHabitacion, @NotNull Integer numAdultos, @NotNull Integer numNinos) {

		this.fechaEntrada = fechaEntrada;
		this.fechaSalida = fechaSalida;
		this.codHuesped = codHuesped;
		this.codHabitacion = codHabitacion;
		this.numAdultos = numAdultos;
		this.numNinos = numNinos;

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

	public Integer getNumAdultos() {
		return numAdultos;
	}

	public void setNumAdultos(Integer numAdultos) {
		this.numAdultos = numAdultos;
	}

	public Integer getNumNinos() {
		return numNinos;
	}

	public void setNumNinos(Integer numNinos) {
		this.numNinos = numNinos;
	}

	public List<Factura> getFacturas() {
		return facturas;
	}

	public void setFacturas(List<Factura> facturas) {
		this.facturas = facturas;
	}
	
	public Integer getTotalAcompañantes() {

		Integer total = 0;

		total = getNumAdultos() + getNumNinos();

		return total;

	}

	public Double getTotalPersona() {

		long millisecondsPerDay = 24 * 60 * 60 * 1000; // Milisegundos por día
		long diffMilliseconds = getFechaSalida().getTime() - getFechaEntrada().getTime();
		Integer totalDias = (int) (diffMilliseconds / millisecondsPerDay);
		Double total = 0.00;

		if (getTotalAcompañantes() > 1) {

			total = (((getTotalAcompañantes() - 1) * getCodHabitacion().getNombreHabitacion().getPrecioXAcompanante())
					+ getCodHabitacion().getNombreHabitacion().getPrecioXPersona()) * totalDias;

		} else
			total = getCodHabitacion().getNombreHabitacion().getPrecioXPersona() * totalDias;

		return total;
	}
}
