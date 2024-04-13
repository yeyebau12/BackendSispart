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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.proyecto.apartahotel.sispart.dto.RecibosPublicosDTO;

import com.proyecto.apartahotel.sispart.entity.RecibosPublicos;
import com.proyecto.apartahotel.sispart.service.interfa.IRecibosPublicosService;

@RestController
@RequestMapping("/recibosPublicos")
public class RecibosPublicosController {

	@Autowired
	private IRecibosPublicosService reciboPublicoService;

	@Secured({ "ROLE_ADMINISTRADOR" })
	@GetMapping("/listarRecibosPublicos")
	public ResponseEntity<?> findAll() {

		List<RecibosPublicos> findAll = null;
		Map<String, Object> response = new HashMap<>();

		try {

			findAll = reciboPublicoService.findAll();

		} catch (DataAccessException e) {
			response.put("mensaje", "Error al listar los registros de la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
		}

		if (findAll.isEmpty()) {
			response.put("mensaje", "No existen registros en la base de datos");
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}

		return new ResponseEntity<List<RecibosPublicos>>(findAll, HttpStatus.OK);
	}

	@Secured({ "ROLE_ADMINISTRADOR" })
	@GetMapping("/listarRecibosPublicos/page/{page}")
	public ResponseEntity<?> findAll(@PathVariable("page") Integer page) {

		Pageable pageable = PageRequest.of(page, 5);
		Page<RecibosPublicos> findAll = null;
		Map<String, Object> response = new HashMap<>();

		try {

			findAll = reciboPublicoService.findAll(pageable);

		} catch (DataAccessException e) {
			response.put("mensaje", "Error al listar los registros de la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
		}

		if (findAll.isEmpty()) {
			response.put("mensaje", "No existen registros en la base de datos");
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}

		return new ResponseEntity<Page<RecibosPublicos>>(findAll, HttpStatus.OK);
	}

	@Secured({ "ROLE_ADMINISTRADOR" })
	@GetMapping("/verRecibosPublicos/{codRecibo}")
	public ResponseEntity<?> detailHuesped(@PathVariable("codRecibo") Long codRecibo) {

		RecibosPublicos reciboPublico = null;
		Map<String, Object> response = new HashMap<>();

		try {

			reciboPublico = reciboPublicoService.findById(codRecibo);

		} catch (DataAccessException e) {
			response.put("mensaje", "Error al realizar la consulta en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		if (!reciboPublicoService.existsById(codRecibo)) {
			response.put("mensaje", "El huesped no existe en la base de datos");
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}

		return new ResponseEntity<RecibosPublicos>(reciboPublico, HttpStatus.OK);

	}

	@Secured({ "ROLE_ADMINISTRADOR" })
	@PostMapping("/crearReciboPublico")
	public ResponseEntity<?> createdReciboPublico(@Valid @RequestBody RecibosPublicosDTO recibosPublicosDTO,
			BindingResult result) {

		Map<String, Object> response = new HashMap<>();

		if (result.hasErrors()) {

			List<String> errors = result.getFieldErrors().stream()
					.map(err -> "El campo '" + err.getField() + "' " + err.getDefaultMessage())
					.collect(Collectors.toList());

			response.put("error", errors);
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);

		}

		try {

			RecibosPublicos reciboPublico = new RecibosPublicos(recibosPublicosDTO.getTipRecibo(),
					recibosPublicosDTO.getNumReferencia(), recibosPublicosDTO.getPagoOportuno(),
					recibosPublicosDTO.getSupension(), recibosPublicosDTO.getTotalPagar(),
					recibosPublicosDTO.getDocRecibo());

			reciboPublicoService.save(reciboPublico);

		} catch (DataAccessException e) {
			response.put("mensaje", "Error al insertar el registro del recibo en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		response.put("mensaje", "El recibo ha sido registrado con exito!");

		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);

	}

	@Secured({ "ROLE_ADMINISTRADOR" })
	@DeleteMapping("/eliminarRegistro/{codRegistro}")
	public ResponseEntity<?> deleteHuesped(@PathVariable("codRegistro") Long codRegistro) {
		Map<String, Object> response = new HashMap<>();

		try {
			reciboPublicoService.delete(codRegistro);

		} catch (DataAccessException e) {
			response.put("mensaje", "Error al eliminar el registro del recibo en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		response.put("mensaje", "El registro del  recibo ha sido eliminado con exito!");

		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);

	}

}
