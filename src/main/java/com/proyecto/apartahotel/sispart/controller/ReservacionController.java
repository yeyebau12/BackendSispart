package com.proyecto.apartahotel.sispart.controller;


import java.util.HashMap;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.proyecto.apartahotel.sispart.dto.ReservacionDTO;
import com.proyecto.apartahotel.sispart.entity.Reservacion;
import com.proyecto.apartahotel.sispart.service.interfa.IReservacionService;

@RestController
@RequestMapping("/reservaciones")
public class ReservacionController {

	@Autowired
	private IReservacionService reservacionService;

	@GetMapping("/verReservacion/{codReservacion}")
	public ResponseEntity<?> detailReservacion(@PathVariable("codReservacion") Long codReservacion) {

		Reservacion reservacion = null;
		Map<String, Object> response = new HashMap<>();

		try {

			reservacion = reservacionService.findById(codReservacion);

		} catch (DataAccessException e) {
			response.put("mensaje", "Error al realizar la consulta en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		if (!reservacionService.existsById(codReservacion)) {
			response.put("mensaje", "La reservacion no existe");
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}

		return new ResponseEntity<Reservacion>(reservacion, HttpStatus.OK);

	}

	@PostMapping("/crearReservacion")
	public ResponseEntity<?> createdReservacion(@RequestBody ReservacionDTO reservacionDTO) {

		Map<String, Object> response = new HashMap<>();

		try {

			Reservacion reservacion = new Reservacion(reservacionDTO.getFechaEntrada(), reservacionDTO.getFechaSalida(),
					reservacionDTO.getNumAcompañantes(), reservacionDTO.getHabitacion(), reservacionDTO.getHuesped());

			reservacionService.save(reservacion);

		} catch (DataAccessException e) {
			response.put("mensaje", "Error al insertar el registro de la reservacion en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		response.put("mensaje", "la reservacion ha sido creada con exito!");

		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);

	}

	/*
	 * @PutMapping("/actualizarReservacion/{codReservacion}") public
	 * ResponseEntity<?> updateReservacion(@RequestBody ReservacionDTO
	 * reservacionDTO,
	 * 
	 * @PathVariable("codReservacion") Long codReservacion) {
	 * 
	 * Map<String, Object> response = new HashMap<>();
	 * 
	 * if (!reservacionService.existsById(codReservacion)) {
	 * 
	 * response.put("mensaje", " ERROR: la reservacion no existe con el codigo : " +
	 * codReservacion);
	 * 
	 * return new ResponseEntity<Map<String, Object>>(response,
	 * HttpStatus.NOT_FOUND); }
	 * 
	 * if
	 * (reservacionService.existsByHuespedAndFechaEntrada(reservacionDTO.getHuesped(
	 * ), reservacionDTO.getFechaEntrada()) && reservacionService
	 * .findByHuespedAndFechaEntrada(reservacionDTO.getHuesped(),
	 * reservacionDTO.getFechaEntrada()) .getCodReservacion() != codReservacion) {
	 * 
	 * response.put("mensaje",
	 * "ERROR: No es posible actualizar la reservacion porqué  ya tiene una reservacion igual registrada"
	 * );
	 * 
	 * return new ResponseEntity<Map<String, Object>>(response,
	 * HttpStatus.BAD_REQUEST); }
	 * 
	 * try {
	 * 
	 * Reservacion reservacion = reservacionService.findById(codReservacion);
	 * reservacion.setFechaEntrada(reservacionDTO.getFechaEntrada());
	 * reservacion.setFechaSalida(reservacionDTO.getFechaSalida());
	 * reservacion.setNumAcompañantes(reservacionDTO.getNumAcompañantes());
	 * reservacion.setHabitacion(reservacionDTO.getHabitacion());
	 * reservacionService.save(reservacion);
	 * 
	 * } catch (DataAccessException e) { response.put("mensaje",
	 * "Error al actualizar el registro del huesped en la base de datos");
	 * response.put("error",
	 * e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
	 * return new ResponseEntity<Map<String, Object>>(response,
	 * HttpStatus.INTERNAL_SERVER_ERROR); }
	 * 
	 * response.put("mensaje",
	 * "El registro del huesped ha sido actualizado exitosamente!");
	 * 
	 * return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
	 * 
	 * }
	 */

	@DeleteMapping("/eliminarReservacion/{codReservacion}")
	public ResponseEntity<?> deleteReservacion(@PathVariable("codReservacion") Long codReservacion) {
		Map<String, Object> response = new HashMap<>();

		try {
			reservacionService.delete(codReservacion);

		} catch (DataAccessException e) {
			response.put("mensaje", "Error al eliminar la reservacion en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		response.put("mensaje", "La reservacion ha sido eliminada con exito!");

		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);

	}

}
