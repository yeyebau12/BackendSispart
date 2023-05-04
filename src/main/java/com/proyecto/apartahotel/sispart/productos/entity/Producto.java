package com.proyecto.apartahotel.sispart.productos.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
@Table(name = "producto")
public class Producto implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "cod_producto")
	private Long codProducto;

	@Column(name = "nombre_producto", length = 40, nullable = false)
	private String nombreProducto;
	@Column(length = 40, nullable = false)
	private String marca;
	@Column(length = 40, nullable = false)
	private double cantidad;
	@Column(length = 40, nullable = false)
	private double precio;

	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "dd-MM-yyyy")
	@JsonFormat(pattern = "dd-MM-yyyy", timezone = "GMT-5")
	@Column(name = "fecha_registro", nullable = false)
	private Date fechaRegistro;

	@Temporal(TemporalType.TIME)
	@DateTimeFormat(pattern = "HH:mm:ss")
	@JsonFormat(pattern = "HH:mm:ss", timezone = "GMT-5")
	@Column(name = "hora_registro", nullable = false)
	private Date horaRegistro;

	public Producto() {

	}

	public Producto(Long codProducto, String nombreProducto, String marca, double cantidad, double precio,
			Date fechaRegistro, Date horaRegistro) {
		this.codProducto = codProducto;
		this.nombreProducto = nombreProducto;
		this.marca = marca;
		this.cantidad = cantidad;
		this.precio = precio;
	}

	public Producto(String nombreProducto, String marca, double cantidad, double precio) {

		this.nombreProducto = nombreProducto;
		this.marca = marca;
		this.cantidad = cantidad;
		this.precio = precio;
	}

	@PrePersist
	public void prePersist() {
		fechaRegistro = new Date();
		horaRegistro = new Date();

	}

	public Long getCodProducto() {
		return codProducto;
	}

	public void setCodProducto(Long codProducto) {
		this.codProducto = codProducto;
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

	public double getPrecio() {
		return precio;
	}

	public double getCantidad() {
		return cantidad;
	}

	public void setCantidad(double cantidad) {
		this.cantidad = cantidad;
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

	private static final long serialVersionUID = 2440858225424655045L;

}
