package com.proyecto.apartahotel.sispart.dto;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

public class ProductsIntDTO {
	@NotEmpty
	private String nombreProducto;
	@NotEmpty
	private String marca;
	@NotNull
	private Integer cantidad;
	@NotNull
	private double precio;

	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT-5")
	@Column(name = "fecha_registro", nullable = false)
	private Date fechaRegistro;

	@Temporal(TemporalType.TIME)
	@DateTimeFormat(pattern = "HH:mm:ss")
	@JsonFormat(pattern = "HH:mm:ss", timezone = "GMT-5")
	@Column(name = "fecha_registro", nullable = false)
	private Date horaRegistro;

	public ProductsIntDTO() {

	}

	public ProductsIntDTO(@NotEmpty String nombreProducto, @NotEmpty String marca, @NotNull Integer cantidad,
			@NotNull double precio) {

		this.nombreProducto = nombreProducto;
		this.marca = marca;
		this.cantidad = cantidad;
		this.precio = precio;

	}

	public String getNombreProducto() {
		return nombreProducto;
	}

	public void setNombreProducto(String nombreProducto) {
		this.nombreProducto = nombreProducto;
	}

	public String getMarca() {
		return marca;
	}

	public void setMarca(String marca) {
		this.marca = marca;
	}

	public Integer getCantidad() {
		return cantidad;
	}

	public void setCantidad(Integer cantidad) {
		this.cantidad = cantidad;
	}

	public double getPrecio() {
		return precio;
	}

	public void setPrecio(double precio) {
		this.precio = precio;
	}

	public Date getFechaRegistro() {
		return fechaRegistro;
	}

	public void setFechaRegistro(Date fechaRegistro) {
		this.fechaRegistro = fechaRegistro;
	}

	public Date getHoraRegistro() {
		return horaRegistro;
	}

	public void setHoraRegistro(Date horaRegistro) {
		this.horaRegistro = horaRegistro;
	}

}
