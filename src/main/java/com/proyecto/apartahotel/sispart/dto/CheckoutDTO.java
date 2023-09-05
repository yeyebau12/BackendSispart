package com.proyecto.apartahotel.sispart.dto;

import java.util.ArrayList;
import java.util.List;

import com.proyecto.apartahotel.sispart.entity.Checkin;
import com.proyecto.apartahotel.sispart.entity.Factura;

public class CheckoutDTO {

	private Checkin checkin;

	

	public CheckoutDTO() {

	

	}

	public CheckoutDTO(Checkin checkin) {

		this.checkin = checkin;

	}

	public Checkin getCheckin() {
		return checkin;
	}

	public void setCheckin(Checkin checkin) {
		this.checkin = checkin;
	}



}
