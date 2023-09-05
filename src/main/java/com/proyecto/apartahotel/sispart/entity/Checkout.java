package com.proyecto.apartahotel.sispart.entity;

import java.io.Serializable;
import java.util.ArrayList;
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
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
public class Checkout implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "cod_checkout")
	private Long codCheckout;

	@OneToOne
	@JoinColumn(name = "cod_checkin")
	private Checkin checkin;



	public Checkout() {


	}

	public Checkout(Long codCheckout, Checkin checkin) {

		this.codCheckout = codCheckout;
		this.checkin = checkin;

	}

	public Checkout(Checkin checkin) {

		this.checkin = checkin;

	}

	public Long getCodCheckout() {
		return codCheckout;
	}

	public void setCodCheckout(Long codCheckout) {
		this.codCheckout = codCheckout;
	}

	public Checkin getCheckin() {
		return checkin;
	}

	public void setCheckin(Checkin checkin) {
		this.checkin = checkin;
	}


	private static final long serialVersionUID = 8776634146078679256L;

}