package com.proyecto.apartahotel.sispart.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.proyecto.apartahotel.sispart.dto.ProductoDTO;
import com.proyecto.apartahotel.sispart.entity.Huesped;
import com.proyecto.apartahotel.sispart.entity.Producto;
import com.proyecto.apartahotel.sispart.service.interfa.IProductoService;

@RestController
@RequestMapping("/producto")
public class ProductoController {

	@Autowired
	private IProductoService productoService;

	@Secured({"ROLE_ADMINISTRADOR","ROLE_RECEPCIONISTA"})
	@GetMapping("/listarProductos")
	public ResponseEntity<?> findAll() {

		List<Producto> findAll = null;
		Map<String, Object> response = new HashMap<>();

		try {

			findAll = productoService.findAll();

		} catch (DataAccessException e) {
			response.put("mensaje", "Error al listar los registros de la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}

		if (findAll.isEmpty()) {
			response.put("mensaje", "No existen registros en la base de datos");
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}

		return new ResponseEntity<List<Producto>>(findAll, HttpStatus.OK);
	}
	
	@Secured({"ROLE_ADMINISTRADOR","ROLE_RECEPCIONISTA"})
	@GetMapping("/listarProductos/page/{page}")
	public ResponseEntity<?> findAll(@PathVariable("page") Integer page) {

		Pageable pageable = PageRequest.of(page, 5);
		Page<Producto> findAll = null;
		Map<String, Object> response = new HashMap<>();

		try {

			findAll = productoService.findAll(pageable);

		} catch (DataAccessException e) {
			response.put("mensaje", "Error al listar los registros de la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}

		if (findAll.isEmpty()) {
			response.put("mensaje", "No existen registros en la base de datos");
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}

		return new ResponseEntity<Page<Producto>>(findAll, HttpStatus.OK);
	}


	@Secured({"ROLE_ADMINISTRADOR","ROLE_RECEPCIONISTA"})
	@GetMapping("/filtrarProducto/{nombreProducto}")
	public ResponseEntity<?> findAllProducto(@PathVariable("nombreProducto") String nombreProducto) {

		List<Producto> findAllProducto = null;
		Map<String, Object> response = new HashMap<>();

		try {

			findAllProducto = productoService.findProducto(nombreProducto);

		} catch (DataAccessException e) {
			response.put("mensaje", "Error al listar los registros de la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}

		if (findAllProducto.isEmpty()) {
			response.put("mensaje", "No existen registros en la base de datos");
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NO_CONTENT);
		}

		return new ResponseEntity<List<Producto>>(findAllProducto, HttpStatus.OK);
	}

	@Secured({"ROLE_ADMINISTRADOR","ROLE_RECEPCIONISTA"})
	@GetMapping("/verProducto/{codProducto}")
	public ResponseEntity<?> detailEmpleado(@PathVariable("codProducto") Long codProducto) {

		Producto producto = null;
		Map<String, Object> response = new HashMap<>();

		try {

			producto = productoService.findByCodProducto(codProducto);

		} catch (DataAccessException e) {
			response.put("mensaje", "Error al realizar la consulta en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		if (!productoService.existsById(codProducto)) {
			response.put("mensaje", "El producto no existe en la base de datos");
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}

		return new ResponseEntity<Producto>(producto, HttpStatus.OK);

	}

	@Secured({"ROLE_ADMINISTRADOR","ROLE_RECEPCIONISTA"})
	@PostMapping("/crearProducto")
	public ResponseEntity<?> createdEmpleado(@Valid @RequestBody ProductoDTO productoDTO, BindingResult result) {

		Map<String, Object> response = new HashMap<>();

		if (result.hasErrors()) {

			List<String> errors = result.getFieldErrors().stream()
					.map(err -> "El campo '" + err.getField() + "' " + err.getDefaultMessage())
					.collect(Collectors.toList());

			response.put("errors", errors);
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);

		}

		try {

			Producto producto = new Producto(productoDTO.getNombreProducto(), productoDTO.getMarca(),
					productoDTO.getCantidad(), productoDTO.getPrecio());

			productoService.save(producto);

		} catch (DataAccessException e) {
			response.put("mensaje", "Error al insertar el registro del producto en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		response.put("mensaje", "El producto ha sido creado con exito!");

		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);

	}

	@Secured({"ROLE_ADMINISTRADOR","ROLE_RECEPCIONISTA"})
	@PutMapping("/actualizarProducto/{codProducto}")
	public ResponseEntity<?> updateEmpleado(@Valid @RequestBody ProductoDTO productoDTO,
			@PathVariable("codProducto") Long codProducto, BindingResult result) {

		Map<String, Object> response = new HashMap<>();

		if (!productoService.existsById(codProducto)) {

			response.put("mensaje", " ERROR: El empleado no existe con el codigo de empleado numero: " + codProducto);

			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}

		if (result.hasErrors()) {

			List<String> errors = result.getFieldErrors().stream()
					.map(err -> "El campo '" + err.getField() + "' " + err.getDefaultMessage())
					.collect(Collectors.toList());

			response.put("errors", errors);
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);

		}

		try {

			Producto producto = productoService.findByCodProducto(codProducto);

			producto.setNombreProducto(productoDTO.getNombreProducto());
			producto.setMarca(productoDTO.getMarca());
			producto.setCantidad(productoDTO.getCantidad());
			producto.setPrecio(productoDTO.getPrecio());

			productoService.save(producto);

		} catch (DataAccessException e) {
			response.put("mensaje", "Error al actualizar el registro del producto en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		response.put("mensaje", "El registro del producto ha sido actualizado exitosamente!");

		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);

	}

	@Secured({"ROLE_ADMINISTRADOR"})
	@DeleteMapping("/eliminarProducto/{codProducto}")
	public ResponseEntity<?> deleteEmpleado(@PathVariable("codProducto") Long codProducto) {
		Map<String, Object> response = new HashMap<>();

		try {
			productoService.delete(codProducto);

		} catch (DataAccessException e) {
			response.put("mensaje", "Error al eliminar el registro del producto en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		response.put("mensaje", "El producto ha sido eliminado con exito!");

		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);

	}
}
