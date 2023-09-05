package com.proyecto.apartahotel.sispart.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
public class Checkin implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "cod_checkin")
	private Long codCheckin;

	@Column(name = "fecha_ingreso", nullable = false)
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT-5")
	private Date fechaIngreso;

	@Column(name = "fecha_salida", nullable = false)
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT-5")
	private Date fechaSalida;

	@ManyToOne
	@JoinColumn(name = "cod_huesped")
	private Huesped huesped;

	@OneToOne
	@JoinColumn(name = "cod_habitacion")
	private Habitacion habitacion;

	public Checkin() {

	}

	public Checkin(Long codCheckin, Date fechaIngreso, Date fechaSalida, Huesped huesped, Habitacion habitacion) {

		this.codCheckin = codCheckin;
		this.fechaIngreso = fechaIngreso;
		this.fechaSalida = fechaSalida;
		this.huesped = huesped;
		this.habitacion = habitacion;
	}

	public Checkin(Date fechaIngreso, Date fechaSalida, Huesped huesped, Habitacion habitacion) {

		this.fechaIngreso = fechaIngreso;
		this.fechaSalida = fechaSalida;
		this.huesped = huesped;
		this.habitacion = habitacion;
	}

	public Long getCodCheckin() {
		return codCheckin;
	}

	public void setCodCheckin(Long codCheckin) {
		this.codCheckin = codCheckin;
	}

	public Date getFechaIngreso() {
		return fechaIngreso;
	}

	public void setFechaIngreso(Date fechaIngreso) {
		this.fechaIngreso = fechaIngreso;
	}

	public Date getFechaSalida() {
		return fechaSalida;
	}

	public void setFechaSalida(Date fechaSalida) {
		this.fechaSalida = fechaSalida;
	}

	public Huesped getHuesped() {
		return huesped;
	}

	public void setHuesped(Huesped huesped) {
		this.huesped = huesped;
	}

	public Habitacion getHabitacion() {
		return habitacion;
	}

	public void setHabitacion(Habitacion habitacion) {
		this.habitacion = habitacion;
	}

	private static final long serialVersionUID = 1L;

}
