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
import org.springframework.security.access.annotation.Secured;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.proyecto.apartahotel.sispart.dto.CheckInDTO;
import com.proyecto.apartahotel.sispart.entity.CheckIn;
import com.proyecto.apartahotel.sispart.entity.EstadoHabitacion;
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

	@Secured({ "ROLE_ADMINISTRADOR", "ROLE_RECEPCIONISTA" })
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

	@Secured({ "ROLE_ADMINISTRADOR", "ROLE_RECEPCIONISTA" })
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

	@Secured({ "ROLE_ADMINISTRADOR", "ROLE_RECEPCIONISTA" })
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

	@Secured({ "ROLE_ADMINISTRADOR", "ROLE_RECEPCIONISTA" })
	@PostMapping("/crearCheckin")
	public ResponseEntity<?> createdCheckin(@Valid @RequestBody CheckInDTO checkinDTO, BindingResult result) {

		Map<String, Object> response = new HashMap<>();

		if (checkinDTO.getCodHuesped().isEstadoHuesped() == false) {
			response.put("mensaje",
					"El husped se encuentra inactivo por el adminitrador,no puede hospedarse en el apartahotel");
			return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
		}

		if (checkinDTO.getCodHabitacion().getEstadoHabitacion().getCodEstadoHabitacion() == 2) {

			response.put("mensaje", "La habitacion que desea asignar esta ocupada por otro huesped!");
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);

		}

		if (checkinDTO.getCodHabitacion().getEstadoHabitacion().getCodEstadoHabitacion() == 3) {

			response.put("mensaje", "La habitacion que desea asignar ya se encuentra reservada!");
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);

		}

		if (checkinDTO.getCodHabitacion().getEstadoHabitacion().getCodEstadoHabitacion() == 4) {

			response.put("mensaje", "La habitacion que desea asignar esta en proceso de limpieza!");
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);

		}

		if (checkinDTO.getTotalAcompañantes() > checkinDTO.getCodHabitacion().getMaxPersonasDisponibles()) {

			response.put("mensaje", "La cantidad de acompañantes es demasiado grande para este tipo de habitacion!");
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);

		}

		if (result.hasErrors()) {

			List<String> errors = result.getFieldErrors().stream()
					.map(err -> "El campo '" + err.getField() + "' " + err.getDefaultMessage())
					.collect(Collectors.toList());

			response.put("error", errors);
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);

		}

		try {

			CheckIn checkin = new CheckIn(checkinDTO.getFechaEntrada(), checkinDTO.getFechaSalida(),
					checkinDTO.getCodHuesped(), checkinDTO.getCodHabitacion(), checkinDTO.getNumAdultos(),
					checkinDTO.getNumNinos());

			Habitacion habitacion = habitacionService
					.findByCodHabitacion(checkinDTO.getCodHabitacion().getCodHabitacion());

			EstadoHabitacion estadoHabitacion = new EstadoHabitacion();
			estadoHabitacion.setCodEstadoHabitacion((long) 3);
			estadoHabitacion.setNombre("OCUPADA");

			habitacion.setEstadoHabitacion(estadoHabitacion);

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
