package com.proyecto.apartahotel.sispart.controller;

import java.time.LocalDate;
import java.time.Period;
import java.util.Calendar;
import java.util.GregorianCalendar;
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
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.proyecto.apartahotel.sispart.dto.EmpleadoDTO;
import com.proyecto.apartahotel.sispart.entity.Empleado;
import com.proyecto.apartahotel.sispart.entity.UsuarioEmpleado;
import com.proyecto.apartahotel.sispart.service.interfa.IEmpleadoService;

@RestController
@RequestMapping("/empleados")
public class EmpleadoController {

	@Autowired
	private IEmpleadoService empleadoService;

	@Secured({"ROLE_ADMINISTRADOR"})
	@GetMapping("/listarEmpleados")
	public ResponseEntity<?> findAll() {

		List<Empleado> findAll = null;
		Map<String, Object> response = new HashMap<>();

		try {

			findAll = empleadoService.findAll();

		} catch (DataAccessException e) {
			response.put("mensaje", "Error al listar los registros de la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}

		if (findAll.isEmpty()) {
			response.put("mensaje", "No existen registros en la base de datos");
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}

		return new ResponseEntity<List<Empleado>>(findAll, HttpStatus.OK);
	}

	@Secured({"ROLE_ADMINISTRADOR"})
	@GetMapping("/listarEmpleados/page/{page}")
	public ResponseEntity<?> findAll(@PathVariable("page") Integer page) {

		Pageable pageable = PageRequest.of(page, 5);
		Page<Empleado> findAll = null;
		Map<String, Object> response = new HashMap<>();

		try {

			findAll = empleadoService.findAll(pageable);

		} catch (DataAccessException e) {
			response.put("mensaje", "Error al listar los registros de la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}

		if (findAll.isEmpty()) {
			response.put("mensaje", "No existen registros en la base de datos");
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}

		return new ResponseEntity<Page<Empleado>>(findAll, HttpStatus.OK);
	}

	@Secured({"ROLE_ADMINISTRADOR"})
	@GetMapping("/verEmpleado/{codEmpleado}")
	public ResponseEntity<?> detailEmpleado(@PathVariable("codEmpleado") Long codEmpelado) {

		Empleado empleado = null;
		Map<String, Object> response = new HashMap<>();

		try {

			empleado = empleadoService.findByCodEmpleado(codEmpelado);

		} catch (DataAccessException e) {
			response.put("mensaje", "Error al realizar la consulta en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		if (!empleadoService.existsById(codEmpelado)) {
			response.put("mensaje", "El empleado no existe en la base de datos");
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}

		return new ResponseEntity<Empleado>(empleado, HttpStatus.OK);

	}

	@Secured({"ROLE_ADMINISTRADOR"})
	@PostMapping("/crearEmpleado")
	public ResponseEntity<?> createdEmpleado(@Valid @RequestBody EmpleadoDTO empleadoDTO, BindingResult result) {

		Map<String, Object> response = new HashMap<>();

		Calendar calendarActual = Calendar.getInstance();
		Calendar calendarNacimiento = new GregorianCalendar();

		calendarNacimiento.setTime(empleadoDTO.getFechaNacimiento());

		int edad = calendarActual.get(Calendar.YEAR) - calendarNacimiento.get(Calendar.YEAR);

		if (calendarActual.get(Calendar.DAY_OF_YEAR) < calendarNacimiento.get(Calendar.DAY_OF_YEAR)) {
			edad--;
		}

		if (empleadoService.exitsTipDocumentoAndNumDocumento(empleadoDTO.getTipDocumento(),
				empleadoDTO.getNumDocumento())) {

			response.put("mensaje", "El empleado que desea registrar ya existe en la base de datos");

			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);

		} else if (result.hasErrors()) {

			List<String> errors = result.getFieldErrors().stream()
					.map(err -> "El campo '" + err.getField() + "' " + err.getDefaultMessage())
					.collect(Collectors.toList());

			response.put("errors", errors);
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);

		}

		try {

			Empleado empleado = new Empleado(empleadoDTO.getNombre(), empleadoDTO.getApellido(),
					empleadoDTO.getTipDocumento(), empleadoDTO.getNumDocumento(), edad, empleadoDTO.getNumTelefono(),
					empleadoDTO.getCorreo(), empleadoDTO.getFechaNacimiento(), empleadoDTO.getDireccion(),
					empleadoDTO.getNomContactoEmergencia(), empleadoDTO.getNumContactoEmergencia(),
					empleadoDTO.getEps(), empleadoDTO.getArl(), empleadoDTO.getSexo(), empleadoDTO.getTipoSangre(),
					empleadoDTO.getFechaIngreso(), empleadoDTO.getFechaSalida());

			empleadoService.save(empleado);

		} catch (DataAccessException e) {
			response.put("mensaje", "Error al insertar el registro del empleado en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		response.put("mensaje", "El empleado ha sido creado con exito!");

		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);

	}

	@Secured({"ROLE_ADMINISTRADOR"})
	@PutMapping("/actualizarEmpleado/{codEmpleado}")
	public ResponseEntity<?> updateEmpleado(@Valid @RequestBody EmpleadoDTO empleadoDTO,
			@PathVariable("codEmpleado") Long codEmpelado, BindingResult result) {

		Map<String, Object> response = new HashMap<>();

		Calendar calendarActual = Calendar.getInstance();
		Calendar calendarNacimiento = new GregorianCalendar();

		if (!empleadoService.existsById(codEmpelado)) {

			response.put("mensaje", " ERROR: El empleado no existe con el codigo de empleado numero: " + codEmpelado);

			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}

		if (empleadoService.exitsTipDocumentoAndNumDocumento(empleadoDTO.getTipDocumento(),
				empleadoDTO.getNumDocumento())
				&& empleadoService
						.findByTipDocumentoAndNumDocumento(empleadoDTO.getTipDocumento(), empleadoDTO.getNumDocumento())
						.getCodEmpleado() != codEmpelado) {

			response.put("mensaje",
					"ERROR: No es posible actualizar  el empleado con el documento: "
							+ empleadoDTO.getTipDocumento().getNomTipoDocumento() + ": " + empleadoDTO.getNumDocumento()
							+ " ,ya se encuentra registrado en la base de datos!");

			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
		}

		if (result.hasErrors()) {

			List<String> errors = result.getFieldErrors().stream()
					.map(err -> "El campo '" + err.getField() + "' " + err.getDefaultMessage())
					.collect(Collectors.toList());

			response.put("errors", errors);
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);

		}

		calendarNacimiento.setTime(empleadoDTO.getFechaNacimiento());

		int edad = calendarActual.get(Calendar.YEAR) - calendarNacimiento.get(Calendar.YEAR);

		if (calendarActual.get(Calendar.DAY_OF_YEAR) < calendarNacimiento.get(Calendar.DAY_OF_YEAR)) {
			edad--;
		}

		try {

			Empleado empleado = empleadoService.findByCodEmpleado(codEmpelado);

			empleado.setNombre(empleadoDTO.getNombre());
			empleado.setApellido(empleadoDTO.getApellido());
			empleado.setTipDocumento(empleadoDTO.getTipDocumento());
			empleado.setNumDocumento(empleadoDTO.getNumDocumento());
			empleado.setEdad(edad);
			empleado.setNumTelefono(empleadoDTO.getNumTelefono());
			empleado.setCorreo(empleadoDTO.getCorreo());
			empleado.setFechaNacimiento(empleadoDTO.getFechaNacimiento());
			empleado.setDireccion(empleadoDTO.getDireccion());
			empleado.setNomContactoEmergencia(empleadoDTO.getNomContactoEmergencia());
			empleado.setNumContactoEmergencia(empleadoDTO.getNumContactoEmergencia());
			empleado.setEps(empleadoDTO.getEps());
			empleado.setArl(empleadoDTO.getArl());
			empleado.setSexo(empleadoDTO.getSexo());
			empleado.setTipoSangre(empleadoDTO.getTipoSangre());
			empleado.setFechaIngreso(empleadoDTO.getFechaIngreso());
			empleado.setFechaSalida(empleadoDTO.getFechaSalida());

			empleadoService.save(empleado);

		} catch (DataAccessException e) {
			response.put("mensaje", "Error al actualizar el registro del empleado en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		response.put("mensaje", "El registro del empleado ha sido actualizado exitosamente!");

		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);

	}

	@Secured({"ROLE_ADMINISTRADOR"})
	@DeleteMapping("/eliminarEmpleado/{codEmpleado}")
	public ResponseEntity<?> deleteEmpleado(@PathVariable("codEmpleado") Long codEmpelado) {
		Map<String, Object> response = new HashMap<>();

		try {
			empleadoService.delete(codEmpelado);

		} catch (DataAccessException e) {
			response.put("mensaje", "Error al eliminar el registro del empleado en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		response.put("mensaje", "El empleado ha sido eliminado con exito!");

		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);

	}

}
