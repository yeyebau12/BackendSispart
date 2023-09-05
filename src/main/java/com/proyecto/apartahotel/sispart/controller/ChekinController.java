package com.proyecto.apartahotel.sispart.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.proyecto.apartahotel.sispart.dto.CheckInDTO;
import com.proyecto.apartahotel.sispart.dto.EmpleadoDTO;
import com.proyecto.apartahotel.sispart.dto.HuespedDTO;
import com.proyecto.apartahotel.sispart.entity.Checkin;
import com.proyecto.apartahotel.sispart.entity.Habitacion;
import com.proyecto.apartahotel.sispart.entity.Huesped;
import com.proyecto.apartahotel.sispart.entity.Reservacion;
import com.proyecto.apartahotel.sispart.entity.TipDocumento;
import com.proyecto.apartahotel.sispart.service.interfa.ICheckinService;
import com.proyecto.apartahotel.sispart.service.interfa.IHabitacionesService;
import com.proyecto.apartahotel.sispart.service.interfa.IHuespedService;
import com.proyecto.apartahotel.sispart.service.interfa.IReservacionService;

@RestController
@RequestMapping("/checkin")
public class ChekinController {

	@Autowired
	private ICheckinService checkInService;
	@Autowired
	private IReservacionService reservacionService;

	@Autowired
	private IHuespedService huespedService;

	@Autowired
	private IHabitacionesService habitacionesService;

	@GetMapping("/listarCheckin")
	public ResponseEntity<?> findAll() {

		List<Checkin> findAll = null;
		Map<String, Object> response = new HashMap<>();

		try {

			findAll = checkInService.findAll();

		} catch (DataAccessException e) {
			response.put("mensaje", "Error al listar los registros de la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}

		if (findAll.isEmpty()) {
			response.put("mensaje", "No existen registros en la base de datos");
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}

		return new ResponseEntity<List<Checkin>>(findAll, HttpStatus.OK);
	}

	@GetMapping("/verCheckin/{codCheckin}")
	public ResponseEntity<?> detailCheckin(@PathVariable("codCheckin") Long codCheckin) {

		Checkin checkin = null;
		Map<String, Object> response = new HashMap<>();

		try {

			checkin = checkInService.findByCodCheckin(codCheckin);

		} catch (DataAccessException e) {
			response.put("mensaje", "Error al realizar la consulta en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		if (!checkInService.existsById(codCheckin)) {
			response.put("mensaje", "El checkin  no existe en la base de datos");
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}

		return new ResponseEntity<Checkin>(checkin, HttpStatus.OK);

	}

	@PostMapping("/crearCheckin")
	public ResponseEntity<?> createdCheckin(@Valid @RequestBody CheckInDTO checkinDTO, BindingResult result) {

		Map<String, Object> response = new HashMap<>();

		Habitacion habitacion = checkinDTO.getHabitacion();
		Huesped huesped = checkinDTO.getHuesped();

		if (result.hasErrors()) {

			List<String> errors = result.getFieldErrors().stream()
					.map(err -> "El campo '" + err.getField() + "' " + err.getDefaultMessage())
					.collect(Collectors.toList());

			response.put("errors", errors);
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);

		}

		if (habitacion.getEstadoHabitacion().equalsIgnoreCase("Ocupada")) {

			response.put("mensaje", "La habitacion ya esta ocupada!");
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);

		} else {
			habitacion.setEstadoHabitacion("Ocupada");

			response.put("mensaje", "La habitacion se le ha asignado al huesped : " + huesped.getNombre() + " "
					+ huesped.getApellido());

			habitacionesService.save(habitacion);
		}

		try {

			Checkin checkIn = new Checkin(checkinDTO.getFechaIngreso(), checkinDTO.getFechaSalida(),
					checkinDTO.getHuesped(), checkinDTO.getHabitacion());

			checkInService.save(checkIn);

		} catch (DataAccessException e) {
			response.put("mensaje", "Error al insertar el registro del Check In en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		response.put("mensaje", "Se ha registrado correctamente el Checkin!");

		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);

	}



}
