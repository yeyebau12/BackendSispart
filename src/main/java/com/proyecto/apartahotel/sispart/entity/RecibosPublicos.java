package com.proyecto.apartahotel.sispart.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
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

@Entity
@Table(name = "recibo_publico")
public class RecibosPublicos implements Serializable {




	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "cod_recibo")
	private Long codRecibo;

	@ManyToOne
	@JoinColumn(name = "cod_tip_recibo", nullable = false)
	private TipoRecibo tipRecibo;

	@Column(name = "num_referencia", length = 35, nullable = false)
	private String numReferencia;

	@Column(name = "pago_oportuno", length = 30, nullable = false)
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT-5")
	private Date pagoOportuno;

	@Column(length = 30, nullable = false)
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT-5")
	private Date supension;

	@Column(name = "total_pago", length = 30, nullable = false)
	private Double totalPagar;

	@Column(name = "documento_recibo")
	private String docRecibo;

	public RecibosPublicos() {

	}

	public RecibosPublicos(Long codRecibo, TipoRecibo tipRecibo, String numReferencia, Date pagoOportuno,
			Date supension, Double totalPagar, String docRecibo) {

		this.codRecibo = codRecibo;
		this.tipRecibo = tipRecibo;
		this.numReferencia = numReferencia;
		this.pagoOportuno = pagoOportuno;
		this.supension = supension;
		this.totalPagar = totalPagar;
		this.docRecibo = docRecibo;
	}

	public RecibosPublicos(TipoRecibo tipRecibo, String numReferencia, Date pagoOportuno, Date supension,
			Double totalPagar, String docRecibo) {
		
		this.tipRecibo = tipRecibo;
		this.numReferencia = numReferencia;
		this.pagoOportuno = pagoOportuno;
		this.supension = supension;
		this.totalPagar = totalPagar;
		this.docRecibo = docRecibo;
	}

	public Long getCodRecibo() {
		return codRecibo;
	}

	public void setCodRecibo(Long codRecibo) {
		this.codRecibo = codRecibo;
	}

	public TipoRecibo getTipRecibo() {
		return tipRecibo;
	}

	public void setTipRecibo(TipoRecibo tipRecibo) {
		this.tipRecibo = tipRecibo;
	}

	public String getNumReferencia() {
		return numReferencia;
	}

	public void setNumReferencia(String numReferencia) {
		this.numReferencia = numReferencia;
	}

	public Date getPagoOportuno() {
		return pagoOportuno;
	}

	public void setPagoOportuno(Date pagoOportuno) {
		this.pagoOportuno = pagoOportuno;
	}

	public Date getSupension() {
		return supension;
	}

	public void setSupension(Date supension) {
		this.supension = supension;
	}

	public Double getTotalPagar() {
		return totalPagar;
	}

	public void setTotalPagar(Double totalPagar) {
		this.totalPagar = totalPagar;
	}

	public String getDocRecibo() {
		return docRecibo;
	}

	public void setDocRecibo(String docRecibo) {
		this.docRecibo = docRecibo;
	}

	private static final long serialVersionUID = -2786815690065365613L;
}
