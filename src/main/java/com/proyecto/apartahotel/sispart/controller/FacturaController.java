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
	public ResponseEntity<?> createdFactura(@RequestBody FacturaDTO facturaDTO) {

		Map<String, Object> response = new HashMap<>();

		try {

			Factura factura = new Factura(facturaDTO.getDescripcion(), facturaDTO.getCheckin(),
					facturaDTO.getItemFactura(), facturaDTO.getEstado());

			facturaService.saveFactura(factura);

			int tamañoLista = facturaDTO.getItemFactura().size();
			
			if (tamañoLista > 0) {
				ItemFactura ultimoItem = facturaDTO.getItemFactura().get(tamañoLista - 1);
				Long codigoProductoUltimoItem = ultimoItem.getProducto().getCodProducto();
				Producto producto = productoService.findByCodProducto(codigoProductoUltimoItem);
				Integer totalM = producto.getCantidad() - facturaDTO.getItemFactura().get(tamañoLista -1).getCantidad();
				producto.setCantidad(totalM);
				productoService.save(producto);
			}

			
			
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
