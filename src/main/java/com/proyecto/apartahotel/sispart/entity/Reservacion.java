package com.proyecto.apartahotel.sispart.entity;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "reservacion")
public class Reservacion implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long codReservacion;

	@Column(name = "fecha_ingreso", nullable = false)
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "dd-MM-yyyy")
	@JsonFormat(pattern = "dd-MM-yyyy", timezone = "GMT-5")
	private Date fechaEntrada;

	@Column(name = "fecha_salidad", nullable = false)
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "dd-MM-yyyy")
	@JsonFormat(pattern = "dd-MM-yyyy", timezone = "GMT-5")
	private Date fechaSalida;

	@Column(length = 10, nullable = false)
	private Integer numAcompañantes;

	@JsonIgnoreProperties(value = { "hibernateLazyInitializer", "handler" })
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "cod_habitacion")
	private Habitacion habitacion;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "cod_huesped")
	@JsonIgnoreProperties(value = { "reservacion", "hibernateLazyInitializer", "handler" }, allowSetters = true)
	private Huesped huesped;

	public Reservacion() {

	}

	public Reservacion(Date fechaEntrada, Date fechaSalida, Integer numAcompañantes, Habitacion habitacion,
			Huesped huesped) {
		this.fechaEntrada = fechaEntrada;
		this.fechaSalida = fechaSalida;
		this.numAcompañantes = numAcompañantes;
		this.habitacion = habitacion;
		this.huesped = huesped;
	}

	public Long getCodReservacion() {
		return codReservacion;
	}

	public void setCodReservacion(Long codReservacion) {
		this.codReservacion = codReservacion;
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

	private static final long serialVersionUID = -5479277631957553285L;

}
