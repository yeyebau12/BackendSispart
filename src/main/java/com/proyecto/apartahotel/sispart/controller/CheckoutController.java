package com.proyecto.apartahotel.sispart.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.proyecto.apartahotel.sispart.entity.EstadoHabitacion;
import com.proyecto.apartahotel.sispart.entity.Habitacion;
import com.proyecto.apartahotel.sispart.service.interfa.IHabitacionesService;
import com.proyecto.apartahotel.sispart.service.interfa.IHuespedService;

@RestController
@RequestMapping("/checkout")
public class CheckoutController {

	@Autowired
	private IHabitacionesService habitacionService;

	@Secured({"ROLE_ADMINISTRADOR", "ROLE_RECEPCIONISTA"})
	@PutMapping("/checkout/{codHabitacion}")
	public ResponseEntity<?> checkOut(@PathVariable("codHabitacion") Long codHabitacion) {

		Habitacion habitacion = habitacionService.findByCodHabitacion(codHabitacion);
		Map<String, Object> response = new HashMap<>();

		if (!habitacionService.existsById(codHabitacion)) {
			response.put("mensaje", "La habitacion no existe en la base de datos");
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);

		}
		if (!(habitacion.getEstadoHabitacion().getCodEstadoHabitacion() == 1)) {
			response.put("mensaje", "La habitacion no esta ocupada");
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
		}

		try {

			EstadoHabitacion estadoHabitación = new EstadoHabitacion();
			
			estadoHabitación.setCodEstadoHabitacion((long)3);
			estadoHabitación.setNombre("Limpieza");
			
			habitacion.setEstadoHabitacion(estadoHabitación);
			habitacionService.save(habitacion);

		} catch (DataAccessException e) {
			response.put("mensaje", "Error al realizar la consulta en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		response.put("mensaje", "El checkout ha sido realizado con exito!");

		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);

	}

}
