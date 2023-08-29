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
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.proyecto.apartahotel.sispart.dto.HabitacionDTO;
import com.proyecto.apartahotel.sispart.entity.Habitacion;
import com.proyecto.apartahotel.sispart.entity.Huesped;
import com.proyecto.apartahotel.sispart.service.interfa.IHabitacionesService;

@RestController
@RequestMapping("/habitacion")
public class habitacionesController {

	@Autowired
	private IHabitacionesService habitacionService;

	@GetMapping("/listarHabitaciones")
	public ResponseEntity<?> findAll() {

		List<Habitacion> findAll = null;
		Map<String, Object> response = new HashMap<>();

		try {

			findAll = habitacionService.findAll();

		} catch (DataAccessException e) {
			response.put("mensaje", "Error al listar los registros de la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}

		if (findAll.isEmpty()) {
			response.put("mensaje", "No existen registros en la base de datos");
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}

		return new ResponseEntity<List<Habitacion>>(findAll, HttpStatus.OK);
	}
	
	@GetMapping("/listarHabitaciones/page/{page}")
	public ResponseEntity<?> findAll(@PathVariable("page") Integer page) {

		Pageable pageable = PageRequest.of(page, 5);
		Page<Habitacion> findAll = null;
		Map<String, Object> response = new HashMap<>();

		try {

			findAll = habitacionService.findAll(pageable);

		} catch (DataAccessException e) {
			response.put("mensaje", "Error al listar los registros de la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}

		if (findAll.isEmpty()) {
			response.put("mensaje", "No existen registros en la base de datos");
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}

		return new ResponseEntity<Page<Habitacion>>(findAll, HttpStatus.OK);
	}

	@GetMapping("/verHabitacion/{codHabitacion}")
	public ResponseEntity<?> detailHabitacion(@PathVariable("codHabitacion") Long codHabitacion) {

		Habitacion habitacion = null;
		Map<String, Object> response = new HashMap<>();

		try {

			habitacion = habitacionService.findByCodHabitacion(codHabitacion);

		} catch (DataAccessException e) {
			response.put("mensaje", "Error al realizar la consulta en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		if (!habitacionService.existsById(codHabitacion)) {
			response.put("mensaje", "La habitacion no existe en la base de datos");
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}

		return new ResponseEntity<Habitacion>(habitacion, HttpStatus.OK);

	}

	@PostMapping("/crearHabitacion")
	public ResponseEntity<?> createdHabitacion(@Valid @RequestBody HabitacionDTO habitacionDTO, BindingResult result) {

		Map<String, Object> response = new HashMap<>();

		if (habitacionService.exitsNumHabitacion(habitacionDTO.getNumHabitacion())) {

			response.put("mensaje", "La habitacion que desea registrar ya existe en la base de datos");

			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);

		} else if (result.hasErrors()) {

			List<String> errors = result.getFieldErrors().stream()
					.map(err -> "El campo '" + err.getField() + "' " + err.getDefaultMessage())
					.collect(Collectors.toList());

			response.put("errors", errors);
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);

		}

		try {

			Habitacion habitacion = new Habitacion(habitacionDTO.getNombreHabitacion(),
					habitacionDTO.getDescripHabitacion(), habitacionDTO.getNumHabitacion(),
					habitacionDTO.getPisoHabitacion(), habitacionDTO.getMaxPersonasDisponibles(),
					habitacionDTO.getPrecioDia(), habitacionDTO.getEstadoHabitacion());

			habitacionService.save(habitacion);

		} catch (DataAccessException e) {
			response.put("mensaje", "Error al insertar el registro de la habitacion en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		response.put("mensaje", "la habitacion ha sido creada con exito!");

		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);

	}

	@PutMapping("/actualizarHabitacion/{codHabitacion}")
	public ResponseEntity<?> updateHabitacion(@Valid @RequestBody HabitacionDTO habitacionDTO,
			@PathVariable("codHabitacion") Long codHabitacion, BindingResult result) {

		Map<String, Object> response = new HashMap<>();

		if (!habitacionService.existsById(codHabitacion)) {

			response.put("mensaje", " ERROR: La habitacion no existe con el codigo : " + codHabitacion);

			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}

		if (habitacionService.exitsNumHabitacion(habitacionDTO.getNumHabitacion()) && habitacionService
				.findByNumHabitacion(habitacionDTO.getNumHabitacion()).getCodHabitacion() != codHabitacion) {

			response.put("mensaje", "ERROR: No es posible actualizar la habitacion numero: "
					+ habitacionDTO.getNumHabitacion() + " porque ya se encuentra registrada en la base de datos");

			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
		}

		if (result.hasErrors()) {

			List<String> errors = result.getFieldErrors().stream()
					.map(err -> "El campo '" + err.getField() + "' " + err.getDefaultMessage())
					.collect(Collectors.toList());

			response.put("errors", errors);
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);

		}

		try {

			Habitacion habitacion = habitacionService.findByCodHabitacion(codHabitacion);

			habitacion.setNombreHabitacion(habitacionDTO.getNombreHabitacion());
			habitacion.setDescripHabitacion(habitacionDTO.getDescripHabitacion());
			habitacion.setNumHabitacion(habitacionDTO.getNumHabitacion());
			habitacion.setPisoHabitacion(habitacionDTO.getPisoHabitacion());
			habitacion.setMaxPersonasDisponibles(habitacionDTO.getMaxPersonasDisponibles());
			habitacion.setPrecioDia(habitacionDTO.getPrecioDia());
			habitacion.setEstadoHabitacion(habitacionDTO.getEstadoHabitacion());

			habitacionService.save(habitacion);

		} catch (DataAccessException e) {
			response.put("mensaje", "Error al actualizar el registro de la habitacion en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		response.put("mensaje", "El registro de la habitacion  ha sido actualizado exitosamente!");

		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);

	}

	@DeleteMapping("/eliminarHabitacion/{codHabitacion}")
	public ResponseEntity<?> deleteHabitacion(@PathVariable("codHabitacion") Long codHabitacion) {
		Map<String, Object> response = new HashMap<>();

		try {
			habitacionService.delete(codHabitacion);

		} catch (DataAccessException e) {
			response.put("mensaje", "Error al eliminar el registro de la habitacion en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		response.put("mensaje", "la habitacion ha sido eliminado con exito!");

		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);

	}

}
