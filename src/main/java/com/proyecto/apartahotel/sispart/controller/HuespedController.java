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

import com.proyecto.apartahotel.sispart.dto.HuespedDTO;
import com.proyecto.apartahotel.sispart.entity.Habitacion;
import com.proyecto.apartahotel.sispart.entity.Huesped;
import com.proyecto.apartahotel.sispart.entity.TipDocumento;
import com.proyecto.apartahotel.sispart.service.interfa.IHabitacionesService;
import com.proyecto.apartahotel.sispart.service.interfa.IHuespedService;

@RestController
@RequestMapping("/huespedes")
public class HuespedController {

	@Autowired
	private IHuespedService huespedService;

	@GetMapping("/listarHuespedes")
	public ResponseEntity<?> findAll() {

		List<Huesped> findAll = null;
		Map<String, Object> response = new HashMap<>();

		try {

			findAll = huespedService.findAll();

		} catch (DataAccessException e) {
			response.put("mensaje", "Error al listar los registros de la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
		}

		if (findAll.isEmpty()) {
			response.put("mensaje", "No existen registros en la base de datos");
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}

		return new ResponseEntity<List<Huesped>>(findAll, HttpStatus.OK);
	}

	@GetMapping("/listarHuespedes/page/{page}")
	public ResponseEntity<?> findAll(@PathVariable("page") Integer page) {

		Pageable pageable = PageRequest.of(page, 5);
		Page<Huesped> findAll = null;
		Map<String, Object> response = new HashMap<>();

		try {

			findAll = huespedService.findAll(pageable);

		} catch (DataAccessException e) {
			response.put("mensaje", "Error al listar los registros de la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
		}

		if (findAll.isEmpty()) {
			response.put("mensaje", "No existen registros en la base de datos");
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}

		return new ResponseEntity<Page<Huesped>>(findAll, HttpStatus.OK);
	}

	@GetMapping("/verhuesped/{codHuesped}")
	public ResponseEntity<?> detailHuesped(@PathVariable("codHuesped") Long codHuesped) {

		Huesped huesped = null;
		Map<String, Object> response = new HashMap<>();

		try {

			huesped = huespedService.getOne(codHuesped);

		} catch (DataAccessException e) {
			response.put("mensaje", "Error al realizar la consulta en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		if (!huespedService.existsById(codHuesped)) {
			response.put("mensaje", "El huesped no existe en la base de datos");
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}

		return new ResponseEntity<Huesped>(huesped, HttpStatus.OK);

	}

	@GetMapping("/verhuesped/{tipDocumento}/{numDocumento}")
	public ResponseEntity<?> viewHuesped(@PathVariable("tipDocumento") TipDocumento tipDocumento,
			@PathVariable("numDocumento") Long numDocumento) {

		Huesped huesped = null;
		Map<String, Object> response = new HashMap<>();

		try {

			huesped = huespedService.getByTipoDocumentoAndNumDocumento(tipDocumento, numDocumento);

		} catch (DataAccessException e) {
			response.put("mensaje", "Error al realizar la consulta en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		if (!huespedService.existsByTipoDocumentoAndNumDocumento(tipDocumento, numDocumento)) {
			response.put("mensaje", "El huesped no existe en la base de datos");
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}

		return new ResponseEntity<Huesped>(huesped, HttpStatus.OK);

	}

	@PostMapping("/crearHuesped")
	public ResponseEntity<?> createdEmpleado(@Valid @RequestBody HuespedDTO huespedDTO, BindingResult result) {

		Map<String, Object> response = new HashMap<>();

		if (huespedService.existsByTipoDocumentoAndNumDocumento(huespedDTO.getTipoDocumento(),
				huespedDTO.getNumDocumento())) {

			response.put("mensaje", "El huesped que desea registrar ya existe en la base de datos");

			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);

		} else if (result.hasErrors()) {

			List<String> errors = result.getFieldErrors().stream()
					.map(err -> "El campo '" + err.getField() + "' " + err.getDefaultMessage())
					.collect(Collectors.toList());

			response.put("error", errors);
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);

		}

		try {

			Huesped huesped = new Huesped(huespedDTO.getNombre(), huespedDTO.getApellido(), huespedDTO.getNumCelular(),
					huespedDTO.getCorreo(), huespedDTO.getTipoDocumento(), huespedDTO.getNumDocumento(),
					huespedDTO.getNacionalidad(), huespedDTO.getLugarOrigen(), huespedDTO.getNomContactoEmergencia(),
					huespedDTO.getNumContactoEmergencia());

			huespedService.save(huesped);

		} catch (DataAccessException e) {
			response.put("mensaje", "Error al insertar el registro del huesped en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		response.put("mensaje", "El huesped ha sido creado con exito!");

		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);

	}

	@PutMapping("/actualizarHuesped/{codHuesped}")
	public ResponseEntity<?> updateHuesped(@Valid @RequestBody HuespedDTO huespedDTO,
			@PathVariable("codHuesped") Long codHuesped, BindingResult result) {

		Map<String, Object> response = new HashMap<>();

		if (!huespedService.existsById(codHuesped)) {

			response.put("mensaje", " ERROR: El huesped no existe con el codigo : " + codHuesped);

			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}

		if (huespedService.existsByTipoDocumentoAndNumDocumento(huespedDTO.getTipoDocumento(),
				huespedDTO.getNumDocumento())
				&& huespedService
						.getByTipoDocumentoAndNumDocumento(huespedDTO.getTipoDocumento(), huespedDTO.getNumDocumento())
						.getCodHuesped() != codHuesped) {

			response.put("mensaje",
					"ERROR: No es posible actualizar a el huesped con el documento: "
							+ huespedDTO.getTipoDocumento().getNomTipoDocumento() + ": " + huespedDTO.getNumDocumento()
							+ " porque ya se encuentra registrado en la base de datos");

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

			Huesped huesped = huespedService.getOne(codHuesped);
			huesped.setNombre(huespedDTO.getNombre());
			huesped.setApellido(huespedDTO.getApellido());
			huesped.setNumCelular(huespedDTO.getNumCelular());
			huesped.setCorreo(huespedDTO.getCorreo());
			huesped.setTipoDocumento(huespedDTO.getTipoDocumento());
			huesped.setNumDocumento(huespedDTO.getNumDocumento());
			huesped.setNacionalidad(huespedDTO.getNacionalidad());
			huesped.setLugarOrigen(huespedDTO.getLugarOrigen());
			huesped.setNomContactoEmergencia(huespedDTO.getNomContactoEmergencia());
			huesped.setNumContactoEmergencia(huespedDTO.getNumContactoEmergencia());
			huesped.setEstadoHuesped(huespedDTO.isEstadoHuesped());

			huespedService.save(huesped);

		} catch (DataAccessException e) {
			response.put("mensaje", "Error al actualizar el registro del huesped en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		response.put("mensaje", "El registro del huesped ha sido actualizado exitosamente!");

		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);

	}

	@DeleteMapping("/eliminarhuesped/{codHuesped}")
	public ResponseEntity<?> deleteHuesped(@PathVariable("codHuesped") Long codHuesped) {
		Map<String, Object> response = new HashMap<>();

		try {
			huespedService.delete(codHuesped);

		} catch (DataAccessException e) {
			response.put("mensaje", "Error al eliminar el registro del huesped en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		response.put("mensaje", "El huesped ha sido eliminado con exito!");

		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);

	}

}
