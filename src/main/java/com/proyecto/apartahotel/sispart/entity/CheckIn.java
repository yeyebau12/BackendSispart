package com.proyecto.apartahotel.sispart.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "check_in")
public class CheckIn implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "cod_checkin")
	private Long codCheckin;

	@Column(name = "fecha_ingreso", nullable = false)
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT-5")
	private Date fechaEntrada;

	@Column(name = "fecha_salida", nullable = false)
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT-5")
	private Date fechaSalida;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "cod_huesped", nullable = false)
	@JsonIgnoreProperties(value = { "hibernateLazyInitializer", "handler" }, allowSetters = true)
	private Huesped codHuesped;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "cod_habitacion", nullable = false)
	@JsonIgnoreProperties(value = { "hibernateLazyInitializer", "handler" }, allowSetters = true)
	private Habitacion codHabitacion;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "checkin", cascade = CascadeType.ALL)
	@JsonIgnoreProperties(value = { "checkin", "hibernateLazyInitializer", "handler" }, allowSetters = true)
	private List<Factura> facturas;

	public CheckIn() {

		this.facturas = new ArrayList<>();

	}

	public CheckIn(Long codCheckin, Date fechaEntrada, Date fechaSalida, Huesped codHuesped, Habitacion codHabitacion) {
		super();
		this.codCheckin = codCheckin;
		this.fechaEntrada = fechaEntrada;
		this.fechaSalida = fechaSalida;
		this.codHuesped = codHuesped;
		this.codHabitacion = codHabitacion;
	}

	public CheckIn(Date fechaEntrada, Date fechaSalida, Huesped codHuesped, Habitacion codHabitacion) {
		super();
		this.fechaEntrada = fechaEntrada;
		this.fechaSalida = fechaSalida;
		this.codHuesped = codHuesped;
		this.codHabitacion = codHabitacion;
	}

	public Long getCodCheckin() {
		return codCheckin;
	}

	public void setCodCheckin(Long codCheckin) {
		this.codCheckin = codCheckin;
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

	public List<Factura> getFacturas() {
		return facturas;
	}

	public void setFacturas(List<Factura> facturas) {
		this.facturas = facturas;
	}

	private static final long serialVersionUID = -763212033771895092L;

}
