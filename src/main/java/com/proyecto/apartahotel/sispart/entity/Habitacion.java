package com.proyecto.apartahotel.sispart.entity;

import java.io.Serializable;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "habitacion")
public class Habitacion implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long codHabitacion;

	@ManyToOne
	@JoinColumn(name = "cod_tipo_habitacion", nullable = false)
	private TipoHabitacion nombreHabitacion;

	@Column(nullable = false)
	private String descripHabitacion;

	@Column(name = "num_habitacion", nullable = false)
	private Integer numHabitacion;

	@Column(name = "piso_habitacion", nullable = false)
	private Integer pisoHabitacion;

	@Column(name = "max_personas_habitacion", nullable = false)
	private Integer maxPersonasDisponibles;

	@ManyToOne
	@JoinColumn(name = "cod_estado_habitacion", nullable = false)
	private EstadoHabitacion estadoHabitacion;

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

	public Habitacion(Long codHabitacion, TipoHabitacion nombreHabitacion, String descripHabitacion,
			Integer numHabitacion, Integer pisoHabitacion, Integer maxPersonasDisponibles,
			EstadoHabitacion estadoHabitacion) {

		this.codHabitacion = codHabitacion;
		this.nombreHabitacion = nombreHabitacion;
		this.descripHabitacion = descripHabitacion;
		this.numHabitacion = numHabitacion;
		this.pisoHabitacion = pisoHabitacion;
		this.maxPersonasDisponibles = maxPersonasDisponibles;
		this.estadoHabitacion = estadoHabitacion;

	}

	public Habitacion(TipoHabitacion nombreHabitacion, String descripHabitacion, Integer numHabitacion,
			Integer pisoHabitacion, Integer maxPersonasDisponibles, EstadoHabitacion estadoHabitacion) {

		this.nombreHabitacion = nombreHabitacion;
		this.descripHabitacion = descripHabitacion;
		this.numHabitacion = numHabitacion;
		this.pisoHabitacion = pisoHabitacion;
		this.maxPersonasDisponibles = maxPersonasDisponibles;
		this.estadoHabitacion = estadoHabitacion;

	}

	public Long getCodHabitacion() {
		return codHabitacion;
	}

	public void setCodHabitacion(Long codHabitacion) {
		this.codHabitacion = codHabitacion;
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

	/*
	 * public List<Factura> getFacturas() { return facturas; }
	 * 
	 * public void setFacturas(List<Factura> facturas) { this.facturas = facturas; }
	 */

	public String getImagenHabitacion() {
		return imagenHabitacion;
	}

	public TipoHabitacion getNombreHabitacion() {
		return nombreHabitacion;
	}

	public void setNombreHabitacion(TipoHabitacion nombreHabitacion) {
		this.nombreHabitacion = nombreHabitacion;
	}

	public EstadoHabitacion getEstadoHabitacion() {
		return estadoHabitacion;
	}

	public void setEstadoHabitacion(EstadoHabitacion estadoHabitacion) {
		this.estadoHabitacion = estadoHabitacion;
	}

	public void setImagenHabitacion(String imagenHabitacion) {
		this.imagenHabitacion = imagenHabitacion;
	}

	private static final long serialVersionUID = 6472275847130544633L;

}
