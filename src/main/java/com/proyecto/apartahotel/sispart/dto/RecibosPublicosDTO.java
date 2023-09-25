package com.proyecto.apartahotel.sispart.dto;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.proyecto.apartahotel.sispart.entity.TipoRecibo;

public class RecibosPublicosDTO {

	@NotNull
	private TipoRecibo tipRecibo;
	@NotEmpty
	private String numReferencia;

	@NotNull
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT-5")
	private Date pagoOportuno;

	@NotNull
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT-5")
	private Date supension;

	@NotNull
	private Double totalPagar;
	private String docRecibo;

	public RecibosPublicosDTO() {

	}

	public RecibosPublicosDTO(@NotNull TipoRecibo tipRecibo, @NotEmpty String numReferencia, @NotNull Date pagoOportuno,
			@NotNull Date supension, @NotNull Double totalPagar, String docRecibo) {

		this.tipRecibo = tipRecibo;
		this.numReferencia = numReferencia;
		this.pagoOportuno = pagoOportuno;
		this.supension = supension;
		this.totalPagar = totalPagar;
		this.docRecibo = docRecibo;
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

}
