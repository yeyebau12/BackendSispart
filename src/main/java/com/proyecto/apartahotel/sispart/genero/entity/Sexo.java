package com.proyecto.apartahotel.sispart.genero.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "sexo")
public class Sexo implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "cod_sexo")
	private Long codSexo;

	@Column(name = "nom_sexo", length = 30, nullable = false)
	private String nomSexo;

	public Sexo() {

	}

	public Sexo(Long codSexo, String nomSexo) {

		this.codSexo = codSexo;
		this.nomSexo = nomSexo;
	}

	public Sexo(String nomSexo) {

		this.nomSexo = nomSexo;
	}

	public Long getCodSexo() {
		return codSexo;
	}

	public void setCodSexo(Long codSexo) {
		this.codSexo = codSexo;
	}

	public String getNomSexo() {
		return nomSexo;
	}

	public void setNomSexo(String nomSexo) {
		this.nomSexo = nomSexo;
	}

	private static final long serialVersionUID = 1L;
}
