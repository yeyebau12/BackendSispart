package com.proyecto.apartahotel.sispart.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "tipo_recibo")
public class TipoRecibo implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "cod_tip_recibo")
	private Long codTipRecibo;

	@Column(length = 30, nullable = false)
	private String empresaPub;

	public TipoRecibo() {

	}

	public TipoRecibo(Long codTipRecibo, String empresaPub) {

		this.codTipRecibo = codTipRecibo;
		this.empresaPub = empresaPub;
	}

	public TipoRecibo(String empresaPub) {

		this.empresaPub = empresaPub;
	}

	public Long getCodTipRecibo() {
		return codTipRecibo;
	}

	public void setCodTipRecibo(Long codTipRecibo) {
		this.codTipRecibo = codTipRecibo;
	}

	public String getEmpresaPub() {
		return empresaPub;
	}

	public void setEmpresaPub(String empresaPub) {
		this.empresaPub = empresaPub;
	}

	private static final long serialVersionUID = 7752731491336886908L;

}
