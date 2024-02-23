package com.proyecto.apartahotel.sispart.dto;

import java.util.Date;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Email;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.proyecto.apartahotel.sispart.entity.Habitacion;

import com.proyecto.apartahotel.sispart.entity.TipDocumento;

public class ReservacionDTO {

	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT-5")
	private Date fechaEntrada;

	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT-5")
	private Date fechaSalida;

	private Integer totalDias;

	private Integer adultos;

	private Integer ninos;

	private TipDocumento tipoDocumento;

	private Long numDocumento;

	private String nombre;

	private String apellido;

	@Email
	private String email;

	private Habitacion habitacion;

	public ReservacionDTO() {

	}

	public ReservacionDTO(Date fechaEntrada, Date fechaSalida, Integer totalDias, Integer adultos, Integer ninos,
			TipDocumento tipoDocumento, Long numDocumento, String nombre, String apellido, @Email String email,
			Habitacion habitacion) {

		this.fechaEntrada = fechaEntrada;
		this.fechaSalida = fechaSalida;
		this.totalDias = totalDias;
		this.adultos = adultos;
		this.ninos = ninos;
		this.tipoDocumento = tipoDocumento;
		this.numDocumento = numDocumento;
		this.nombre = nombre;
		this.apellido = apellido;
		this.email = email;
		this.habitacion = habitacion;
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

	public Integer getTotalDias() {
		return totalDias;
	}

	public void setTotalDias(Integer totalDias) {
		this.totalDias = totalDias;
	}

	public Integer getAdultos() {
		return adultos;
	}

	public void setAdultos(Integer adultos) {
		this.adultos = adultos;
	}

	public Integer getNinos() {
		return ninos;
	}

	public void setNinos(Integer ninos) {
		this.ninos = ninos;
	}

	public Habitacion getHabitacion() {
		return habitacion;
	}

	public void setHabitacion(Habitacion habitacion) {
		this.habitacion = habitacion;
	}

	public TipDocumento getTipoDocumento() {
		return tipoDocumento;
	}

	public void setTipoDocumento(TipDocumento tipoDocumento) {
		this.tipoDocumento = tipoDocumento;
	}

	public Long getNumDocumento() {
		return numDocumento;
	}

	public void setNumDocumento(Long numDocumento) {
		this.numDocumento = numDocumento;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Integer getTotalHuespedes() {

		return adultos + ninos;
	}

	// Metodo para dar el precio total de la reserva
	public Double getTotalPersona() {

		Double total = 0.00;

		if (getTotalHuespedes() > 1) {

			total = ((getTotalHuespedes() - 1) * getHabitacion().getNombreHabitacion().getPrecioXAcompanante())
					+ getHabitacion().getNombreHabitacion().getPrecioXPersona();

		} else
			total = getHabitacion().getNombreHabitacion().getPrecioXPersona();

		return total;
	}

}
