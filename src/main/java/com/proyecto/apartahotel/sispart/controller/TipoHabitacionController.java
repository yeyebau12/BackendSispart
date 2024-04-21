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
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.proyecto.apartahotel.sispart.dto.TipoHabitacionDTO;

import com.proyecto.apartahotel.sispart.entity.TipoHabitacion;
import com.proyecto.apartahotel.sispart.service.interfa.ITipoHabitacionService;

@RestController
@RequestMapping("/tipoHabiatcion")
public class TipoHabitacionController {

	@Autowired
	private ITipoHabitacionService tipoHabitacionService;

	@Secured({ "ROLE_ADMINISTRADOR" })
	@GetMapping("/listarTipoHabitacion")
	public ResponseEntity<?> findAll() {

		List<TipoHabitacion> findAll = null;
		Map<String, Object> response = new HashMap<>();

		try {

			findAll = tipoHabitacionService.findAll();

		} catch (DataAccessException e) {
			response.put("mensaje", "Error al listar los registros de la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}

		if (findAll.isEmpty()) {
			response.put("mensaje", "No existen registros en la base de datos");
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}

		return new ResponseEntity<List<TipoHabitacion>>(findAll, HttpStatus.OK);
	}

	@Secured({ "ROLE_ADMINISTRADOR" })
	@GetMapping("/listarTipoHabitacion/page/{page}")
	public ResponseEntity<?> findAll(@PathVariable("page") Integer page) {

		Pageable pageable = PageRequest.of(page, 5);
		Page<TipoHabitacion> findAll = null;
		Map<String, Object> response = new HashMap<>();

		try {

			findAll = tipoHabitacionService.findAll(pageable);

		} catch (DataAccessException e) {
			response.put("mensaje", "Error al listar los registros de la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}

		if (findAll.isEmpty()) {
			response.put("mensaje", "No existen registros en la base de datos");
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}

		return new ResponseEntity<Page<TipoHabitacion>>(findAll, HttpStatus.OK);
	}

	@Secured({ "ROLE_ADMINISTRADOR" })
	@PostMapping("/crearTipoHabitacion")
	public ResponseEntity<?> createdTipoHabitacion(@Valid @RequestBody TipoHabitacionDTO tipoHabitacionDTO,
			BindingResult result) {

		Map<String, Object> response = new HashMap<>();

		if (result.hasErrors()) {

			List<String> errors = result.getFieldErrors().stream()
					.map(err -> "El campo '" + err.getField() + "' " + err.getDefaultMessage())
					.collect(Collectors.toList());

			response.put("errors", errors);
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);

		}

		try {

			TipoHabitacion tipoHabitacion = new TipoHabitacion(tipoHabitacionDTO.getNombre(),
					tipoHabitacionDTO.getPrecioXPersona(), tipoHabitacionDTO.getPrecioXAcompanante());

			tipoHabitacionService.save(tipoHabitacion);

		} catch (DataAccessException e) {
			response.put("mensaje", "Error al insertar el registro del tipo de habitacion en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		response.put("mensaje", "El tipo de habitacion ha sido creado con exito!");

		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);

	}

	@Secured({ "ROLE_ADMINISTRADOR" })
	@PutMapping("/actualizarTipoHabitacion/{codTipoHabitacion}")
	public ResponseEntity<?> updateTipoHabitacion(@Valid @RequestBody TipoHabitacionDTO tipoHabitacionDTO,
			@PathVariable("codTipoHabitacion") Long codTipoHabitacion, BindingResult result) {

		Map<String, Object> response = new HashMap<>();

		if (!tipoHabitacionService.existsById(codTipoHabitacion)) {

			response.put("mensaje",
					" ERROR: El tipo de habitacion no existe con el codigo  numero: " + codTipoHabitacion);

			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}

		if (result.hasErrors()) {

			List<String> errors = result.getFieldErrors().stream()
					.map(err -> "El campo '" + err.getField() + "' " + err.getDefaultMessage())
					.collect(Collectors.toList());

			response.put("errors", errors);
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);

		}

		try {

			TipoHabitacion tipoHabitacion = tipoHabitacionService.findByCodTipoHabitacion(codTipoHabitacion);

			tipoHabitacion.setNombre(tipoHabitacionDTO.getNombre());
			tipoHabitacion.setPrecioXPersona(tipoHabitacionDTO.getPrecioXPersona());
			tipoHabitacion.setPrecioXAcompanante(tipoHabitacionDTO.getPrecioXAcompanante());

			tipoHabitacionService.save(tipoHabitacion);

		} catch (DataAccessException e) {
			response.put("mensaje", "Error al actualizar el registro del tipo de habitacion en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		response.put("mensaje", "El registro del tipo de habitacion ha sido actualizado exitosamente!");

		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);

	}

}
