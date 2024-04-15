package com.proyecto.apartahotel.sispart.entity;

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
@Table(name = "actividad_empleado")
public class Actividad {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "cod_actividad")
	private Long codActividad;

	@Column(name = "titulo", length = 30, nullable = false)
	private String titulo;

	@Column(name = "descripcion", length = 300, nullable = false)
	private String descripcion;

	@Column(name = "fecha_entrega", nullable = false)
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT-5")
	private Date fechaEntrega;

	@Column(name = "hora_enviado", nullable = false)
	@Temporal(TemporalType.TIME)
	@DateTimeFormat(pattern = "HH:mm:ss")
	@JsonFormat(pattern = "HH:mm:ss", timezone = "GMT-5")
	private Date horaEntrega;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "cod_empleado")
	@JsonIgnoreProperties(value = { "actividad", "hibernateLazyInitializer", "handler" }, allowSetters = true)
	private Empleado empleado;

	@Column(name = "estado_actividad")
	private String estadoActividad;

	public Actividad() {
		

	}

	public Actividad(Long codActividad, String titulo, String descripcion, Date fechaEntrega, Date horaEntrega,
			Empleado empleado, String estadoActividad) {

		this.codActividad = codActividad;
		this.titulo = titulo;
		this.descripcion = descripcion;
		this.fechaEntrega = fechaEntrega;
		this.horaEntrega = horaEntrega;
		this.empleado = empleado;
		this.estadoActividad = estadoActividad;
	}

	public Actividad(String titulo, String descripcion, Date fechaEntrega, Date horaEntrega, Empleado empleado,
			String estadoActividad) {

		this.titulo = titulo;
		this.descripcion = descripcion;
		this.fechaEntrega = fechaEntrega;
		this.horaEntrega = horaEntrega;
		this.empleado = empleado;
		this.estadoActividad = estadoActividad;
	}

	public Long getCodActividad() {
		return codActividad;
	}

	public void setCodActividad(Long codActividad) {
		this.codActividad = codActividad;
	}

	public Empleado getEmpleado() {
		return empleado;
	}

	public void setEmpleado(Empleado empleado) {
		this.empleado = empleado;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public Date getFechaEntrega() {
		return fechaEntrega;
	}

	public void setFechaEntrega(Date fechaEntrega) {
		this.fechaEntrega = fechaEntrega;
	}

	public Date getHoraEntrega() {
		return horaEntrega;
	}

	public void setHoraEntrega(Date horaEntrega) {
		this.horaEntrega = horaEntrega;
	}

	public String getEstadoActividad() {
		return estadoActividad;
	}

	public void setEstadoActividad(String estadoActividad) {
		this.estadoActividad = estadoActividad;
	}

}
