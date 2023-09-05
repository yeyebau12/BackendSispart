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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.proyecto.apartahotel.sispart.dto.CheckoutDTO;

import com.proyecto.apartahotel.sispart.entity.Checkout;
import com.proyecto.apartahotel.sispart.entity.Factura;
import com.proyecto.apartahotel.sispart.entity.Habitacion;

import com.proyecto.apartahotel.sispart.service.interfa.ICheckoutService;
import com.proyecto.apartahotel.sispart.service.interfa.IFacturaService;
import com.proyecto.apartahotel.sispart.service.interfa.IHabitacionesService;

@RestController
@RequestMapping("/checkout")
public class CheckoutController {

	@Autowired
	private ICheckoutService checkoutService;

	@Autowired
	private IHabitacionesService habitacionService;

	@Autowired
	private IFacturaService facturaService;

	@GetMapping("/listarCheckout")
	public ResponseEntity<?> findAll() {

		List<Checkout> findAll = null;
		Map<String, Object> response = new HashMap<>();

		try {

			findAll = checkoutService.findAll();

		} catch (DataAccessException e) {
			response.put("mensaje", "Error al listar los registros de la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}

		if (findAll.isEmpty()) {
			response.put("mensaje", "No existen registros en la base de datos");
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}

		return new ResponseEntity<List<Checkout>>(findAll, HttpStatus.OK);
	}

	@GetMapping("/verCheckout/{codCheckout}")
	public ResponseEntity<?> detailCheckin(@PathVariable("codCheckout") Long codCheckout) {

		Checkout checkout = null;
		Map<String, Object> response = new HashMap<>();

		try {

			checkout = checkoutService.findByCodCheckout(codCheckout);

		} catch (DataAccessException e) {
			response.put("mensaje", "Error al realizar la consulta en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		if (!checkoutService.existsById(codCheckout)) {
			response.put("mensaje", "La habitacion no existe en la base de datos");
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}

		return new ResponseEntity<Checkout>(checkout, HttpStatus.OK);

	}

	@PostMapping("/registrarCheckout")
	public ResponseEntity<?> createdCheckout(@Valid @RequestBody CheckoutDTO checkoutDTO, BindingResult result) {

		Map<String, Object> response = new HashMap<>();
		Habitacion habitacion = checkoutDTO.getCheckin().getHabitacion();
		Factura factura = (Factura) checkoutDTO.getCheckin().getHuesped().getFacturas();

		if (result.hasErrors()) {

			List<String> errors = result.getFieldErrors().stream()
					.map(err -> "El campo '" + err.getField() + "' " + err.getDefaultMessage())
					.collect(Collectors.toList());

			response.put("errors", errors);
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);

		}

		if (habitacion.getEstadoHabitacion().equalsIgnoreCase("Ocupada")) {

			habitacion.setEstadoHabitacion("Limpieza");
			habitacionService.save(habitacion);
			response.put("mensaje", "La habitacion debe ser limpiada!");

		}

		if (factura.getEstado().equalsIgnoreCase("Abierta")) {
			factura.setEstado("Cerrada");
            facturaService.saveFactura(factura);
			response.put("mensaje", "Factura Cancelada!");

		}

		try {

			Checkout checkout = new Checkout(checkoutDTO.getCheckin());

			checkoutService.save(checkout);

		} catch (DataAccessException e) {
			response.put("mensaje", "Error al insertar el registro de la habitacion en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		response.put("mensaje", "Se ha registrado correctamente el CheckOut!");

		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);

	}

}
