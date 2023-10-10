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
@Table(name = "region")
public class Region  implements Serializable{




	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "cod_region")
	private Long codRegion;

	@ManyToOne
	@JoinColumn(name = "cod_nacionalidad", nullable = false)
	private Nacionalidad nacionalidad;

	@Column(length = 30, nullable = false)
	private String nombre;

	public Region() {

	}

	public Region(Long codRegion, Nacionalidad nacionalidad, String nombre) {

		this.codRegion = codRegion;
		this.nacionalidad = nacionalidad;
		this.nombre = nombre;
	}

	public Region(Nacionalidad nacionalidad, String nombre) {
		super();
		this.nacionalidad = nacionalidad;
		this.nombre = nombre;
	}

	public Long getCodRegion() {
		return codRegion;
	}

	public void setCodRegion(Long codRegion) {
		this.codRegion = codRegion;
	}

	public Nacionalidad getNacionalidad() {
		return nacionalidad;
	}

	public void setNacionalidad(Nacionalidad nacionalidad) {
		this.nacionalidad = nacionalidad;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	private static final long serialVersionUID = -2712976841914234968L;
}
