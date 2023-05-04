package com.proyecto.apartahotel.sispart.reservacion.controller;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.proyecto.apartahotel.sispart.huesped.service.IHuespedServiceImpl;

@RestController
@RequestMapping("/reservacion")
public class ReservacionController {

	@Autowired
	private IHuespedServiceImpl huespedService;

}
