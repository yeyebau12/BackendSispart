package com.proyecto.apartahotel.sispart.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "habitacion")
public class Habitacion implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long codHabitacion;

	@Column(length = 50, nullable = false)
	private String nombreHabitacion;
	@Column(nullable = false)
	private String descripHabitacion;
	@Column(name = "num_habitacion", nullable = false)
	private Integer numHabitacion;
	@Column(name = "piso_habitacion", nullable = false)
	private Integer pisoHabitacion;
	@Column(name = "max_personas_habitacion", nullable = false)
	private Integer maxPersonasDisponibles;
	@Column(name = "precio_habitacion", nullable = false)
	private Double precioDia;
	@Column(name = "estado_habitacion", nullable = false)
	private String estadoHabitacion;
	private String imagenHabitacion;

	/*
	 * @OneToMany(fetch = FetchType.LAZY, mappedBy = "habitacion", cascade =
	 * CascadeType.ALL)
	 * 
	 * @JsonIgnoreProperties(value = { "huesped", "hibernateLazyInitializer",
	 * "handler" }, allowSetters = true) private List<Factura> facturas;
	 */

	public Habitacion() {

		// this.facturas = new ArrayList<>();

	}

	public Habitacion(Long codHabitacion, String nombreHabitacion, String descripHabitacion, Integer numHabitacion,
			Integer pisoHabitacion, Integer maxPersonasDisponibles, Double precioDia, String estadoHabitacion) {

		this.codHabitacion = codHabitacion;
		this.nombreHabitacion = nombreHabitacion;
		this.descripHabitacion = descripHabitacion;
		this.numHabitacion = numHabitacion;
		this.pisoHabitacion = pisoHabitacion;
		this.maxPersonasDisponibles = maxPersonasDisponibles;
		this.precioDia = precioDia;
		this.estadoHabitacion = estadoHabitacion;

	}

	public Habitacion(String nombreHabitacion, String descripHabitacion, Integer numHabitacion, Integer pisoHabitacion,
			Integer maxPersonasDisponibles, Double precioDia, String estadoHabitacion) {

		this.nombreHabitacion = nombreHabitacion;
		this.descripHabitacion = descripHabitacion;
		this.numHabitacion = numHabitacion;
		this.pisoHabitacion = pisoHabitacion;
		this.maxPersonasDisponibles = maxPersonasDisponibles;
		this.precioDia = precioDia;
		this.estadoHabitacion = estadoHabitacion;

	}

	public Long getCodHabitacion() {
		return codHabitacion;
	}

	public void setCodHabitacion(Long codHabitacion) {
		this.codHabitacion = codHabitacion;
	}

	public String getNombreHabitacion() {
		return nombreHabitacion;
	}

	public void setNombreHabitacion(String nombreHabitacion) {
		this.nombreHabitacion = nombreHabitacion;
	}

	public String getDescripHabitacion() {
		return descripHabitacion;
	}

	public void setDescripHabitacion(String descripHabitacion) {
		this.descripHabitacion = descripHabitacion;
	}

	public Integer getNumHabitacion() {
		return numHabitacion;
	}

	public void setNumHabitacion(Integer numHabitacion) {
		this.numHabitacion = numHabitacion;
	}

	public Integer getPisoHabitacion() {
		return pisoHabitacion;
	}

	public void setPisoHabitacion(Integer pisoHabitacion) {
		this.pisoHabitacion = pisoHabitacion;
	}

	public Integer getMaxPersonasDisponibles() {
		return maxPersonasDisponibles;
	}

	public void setMaxPersonasDisponibles(Integer maxPersonasDisponibles) {
		this.maxPersonasDisponibles = maxPersonasDisponibles;
	}

	public Double getPrecioDia() {
		return precioDia;
	}

	public void setPrecioDia(Double precioDia) {
		this.precioDia = precioDia;
	}

	/*
	 * public List<Factura> getFacturas() { return facturas; }
	 * 
	 * public void setFacturas(List<Factura> facturas) { this.facturas = facturas; }
	 */
	public String getEstadoHabitacion() {
		return estadoHabitacion;
	}

	public void setEstadoHabitacion(String estadoHabitacion) {
		this.estadoHabitacion = estadoHabitacion;
	}

	public String getImagenHabitacion() {
		return imagenHabitacion;
	}

	public void setImagenHabitacion(String imagenHabitacion) {
		this.imagenHabitacion = imagenHabitacion;
	}

	private static final long serialVersionUID = 6472275847130544633L;

}
