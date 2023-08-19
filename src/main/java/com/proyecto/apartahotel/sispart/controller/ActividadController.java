package com.proyecto.apartahotel.sispart.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import com.proyecto.apartahotel.sispart.dto.ActividadDTO;
import com.proyecto.apartahotel.sispart.entity.Actividad;

import com.proyecto.apartahotel.sispart.service.interfa.IActividadService;
import com.proyecto.apartahotel.sispart.service.interfa.IEmpleadoService;



@RestController
@RequestMapping("actividades")
public class ActividadController {

	@Autowired
	private IActividadService actividadService;

	@Autowired
	private IEmpleadoService empleadoService;

	@GetMapping("/listarActividades")
	public ResponseEntity<?> findAll() {
		List<Actividad> findAll = null;
		Map<String, Object> response = new HashMap<>();

		try {

			findAll = actividadService.findAll();

		} catch (DataAccessException e) {
			response.put("mensaje", "Error al listar los registros de la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}

		if (findAll.isEmpty()) {
			response.put("mensaje", "No existen registros en la base de datos");
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}

		return new ResponseEntity<List<Actividad>>(findAll, HttpStatus.OK);
	}

	@GetMapping("/verActividad/{codActividad}")
	public ResponseEntity<?> detailActividad(@PathVariable("codActividad") Long codActividad) {

		Actividad actividad = null;
		Map<String, Object> response = new HashMap<>();

		try {

			actividad = actividadService.findByCodActividad(codActividad);

		} catch (DataAccessException e) {
			response.put("mensaje", "Error al realizar la consulta en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		if (!actividadService.existsByCodActividad(codActividad)) {
			response.put("mensaje", "La actividad no existe en la base de datos");
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}

		return new ResponseEntity<Actividad>(actividad, HttpStatus.OK);

	}

	@PostMapping("/crearActividad")
	public ResponseEntity<?> createActividad(@Valid @RequestBody ActividadDTO actividadDTO, BindingResult result) {

		Map<String, Object> response = new HashMap<>();

		if (result.hasErrors()) {

			List<String> errors = result.getFieldErrors().stream()
					.map(err -> "El campo '" + err.getField() + "' " + err.getDefaultMessage())
					.collect(Collectors.toList());

			response.put("errors", errors);
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);

		}

		if (!empleadoService.existsById(actividadDTO.getEmpleado().getCodEmpleado())) {

			response.put("mensaje", "El empleado no se encuentra registrado en la base de datos de empleados");

			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}

		try {

			Actividad actividad = new Actividad(actividadDTO.getTitulo(), actividadDTO.getDescripcion(),
					actividadDTO.getFechaEntrega(), actividadDTO.getHoraEntrega(), actividadDTO.getEmpleado(),
					actividadDTO.getEstadoActividad());

			actividadService.save(actividad);

		} catch (DataAccessException e) {
			response.put("mensaje", "Error al insertar el registro de la actividad en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		response.put("mensaje", "La actividad ha sido creada con exito!");

		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
	}

	@DeleteMapping("/eliminarActividad/{codActividad}")
	public ResponseEntity<?> deleteActividad(@PathVariable("codActividad") Long codActividad) {
		Map<String, Object> response = new HashMap<>();

		try {
			actividadService.delete(codActividad);

		} catch (DataAccessException e) {
			response.put("mensaje", "Error al eliminar el registro de la actividad en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		response.put("mensaje", "La actividad ha sido eliminada con exito!");

		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);

	}

}
