package com.proyecto.apartahotel.sispart.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "tipos_de_sangre")
public class TipoSangre implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "cod_tipo_sangre")
	private Long codTipoSangre;

	@Column(name = "nom_tipo_sangre", length = 30, nullable = false)
	private String nomTipoSangre;

	public TipoSangre() {
	}

	public TipoSangre(Long codTipoSangre, String nomTipoSangre) {

		this.codTipoSangre = codTipoSangre;
		this.nomTipoSangre = nomTipoSangre;
	}

	public TipoSangre(String nomTipoSangre) {

		this.nomTipoSangre = nomTipoSangre;
	}

	public Long getCodTipoSangre() {
		return codTipoSangre;
	}

	public void setCodTipoSangre(Long codTipoSangre) {
		this.codTipoSangre = codTipoSangre;
	}

	public String getNomTipoSangre() {
		return nomTipoSangre;
	}

	public void setNomTipoSangre(String nomTipoSangre) {
		this.nomTipoSangre = nomTipoSangre;
	}

	private static final long serialVersionUID = -7038267316447102380L;

}
