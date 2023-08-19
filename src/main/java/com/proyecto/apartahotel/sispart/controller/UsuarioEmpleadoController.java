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
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.proyecto.apartahotel.sispart.dto.UsuarioEmpleadoDTO;
import com.proyecto.apartahotel.sispart.entity.Empleado;
import com.proyecto.apartahotel.sispart.entity.UsuarioEmpleado;
import com.proyecto.apartahotel.sispart.service.interfa.IEmailService;
import com.proyecto.apartahotel.sispart.service.interfa.IEmpleadoService;
import com.proyecto.apartahotel.sispart.service.interfa.IUsuarioEmpleadoService;

@RestController
@RequestMapping("/loginEmpleado")
public class UsuarioEmpleadoController {

	@Autowired
	private IUsuarioEmpleadoService usuarioEmpleadoService;

	@Autowired
	private IEmpleadoService empleadoService;

	@Autowired
	private IEmailService emailService;

	@Autowired
	private BCryptPasswordEncoder passwordEncoder;

	@GetMapping("/listarLoginEmpleados")
	public ResponseEntity<?> findAll() {

		List<UsuarioEmpleado> findAll = null;
		Map<String, Object> response = new HashMap<>();

		try {
			findAll = usuarioEmpleadoService.findAll();
		} catch (DataAccessException e) {
			response.put("mensaje", "Error al listar los registros de la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}

		if (findAll.isEmpty()) {
			response.put("mensaje", "No existen registros en la base de datos");
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}

		return new ResponseEntity<List<UsuarioEmpleado>>(findAll, HttpStatus.OK);
	}

	@PostMapping("/registroLoginEmpleado")
	public ResponseEntity<?> createLogin(@Valid @RequestBody UsuarioEmpleadoDTO usuarioEmpleadoDto,
			BindingResult result) {

		String body = "";
		Empleado empleado = empleadoService.findByTipDocumentoAndNumDocumento(usuarioEmpleadoDto.getTipDocumento(),
				usuarioEmpleadoDto.getNumDocumento());

		Map<String, Object> response = new HashMap<>();

		if (!empleadoService.exitsTipDocumentoAndNumDocumento(usuarioEmpleadoDto.getTipDocumento(),
				usuarioEmpleadoDto.getNumDocumento())) {

			response.put("mensaje", "El empleado no se encuentra registrado en la base de datos de empleados");

			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}

		if (result.hasErrors()) {

			List<String> errors = result.getFieldErrors().stream()
					.map(err -> "El campo '" + err.getField() + "' " + err.getDefaultMessage())
					.collect(Collectors.toList());

			response.put("errors", errors);
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);

		} else if (!usuarioEmpleadoDto.getContrasena().equals(usuarioEmpleadoDto.getConfirContrasena())) {
			response.put("errors", "Las contraseñas no coinciden");

			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
		}
		try {

			String contraseña = passwordEncoder.encode(usuarioEmpleadoDto.getContrasena());
			String confirContraseña = passwordEncoder.encode(usuarioEmpleadoDto.getConfirContrasena());

			UsuarioEmpleado usuarioEmpleado = new UsuarioEmpleado(usuarioEmpleadoDto.getTipDocumento(),
					usuarioEmpleadoDto.getNumDocumento(), usuarioEmpleadoDto.getUserName(), contraseña,
					confirContraseña, usuarioEmpleadoDto.getRol());

			usuarioEmpleadoService.save(usuarioEmpleado);

		} catch (DataAccessException e) {
			response.put("mensaje", "Error al insertar el registro en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		try {

			body = "\r\nUSERNAME: " + usuarioEmpleadoDto.getUserName() + "\nCONTRASEÑA:"
					+ usuarioEmpleadoDto.getContrasena() + "\r\n";

			emailService.sendEmail(empleado.getCorreo(), body);

		} catch (Exception e) {

			response.put("mensaje", "Ha sucedido un error con el envio del correo para el empleado.");
			response.put("error", e.getMessage());
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		response.put("mensaje", "Los accesos han sido creados con exito!");
		response.put("mensaje",
				"El e-mail ha sido enviado " + "a " + empleado.getCorreo() + " con las credenciales de acceso!");

		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
	}
	
	

}
