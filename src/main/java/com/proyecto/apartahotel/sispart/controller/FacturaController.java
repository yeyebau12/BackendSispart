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
import org.springframework.security.access.annotation.Secured;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.proyecto.apartahotel.sispart.dto.FacturaDTO;
import com.proyecto.apartahotel.sispart.dto.HabitacionDTO;
import com.proyecto.apartahotel.sispart.entity.Factura;
import com.proyecto.apartahotel.sispart.entity.Habitacion;
import com.proyecto.apartahotel.sispart.entity.ItemFactura;
import com.proyecto.apartahotel.sispart.entity.Producto;
import com.proyecto.apartahotel.sispart.service.interfa.IFacturaService;
import com.proyecto.apartahotel.sispart.service.interfa.IHuespedService;
import com.proyecto.apartahotel.sispart.service.interfa.IProductoService;

@RestController
@RequestMapping("/factura")
public class FacturaController {

	@Autowired
	private IFacturaService facturaService;

	@Autowired
	private IProductoService productoService;

	@Secured({ "ROLE_ADMINISTRADOR", "ROLE_RECEPCIONISTA" })
	@GetMapping("/verFactura/{codFactura}")
	public ResponseEntity<?> verFactura(@PathVariable("codFactura") Long codFactura) {

		Factura factura = null;
		Map<String, Object> response = new HashMap<>();

		try {

			factura = facturaService.findFacturaById(codFactura);

		} catch (DataAccessException e) {
			response.put("mensaje", "Error al realizar la consulta en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		if (!facturaService.existsFacturaById(codFactura)) {
			response.put("mensaje", "La factura no existe en la base de datos");
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}

		return new ResponseEntity<Factura>(factura, HttpStatus.OK);

	}

	@Secured({ "ROLE_ADMINISTRADOR", "ROLE_RECEPCIONISTA" })
	@PostMapping("/crearFactura")
	public ResponseEntity<?> createdFactura(@Valid @RequestBody FacturaDTO facturaDTO, BindingResult result) {

		Map<String, Object> response = new HashMap<>();

		List<ItemFactura> items = facturaDTO.getItemFactura();

		if (result.hasErrors()) {

			List<String> errors = result.getFieldErrors().stream()
					.map(err -> "El campo '" + err.getField() + "' " + err.getDefaultMessage())
					.collect(Collectors.toList());

			response.put("errors", errors);
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);

		}

		if (items != null && !items.isEmpty()) {
			for (ItemFactura item : items) {

				Long codigoProducto = item.getProducto().getCodProducto();
				Producto producto = productoService.findByCodProducto(codigoProducto);

				if (producto != null) {
					
					 if (item.getCantidad() > producto.getCantidad()) {
						 
				            response.put("error", "La cantidad de productos: "+item.getProducto().getNombreProducto()+" de la marca: "+item.getProducto().getMarca()+" es insuficiente en el inventario.");
				            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
				        }

					Integer totalM = producto.getCantidad() - item.getCantidad();
					producto.setCantidad(totalM);
					productoService.save(producto);
				} else {

					response.put("mensaje", "Producto no encontrado con el código: " + codigoProducto);
					return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);

				}

			}
		} else {

			response.put("mensaje", "La lista de items de la factura está vacía ");
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);

		}

		try {

			Factura factura = new Factura(facturaDTO.getDescripcion(), facturaDTO.getCheckin(),
					facturaDTO.getItemFactura(), facturaDTO.getEstado());

			facturaService.saveFactura(factura);

		} catch (DataAccessException e) {

			response.put("mensaje", "Error al insertar el registro de la factura en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		response.put("mensaje", "la factura ha sido creada con exito!");
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);

	}

	@Secured({ "ROLE_ADMINISTRADOR", "ROLE_RECEPCIONISTA" })
	@DeleteMapping("/eliminarFactura/{codFactura}")
	public ResponseEntity<?> deleteFactura(@PathVariable("codFactura") Long codFactura) {
		Map<String, Object> response = new HashMap<>();

		try {
			facturaService.deleteFacturaById(codFactura);

		} catch (DataAccessException e) {
			response.put("mensaje", "Error al eliminar el registro la factura en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		response.put("mensaje", "la factura ha sido eliminada con exito!");

		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);

	}

}
