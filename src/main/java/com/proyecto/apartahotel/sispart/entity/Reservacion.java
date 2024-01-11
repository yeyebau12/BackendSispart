package com.proyecto.apartahotel.sispart.entity;

import java.io.Serializable;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;

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
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT-5")
	private Date fechaEntrada;

	@Column(name = "fecha_salida", nullable = false)
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT-5")
	private Date fechaSalida;

	@Column(length = 10, nullable = false)
	private Integer totalDias;

	private Integer adultos;

	@Column(name = "niños")
	private Integer ninos;

	@ManyToOne
	@JoinColumn(name = "cod_tip_documento", nullable = false)
	private TipDocumento tipoDocumento;

	@Column(name = "num_documento", length = 30, nullable = false)
	private Long numDocumento;

	@Column(length = 30, nullable = false)
	private String nombre;

	@Column(length = 30, nullable = false)
	private String apellido;

	@Column(length = 50, nullable = false)
	private String email;

	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "cod_habitacion")
	private Habitacion habitacion;

	public Reservacion() {

	}

	public Reservacion(Date fechaEntrada, Date fechaSalida, Integer totalDias, Integer adultos, Integer ninos,
			TipDocumento tipoDocumento, Long numDocumento, String nombre, String apellido, String email,
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

	public Habitacion getHabitacion() {
		return habitacion;
	}

	public void setHabitacion(Habitacion habitacion) {
		this.habitacion = habitacion;
	}

	public Integer getNinos() {
		return ninos;
	}

	public void setNinos(Integer ninos) {
		this.ninos = ninos;
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

	public Double getTotalReservacion() {
		Double total = 0.00;

		total = totalDias.doubleValue() * habitacion.getNombreHabitacion().getPrecioDia();

		return total;
	}
	
	public Double getTotalPersona() {
		Double total = 0.00;

		total = getTotalHuespedes() * habitacion.getNombreHabitacion().getPrecioDia();

		return total;
	}


	private static final long serialVersionUID = -5479277631957553285L;

}
