package com.proyecto.apartahotel.sispart.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.security.PermitAll;
import javax.mail.Message;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.proyecto.apartahotel.sispart.dto.EstadoHabitacionDTO;
import com.proyecto.apartahotel.sispart.dto.HabitacionDTO;
import com.proyecto.apartahotel.sispart.dto.ReservacionDTO;
import com.proyecto.apartahotel.sispart.entity.EstadoHabitacion;
import com.proyecto.apartahotel.sispart.entity.Habitacion;
import com.proyecto.apartahotel.sispart.entity.Producto;
import com.proyecto.apartahotel.sispart.entity.Reservacion;
import com.proyecto.apartahotel.sispart.service.interfa.IEmailService;
import com.proyecto.apartahotel.sispart.service.interfa.IHabitacionesService;
import com.proyecto.apartahotel.sispart.service.interfa.IReservacionService;

@RestController
@RequestMapping("/reservaciones")
public class ReservacionController {

	@Autowired
	private IReservacionService reservacionService;

	@Autowired
	IHabitacionesService habitacionService;

	@Autowired
	private IEmailService emailService;

	@Secured({ "ROLE_ADMINISTRADOR", "ROLE_RECEPCIONISTA" })
	@GetMapping("/listarReservas")
	public ResponseEntity<?> findAll() {

		List<Reservacion> findAll = null;
		Map<String, Object> response = new HashMap<>();

		try {

			findAll = reservacionService.findAll();

		} catch (DataAccessException e) {
			response.put("mensaje", "Error al listar los registros de la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}

		if (findAll.isEmpty()) {
			response.put("mensaje", "No existen registros en la base de datos");
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}

		return new ResponseEntity<List<Reservacion>>(findAll, HttpStatus.OK);
	}

	@Secured({ "ROLE_ADMINISTRADOR", "ROLE_RECEPCIONISTA" })
	@GetMapping("/listarReservas/page/{page}")
	public ResponseEntity<?> findAll(@PathVariable("page") Integer page) {

		Pageable pageable = PageRequest.of(page, 5);
		Page<Reservacion> findAll = null;
		Map<String, Object> response = new HashMap<>();

		try {

			findAll = reservacionService.findAll(pageable);

		} catch (DataAccessException e) {
			response.put("mensaje", "Error al listar los registros de la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}

		if (findAll.isEmpty()) {
			response.put("mensaje", "No existen registros en la base de datos");
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}

		return new ResponseEntity<Page<Reservacion>>(findAll, HttpStatus.OK);
	}

	@Secured({ "ROLE_ADMINISTRADOR", "ROLE_RECEPCIONISTA" })
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
		String body = "";
		long millisecondsPerDay = 24 * 60 * 60 * 1000; // Milisegundos por día
		long diffMilliseconds = reservacionDTO.getFechaSalida().getTime() - reservacionDTO.getFechaEntrada().getTime();
		Integer totalDias = (int) (diffMilliseconds / millisecondsPerDay);

		if (reservacionDTO.getHabitacion().getEstadoHabitacion().getCodEstadoHabitacion() ==2) {

			response.put("mensaje", "La habitacion que desea asignar esta ocupada por otro huesped!");
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);

		}

		if (reservacionDTO.getHabitacion().getEstadoHabitacion().getCodEstadoHabitacion() == 3) {

			response.put("mensaje", "La habitacion que desea asignar ya se encuentra reservada!");
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);

		}

		if (reservacionDTO.getHabitacion().getEstadoHabitacion().getCodEstadoHabitacion() == 4) {

			response.put("mensaje", "La habitacion que desea asignar esta en proceso de limpieza!");
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);

		}

		if (reservacionDTO.getTotalHuespedes() > reservacionDTO.getHabitacion().getMaxPersonasDisponibles()) {

			response.put("mensaje", "La cantidad de acompañantes es demasiado grande para este tipo de habitacion!");
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);

		}

		try {

			Reservacion reservacion = new Reservacion(reservacionDTO.getFechaEntrada(), reservacionDTO.getFechaSalida(),
					totalDias, reservacionDTO.getAdultos(), reservacionDTO.getNinos(),
					reservacionDTO.getTipoDocumento(), reservacionDTO.getNumDocumento(), reservacionDTO.getNombre(),
					reservacionDTO.getApellido(), reservacionDTO.getEmail(), reservacionDTO.getHabitacion());

			Habitacion habitacion = habitacionService
					.findByCodHabitacion(reservacionDTO.getHabitacion().getCodHabitacion());
			//habitacion.setEstadoHabitacion(null);

			reservacionService.save(reservacion);
			habitacionService.save(habitacion);

		} catch (DataAccessException e) {
			response.put("mensaje", "Error al insertar el registro de la reservacion en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		try {

			Double precioFinal = reservacionDTO.getTotalPersona() * totalDias;
			SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
			String fechaSalida = dateFormat.format(reservacionDTO.getFechaSalida());
			String fechaEntrada = dateFormat.format(reservacionDTO.getFechaEntrada());

			body = " Señor@ " + reservacionDTO.getNombre() + " " + reservacionDTO.getApellido()
					+ " , a continuacion puede observar el detalle de su reserva : \r\n" + " Fecha de Ingreso: "
					+ fechaEntrada + "\r\n Fecha de salida: " + fechaSalida + "\r\n Adultos: "
					+ reservacionDTO.getAdultos() + "\r\n Niños: " + reservacionDTO.getNinos() + "\r\n Habitacion: "
					+ reservacionDTO.getHabitacion().getNumHabitacion() + "-"
					+ reservacionDTO.getHabitacion().getNombreHabitacion().getNombre() + "\r\nTotal de la Reserva: $"
					+ precioFinal + " COP";

			emailService.sendEmailReserva(reservacionDTO.getEmail(), body);
		} catch (Exception e) {
			response.put("mensaje", "Ha sucedido un error con el envio del correo para el huesped.");
			response.put("error", e.getMessage());
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		List<String> menssage = new ArrayList<>();

		menssage.add("La reservacion ha sido creada con exito!");
		menssage.add("Se ha enviado el detalle de su reserva a su correo electronico !");

		for (int i = 0; i < menssage.size(); i++) {

			response.put("mensaje", menssage);
		}

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
	@Secured({ "ROLE_ADMINISTRADOR", "ROLE_RECEPCIONISTA" })
	@DeleteMapping("/eliminarReservacion/{codReservacion}")
	public ResponseEntity<?> deleteReservacion(@PathVariable("codReservacion") Long codReservacion) {
		Map<String, Object> response = new HashMap<>();
		Reservacion reservacion = reservacionService.findById(codReservacion);
		Habitacion habitacion = habitacionService.findByNumHabitacion(reservacion.getHabitacion().getNumHabitacion());

		try {

			// habitacion.setEstadoHabitacion("Disponible");
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
