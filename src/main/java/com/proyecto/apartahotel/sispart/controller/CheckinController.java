package com.proyecto.apartahotel.sispart.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.proyecto.apartahotel.sispart.dto.CheckinDTO;
import com.proyecto.apartahotel.sispart.entity.CheckIn;
import com.proyecto.apartahotel.sispart.entity.Habitacion;
import com.proyecto.apartahotel.sispart.entity.Huesped;
import com.proyecto.apartahotel.sispart.entity.TipDocumento;
import com.proyecto.apartahotel.sispart.service.interfa.ICheckinService;
import com.proyecto.apartahotel.sispart.service.interfa.IHabitacionesService;
import com.proyecto.apartahotel.sispart.service.interfa.IHuespedService;

@RestController
@RequestMapping("/checkin")

public class CheckinController {

	@Autowired
	private ICheckinService checkinService;

	@Autowired
	private IHuespedService huespedService;

	@Autowired
	private IHabitacionesService habitacionService;

	@GetMapping("/listarCheckin")
	public ResponseEntity<?> findAll() {

		List<CheckIn> findAll = null;
		Map<String, Object> response = new HashMap<>();

		try {

			findAll = checkinService.findAll();

		} catch (DataAccessException e) {
			response.put("mensaje", "Error al listar los registros de la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
		}

		if (findAll.isEmpty()) {
			response.put("mensaje", "No existen registros en la base de datos");
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}

		return new ResponseEntity<List<CheckIn>>(findAll, HttpStatus.OK);

	}

	@GetMapping("/listarCheckin/page/{page}")
	public ResponseEntity<?> findAll(@PathVariable("page") Integer page) {

		Pageable pageable = PageRequest.of(page, 5);
		Page<CheckIn> findAll = null;
		Map<String, Object> response = new HashMap<>();

		try {

			findAll = checkinService.findAll(pageable);

		} catch (DataAccessException e) {
			response.put("mensaje", "Error al listar los registros de la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
		}

		if (findAll.isEmpty()) {
			response.put("mensaje", "No existen registros en la base de datos");
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}

		return new ResponseEntity<Page<CheckIn>>(findAll, HttpStatus.OK);
	}

	@GetMapping("/verCheckin/{codCheckin}")
	public ResponseEntity<?> detailCheckin(@PathVariable("codCheckin") Long codCheckin) {

		CheckIn checkin = null;
		Map<String, Object> response = new HashMap<>();

		try {

			checkin = checkinService.getOne(codCheckin);

		} catch (DataAccessException e) {
			response.put("mensaje", "Error al realizar la consulta en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		if (!checkinService.existsById(codCheckin)) {
			response.put("mensaje", "El checkin no existe en la base de datos");
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}

		return new ResponseEntity<CheckIn>(checkin, HttpStatus.OK);

	}

	@PostMapping("/crearCheckin/{tipDocumento}/{numDocumento}")
	public ResponseEntity<?> createdCheckin(@Valid @RequestBody CheckinDTO checkinDTO,
			@PathVariable("tipDocumento") TipDocumento tipDocumento, @PathVariable("numDocumento") Long numDocumento,
			BindingResult result) {

		Huesped huesped = null;
		Habitacion habitacion = habitacionService.findByCodHabitacion(checkinDTO.getCodHabitacion().getCodHabitacion());
		;
		Map<String, Object> response = new HashMap<>();

		if (!huespedService.existsByTipoDocumentoAndNumDocumento(tipDocumento, numDocumento)) {

			response.put("mensaje", "El huesped identificado con : " + tipDocumento.getNomTipoDocumento() + ": "
					+ numDocumento + " no se encuentra registrado ");

			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);

		} else if (!habitacionService.existsById(checkinDTO.getCodHabitacion().getCodHabitacion())) {

			response.put("mensaje", "No se encuentra la habitación ");

			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);

		} else if (habitacion.getEstadoHabitacion().equalsIgnoreCase("Ocupado")) {

			response.put("mensaje", "La habitacion se encuentra Ocupada. ");

			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);

		} else if (habitacion.getEstadoHabitacion().equalsIgnoreCase("Limpieza")) {

			response.put("mensaje", "La habitacion se encuentra en limpieza. ");

			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);

		}else if (habitacion.getEstadoHabitacion().equalsIgnoreCase("Reservada")) {

			response.put("mensaje", "La habitacion ya se encuentra reservada. ");

			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);

		} else if (result.hasErrors()) {

			List<String> errors = result.getFieldErrors().stream()
					.map(err -> "El campo '" + err.getField() + "' " + err.getDefaultMessage())
					.collect(Collectors.toList());

			response.put("error", errors);
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);

		}

		try {

			huesped = huespedService.getByTipoDocumentoAndNumDocumento(tipDocumento, numDocumento);

			CheckIn checkin = new CheckIn(checkinDTO.getFechaEntrada(), checkinDTO.getFechaSalida(), huesped,
					checkinDTO.getCodHabitacion());

			habitacion.setEstadoHabitacion("Ocupado");

			checkinService.save(checkin);
			habitacionService.save(habitacion);

		} catch (DataAccessException e) {
			response.put("mensaje", "Error al insertar el registro del huesped en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		response.put("mensaje", "El checkin ha sido creado con exito!");

		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);

	}

	
}
