package com.proyecto.apartahotel.sispart.tipDocumento.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "tipos_de_documentos")
public class TipDocumento implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "cod_tipo_documento")
	private Long codTipoDocumento;

	@Column(name = "nom_tipo_documento", length = 30, nullable = false)
	private String nomTipoDocumento;

	public TipDocumento() {

	}

	public TipDocumento(Long codTipoDocumento, String nomTipoDocumento) {
		this.codTipoDocumento = codTipoDocumento;
		this.nomTipoDocumento = nomTipoDocumento;
	}

	public TipDocumento(String nomTipoDocumento) {
		this.nomTipoDocumento = nomTipoDocumento;
	}

	public Long getCodTipoDocumento() {
		return codTipoDocumento;
	}

	public void setCodTipoDocumento(Long codTipoDocumento) {
		this.codTipoDocumento = codTipoDocumento;
	}

	public String getNomTipoDocumento() {
		return nomTipoDocumento;
	}

	public void setNomTipoDocumento(String nomTipoDocumento) {
		this.nomTipoDocumento = nomTipoDocumento;
	}

	private static final long serialVersionUID = 1L;

}
