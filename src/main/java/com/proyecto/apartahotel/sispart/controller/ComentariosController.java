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

import com.proyecto.apartahotel.sispart.dto.ComentariosDTO;
import com.proyecto.apartahotel.sispart.dto.EmpleadoDTO;
import com.proyecto.apartahotel.sispart.entity.Comentarios;
import com.proyecto.apartahotel.sispart.service.interfa.IComentarioService;

@RestController
@RequestMapping("/Comentarios")
public class ComentariosController {

	@Autowired
	private IComentarioService comentarioService;

	@GetMapping("/listarComentarios")
	public ResponseEntity<?> findAll() {
		List<Comentarios> findAll = null;
		Map<String, Object> response = new HashMap<>();

		try {

			findAll = comentarioService.findAll();

		} catch (DataAccessException e) {
			response.put("mensaje", "Error al listar los registros de la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}

		if (findAll.isEmpty()) {
			response.put("mensaje", "No existen registros en la base de datos");
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}

		return new ResponseEntity<List<Comentarios>>(findAll, HttpStatus.OK);
	}

	@PostMapping("/crearComentario")
	public ResponseEntity<?> createComentario(@Valid @RequestBody ComentariosDTO comentarioDTO, BindingResult result) {

		Map<String, Object> response = new HashMap<>();

		if (result.hasErrors()) {

			List<String> errors = result.getFieldErrors().stream()
					.map(err -> "El campo '" + err.getField() + "' " + err.getDefaultMessage())
					.collect(Collectors.toList());

			response.put("errors", errors);
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);

		}

		try {

			Comentarios comentario = new Comentarios(comentarioDTO.getNombre(), comentarioDTO.getEmail(),
					comentarioDTO.getNumTelefono(), comentarioDTO.getComentario());

			comentarioService.save(comentario);

		} catch (DataAccessException e) {
			response.put("mensaje", "Error al insertar el registro del empleado en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		response.put("mensaje", "El comentario ha sido creado con exito!");

		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
	}
	
	@DeleteMapping("/eliminarComentario/{codComentario}")
	public ResponseEntity<?> deleteEmpleado(@PathVariable("codComentario") Long codComentario) {
		Map<String, Object> response = new HashMap<>();

		try {
			comentarioService.delete(codComentario);

		} catch (DataAccessException e) {
			response.put("mensaje", "Error al eliminar el registro del comentario en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		response.put("mensaje", "El comentario ha sido eliminado con exito!");

		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);

	}
}
