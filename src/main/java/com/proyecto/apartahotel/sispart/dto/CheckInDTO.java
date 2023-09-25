package com.proyecto.apartahotel.sispart.dto;

import java.util.Date;

import javax.validation.constraints.NotNull;

import com.proyecto.apartahotel.sispart.entity.Habitacion;
import com.proyecto.apartahotel.sispart.entity.Huesped;

public class CheckinDTO {

	@NotNull
	private Date fechaEntrada;
	@NotNull
	private Date fechaSalida;
	private Huesped codHuesped;
	@NotNull
	private Habitacion codHabitacion;

	public CheckinDTO() {

	}

	public CheckinDTO(@NotNull Date fechaEntrada, @NotNull Date fechaSalida, @NotNull Huesped codHuesped,
			@NotNull Habitacion codHabitacion) {

		this.fechaEntrada = fechaEntrada;
		this.fechaSalida = fechaSalida;
		this.codHuesped = codHuesped;
		this.codHabitacion = codHabitacion;
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
	
	

}
