package com.proyecto.apartahotel.sispart.dto;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.proyecto.apartahotel.sispart.entity.CheckIn;
import com.proyecto.apartahotel.sispart.entity.Huesped;
import com.proyecto.apartahotel.sispart.entity.ItemFactura;

public class FacturaDTO {

	@NotEmpty
	private String descripcion;

	private List<ItemFactura> itemFactura;

	@Column(name = "fecha_creacion", nullable = false)
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "dd-MM-yyyy")
	@JsonFormat(pattern = "dd-MM-yyyy", timezone = "GMT-5")
	private Date fechaCreacion;

	@Column(name = "hora_creacion", nullable = false)
	@Temporal(TemporalType.TIME)
	@DateTimeFormat(pattern = "HH:mm:ss")
	@JsonFormat(pattern = "HH:mm:ss", timezone = "GMT-5")
	private Date horaCreacion;

	@NotNull
	private CheckIn checkIn;

	private String estado;

	public FacturaDTO() {

		this.itemFactura = new ArrayList<>();
	}

	public FacturaDTO(@NotEmpty String descripcion, List<ItemFactura> itemFactura, @NotNull CheckIn checkin,
			String estado) {

		this.descripcion = descripcion;
		this.itemFactura = itemFactura;
		this.checkIn = checkin;
		this.estado = estado;

	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public List<ItemFactura> getItemFactura() {
		return itemFactura;
	}

	public void setItemFactura(List<ItemFactura> itemFactura) {
		this.itemFactura = itemFactura;
	}

	public Date getFechaCreacion() {
		return fechaCreacion;
	}

	public void setFechaCreacion(Date fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}

	public Date getHoraCreacion() {
		return horaCreacion;
	}

	public void setHoraCreacion(Date horaCreacion) {
		this.horaCreacion = horaCreacion;
	}

	public CheckIn getCheckIn() {
		return checkIn;
	}

	public void setCheckIn(CheckIn checkIn) {
		this.checkIn = checkIn;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

}
