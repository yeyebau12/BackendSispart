package com.proyecto.apartahotel.sispart.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.proyecto.apartahotel.sispart.dto.EmpleadoDTO;
import com.proyecto.apartahotel.sispart.dto.HuespedDTO;
import com.proyecto.apartahotel.sispart.entity.Habitacion;
import com.proyecto.apartahotel.sispart.entity.Huesped;
import com.proyecto.apartahotel.sispart.entity.Reservacion;
import com.proyecto.apartahotel.sispart.entity.TipDocumento;
import com.proyecto.apartahotel.sispart.service.interfa.IHabitacionesService;
import com.proyecto.apartahotel.sispart.service.interfa.IHuespedService;
import com.proyecto.apartahotel.sispart.service.interfa.IReservacionService;

@RestController
@RequestMapping("/checkin")
public class ChekinController {

	@Autowired
	private IReservacionService reservacionService;

	@Autowired
	private IHuespedService huespedService;

	@Autowired
	private IHabitacionesService habitacionesService;

	@PostMapping("/crearHuesped/{tipDocumento}/{numDocumento}/{fechaEntrada}")
	public ResponseEntity<?> createdEmpleado(@Valid @RequestBody HuespedDTO huespedDTO,
			@PathVariable("tipDocumento") TipDocumento tipDocumento, @PathVariable("numDocumento") Long numDocumento,
			@PathVariable("fechaEntrada") @DateTimeFormat(pattern = "yyyy-MM-dd") @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT-5") Date fechaEntrada,
			BindingResult result) {

		Map<String, Object> response = new HashMap<>();
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		String fechEntrada = dateFormat.format(fechaEntrada);

		Reservacion reservacion = reservacionService.findByTipDocumentoAndNumDocumentoAndFechaEntrada(tipDocumento,
				numDocumento, fechaEntrada);

		if (!reservacionService.existsByTipDocumentoAndNumDocumentoAndFechaEntrada(tipDocumento, numDocumento,
				fechaEntrada)) {

			response.put("mensaje", "No existe una reserva para el usuario : " + tipDocumento.getNomTipoDocumento()
					+ ": " + numDocumento + " ,para el ingreso del día " + fechEntrada);

			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);

		} else if (huespedService.existsByTipoDocumentoAndNumDocumento(huespedDTO.getTipoDocumento(),
				huespedDTO.getNumDocumento())) {

			response.put("mensaje", "El huesped que desea registrar ya existe en la base de datos de huesped");

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

		List<String> menssage = new ArrayList<>();

		menssage.add("El huesped ha sido creado con exito!, el valor a pagar es: $" + reservacion.getTotalReservacion()
				+ " COP");
		menssage.add("La habitacion del Huesped es : " + reservacion.getHabitacion().getNumHabitacion()
				+ " ubicada en el piso: " + reservacion.getHabitacion().getPisoHabitacion());

		for (int i = 0; i < menssage.size(); i++) {

			response.put("mensaje", menssage);
		}

		response.put("mensaje", menssage);

		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
	}

}
