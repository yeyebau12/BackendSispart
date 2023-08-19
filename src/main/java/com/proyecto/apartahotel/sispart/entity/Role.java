package com.proyecto.apartahotel.sispart.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "role")
public class Role implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "cod_role")
	private Integer codRole;

	@Column(unique = true, length = 20, nullable = false)
	private String nombre;

	public Role() {

	}

	public Role(Integer codRole, String nombre) {
		this.codRole = codRole;
		this.nombre = nombre;
	}

	public Role(String nombre) {
		this.nombre = nombre;
	}

	public Integer getCodRole() {
		return codRole;
	}

	public void setCodRole(Integer codRole) {
		this.codRole = codRole;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	private static final long serialVersionUID = 1L;

}
