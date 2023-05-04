package com.proyecto.apartahotel.sispart.facturacion.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.proyecto.apartahotel.sispart.huesped.entity.Huesped;

@Entity
@Table(name = " factura")
public class Factura implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long codFactura;

	@Column(length = 100)
	private String descripcion;

	@JsonIgnoreProperties(value = { "hibernateLazyInitializer", "handler" }, allowSetters = true)
	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "cod_factura")
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

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "cod_huesped")
	@JsonIgnoreProperties(value = { "facturas", "hibernateLazyInitializer", "handler" }, allowSetters = true)
	private Huesped huesped;

	public Factura() {

		this.itemFactura = new ArrayList<>();
	}

	public Factura(Long codFactura, String descripcion, Huesped huesped) {

		this.codFactura = codFactura;
		this.descripcion = descripcion;
		this.huesped = huesped;
	}

	public Factura(String descripcion, Huesped huesped) {

		this.descripcion = descripcion;
		this.huesped = huesped;
	}

	@PrePersist
	public void prePersist() {
		fechaCreacion = new Date();
		horaCreacion = new Date();

	}

	public Long getCodFactura() {
		return codFactura;
	}

	public void setCodFactura(Long codFactura) {
		this.codFactura = codFactura;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
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

	public Huesped getHuesped() {
		return huesped;
	}

	public void setHuesped(Huesped huesped) {
		this.huesped = huesped;
	}

	public List<ItemFactura> getItemFactura() {
		return itemFactura;
	}

	public void setItemFactura(List<ItemFactura> itemFactura) {
		this.itemFactura = itemFactura;
	}

	public Double getTotal() {
		Double total = 0.00;

		for (ItemFactura itemsFacturas : itemFactura) {
			total += itemsFacturas.getSubtotal();
		}

		return total;
	}

	public Double setCambio(Double recibido) {

		Double nuevoSaldo = 0.00;
		nuevoSaldo = recibido - getTotal();

		return nuevoSaldo;
	}

	private static final long serialVersionUID = -4099422168283654087L;

}
