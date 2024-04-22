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
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.proyecto.apartahotel.sispart.dto.TipoReciboDTO;
import com.proyecto.apartahotel.sispart.entity.TipoRecibo;
import com.proyecto.apartahotel.sispart.service.interfa.ITipoReciboService;

@RestController
@RequestMapping("/tipRecibo")
public class TipoReciboController {

	@Autowired
	private ITipoReciboService tipoReciboService;

	@Secured({"ROLE_ADMINISTRADOR"})
	@GetMapping("/listarTipoRecibos")
	public ResponseEntity<?> findAll() {

		List<TipoRecibo> findAll = null;
		Map<String, Object> response = new HashMap<>();

		try {
			findAll = tipoReciboService.findAll();
		} catch (DataAccessException e) {
			response.put("mensaje", "Error al listar los registros de la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
		}

		if (findAll.isEmpty()) {
			response.put("mensaje", "No existen registros en la base de datos");
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NO_CONTENT);
		}

		return new ResponseEntity<List<TipoRecibo>>(findAll, HttpStatus.OK);
	}
	

	@Secured({ "ROLE_ADMINISTRADOR" })
	@GetMapping("/listarTipoRecibos/page/{page}")
	public ResponseEntity<?> findAll(@PathVariable("page") Integer page) {

		Pageable pageable = PageRequest.of(page, 5);
		Page<TipoRecibo> findAll = null;
		Map<String, Object> response = new HashMap<>();

		try {

			findAll = tipoReciboService.findAll(pageable);

		} catch (DataAccessException e) {
			response.put("mensaje", "Error al listar los registros de la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}

		if (findAll.isEmpty()) {
			response.put("mensaje", "No existen registros en la base de datos");
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NO_CONTENT);
		}

		return new ResponseEntity<Page<TipoRecibo>>(findAll, HttpStatus.OK);
	}

	@Secured({ "ROLE_ADMINISTRADOR" })
	@PostMapping("/crearTipoRecibo")
	public ResponseEntity<?> createdTipoRecibo(@Valid @RequestBody TipoReciboDTO tipoReciboDTO,
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

			TipoRecibo tipoRecibo = new TipoRecibo(tipoReciboDTO.getEmpresaPub());

			tipoReciboService.save(tipoRecibo);

		} catch (DataAccessException e) {
			response.put("mensaje", "Error al insertar el registro de la empresa publica en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		response.put("mensaje", "El nombre de la empresa publica ha sido creado con exito!");

		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);

	}

	@Secured({ "ROLE_ADMINISTRADOR" })
	@PutMapping("/actualizarTipoRecibo/{codTipoRecibo}")
	public ResponseEntity<?> updateTipoRecibo(@Valid @RequestBody TipoReciboDTO tipoReciboDTO,
			@PathVariable("codTipoRecibo") Long codTipoRecibo, BindingResult result) {

		Map<String, Object> response = new HashMap<>();

		if (!tipoReciboService.existsById(codTipoRecibo)) {

			response.put("Error",
					"El tipo de recibo no existe con el codigo  número: " + codTipoRecibo);

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

			TipoRecibo tipoReecibo = tipoReciboService.findByCodTipoRecibo(codTipoRecibo);

			tipoReciboDTO.setEmpresaPub(tipoReciboDTO.getEmpresaPub());

			tipoReciboService.save(tipoReecibo);

		} catch (DataAccessException e) {
			response.put("error", "Error al actualizar el registro del tipo de recibo en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		response.put("mensaje", "El registro del tipo de recibo ha sido actualizado exitosamente!");

		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);

	}
	
	@Secured({"ROLE_ADMINISTRADOR"})
	@DeleteMapping("/eliminarTipoRecibo/{codTipoRecibo}")
	public ResponseEntity<?> deleteEmpleado(@PathVariable("codTipoRecibo") Long codTipoRecibo) {
		Map<String, Object> response = new HashMap<>();

		try {
			tipoReciboService.delete(codTipoRecibo);

		} catch (DataAccessException e) {
			response.put("error", "Error al eliminar el registro del tipo de recibo en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		response.put("mensaje", "El tipo de recibo ha sido eliminado con exito!");

		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);

	}

}
