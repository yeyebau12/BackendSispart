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

import com.proyecto.apartahotel.sispart.dto.ProductsIntDTO;
import com.proyecto.apartahotel.sispart.entity.ProductsInt;
import com.proyecto.apartahotel.sispart.service.interfa.IProductsIntService;

@RestController
@RequestMapping("/productsInt")
public class ProductsIntController {

	@Autowired
	private IProductsIntService productsIntService;
	
	@Secured({"ROLE_ADMINISTRADOR","ROLE_RECEPCIONISTA"})
	@GetMapping("/listarProductos")
	public ResponseEntity<?> findAll() {

		List<ProductsInt> findAll = null;
		Map<String, Object> response = new HashMap<>();

		try {

			findAll = productsIntService.findAll();

		} catch (DataAccessException e) {
			response.put("mensaje", "Error al listar los registros de la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
		}

		if (findAll.isEmpty()) {
			response.put("mensaje", "No existen registros en la base de datos");
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NO_CONTENT);
		}

		return new ResponseEntity<List<ProductsInt>>(findAll, HttpStatus.OK);
	}
	
	@Secured({"ROLE_ADMINISTRADOR","ROLE_RECEPCIONISTA"})
	@GetMapping("/listarProductos/page/{page}")
	public ResponseEntity<?> findAll(@PathVariable("page") Integer page) {

		Pageable pageable = PageRequest.of(page, 5);
		Page<ProductsInt> findAll = null;
		Map<String, Object> response = new HashMap<>();

		try {

			findAll = productsIntService.findAll(pageable);

		} catch (DataAccessException e) {
			response.put("mensaje", "Error al listar los registros de la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
		}

		if (findAll.isEmpty()) {
			response.put("mensaje", "No existen registros en la base de datos");
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NO_CONTENT);
		}

		return new ResponseEntity<Page<ProductsInt>>(findAll, HttpStatus.OK);
	}


	@Secured({"ROLE_ADMINISTRADOR","ROLE_RECEPCIONISTA"})
	@GetMapping("/filtrarProducto/{nombreProducto}")
	public ResponseEntity<?> findAllProducto(@PathVariable("nombreProducto") String nombreProducto) {

		List<ProductsInt> findAllProducto = null;
		Map<String, Object> response = new HashMap<>();

		try {

			findAllProducto = productsIntService.findProducto(nombreProducto);

		} catch (DataAccessException e) {
			response.put("mensaje", "Error al listar los registros de la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
		}

		if (findAllProducto.isEmpty()) {
			response.put("mensaje", "No existen registros en la base de datos");
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NO_CONTENT);
		}

		return new ResponseEntity<List<ProductsInt>>(findAllProducto, HttpStatus.OK);
	}

	@Secured({"ROLE_ADMINISTRADOR","ROLE_RECEPCIONISTA"})
	@GetMapping("/verProducto/{codProducto}")
	public ResponseEntity<?> detailEmpleado(@PathVariable("codProducto") Long codProductInt) {

		ProductsInt producto = null;
		Map<String, Object> response = new HashMap<>();

		try {

			producto = productsIntService.findByCodProducto(codProductInt);

		} catch (DataAccessException e) {
			response.put("mensaje", "Error al realizar la consulta en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		if (!productsIntService.existsById(codProductInt)) {
			response.put("mensaje", "El producto no existe en la base de datos");
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}

		return new ResponseEntity<ProductsInt>(producto, HttpStatus.OK);

	}

	@Secured({"ROLE_ADMINISTRADOR","ROLE_RECEPCIONISTA"})
	@PostMapping("/crearProducto")
	public ResponseEntity<?> createdEmpleado(@Valid @RequestBody ProductsIntDTO productsIntDTO, BindingResult result) {

		Map<String, Object> response = new HashMap<>();

		if (result.hasErrors()) {

			List<String> errors = result.getFieldErrors().stream()
					.map(err -> "El campo '" + err.getField() + "' " + err.getDefaultMessage())
					.collect(Collectors.toList());

			response.put("errors", errors);
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);

		}

		try {

			ProductsInt productsInt = new ProductsInt(productsIntDTO.getNombreProducto(), productsIntDTO.getMarca(),
					productsIntDTO.getCantidad(), productsIntDTO.getPrecio());

			productsIntService.save(productsInt);

		} catch (DataAccessException e) {
			response.put("mensaje", "Error al insertar el registro del producto en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		response.put("mensaje", "El producto ha sido creado con exito!");

		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);

	}

	@Secured({"ROLE_ADMINISTRADOR","ROLE_RECEPCIONISTA"})
	@PutMapping("/actualizarProducto/{codProductInt}")
	public ResponseEntity<?> updateEmpleado(@Valid @RequestBody ProductsIntDTO productsIntDTO,
			@PathVariable("codProductInt") Long codProductInt, BindingResult result) {

		Map<String, Object> response = new HashMap<>();

		if (!productsIntService.existsById(codProductInt)) {

			response.put("Error", "El producto no existe con el codigo de producto numero: " + codProductInt);

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

			ProductsInt productsInt = productsIntService.findByCodProducto(codProductInt);

			productsInt.setNombreProducto(productsIntDTO.getNombreProducto());
			productsInt.setMarca(productsIntDTO.getMarca());
			productsInt.setCantidad(productsIntDTO.getCantidad());
			productsInt.setPrecio(productsIntDTO.getPrecio());

			productsIntService.save(productsInt);

		} catch (DataAccessException e) {
			response.put("Error", "Error al actualizar el registro del producto en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		response.put("mensaje", "El registro del producto ha sido actualizado exitosamente!");

		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);

	}

	@Secured({"ROLE_ADMINISTRADOR"})
	@DeleteMapping("/eliminarProducto/{codProductInt}")
	public ResponseEntity<?> deleteEmpleado(@PathVariable("codProductInt") Long codProductInt) {
		Map<String, Object> response = new HashMap<>();

		try {
			productsIntService.delete(codProductInt);

		} catch (DataAccessException e) {
			response.put("Error", "Error al eliminar el registro del producto en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		response.put("mensaje", "El producto ha sido eliminado con exito!");

		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);

	}
}
