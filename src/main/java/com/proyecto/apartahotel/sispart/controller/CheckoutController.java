package com.proyecto.apartahotel.sispart.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.proyecto.apartahotel.sispart.entity.Habitacion;
import com.proyecto.apartahotel.sispart.service.interfa.IHabitacionesService;
import com.proyecto.apartahotel.sispart.service.interfa.IHuespedService;

@RestController
@RequestMapping("/checkout")
public class CheckoutController {
	
	
	@Autowired
	private IHuespedService huespedService;

	@Autowired
	private IHabitacionesService habitacionService;
	
	
	@GetMapping("/checkout/{numHabitacion}")
	public ResponseEntity<?> checkOut(@PathVariable("numHabitacion") Integer numHabitacion) {
		
		
		Habitacion habitacion = habitacionService.findByNumHabitacion(numHabitacion);
		Map<String, Object> response = new HashMap<>();
		
		if (!habitacionService.exitsNumHabitacion(numHabitacion)) {
			response.put("mensaje", "La habitacion no existe en la base de datos");
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
			
		}
		
		if(!habitacion.getEstadoHabitacion().getNombre().equalsIgnoreCase("Ocupada")) {
			response.put("mensaje", "La habitacion no esta ocupada");
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
		}

		try {

			//habitacion.setEstadoHabitacion("Limpieza");
			//habitacionService.save(habitacion);

		} catch (DataAccessException e) {
			response.put("mensaje", "Error al realizar la consulta en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		response.put("mensaje", "El checkout ha sido realizado con exito!");

		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
		
		
		
	}

}
