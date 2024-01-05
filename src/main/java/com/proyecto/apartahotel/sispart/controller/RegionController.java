package com.proyecto.apartahotel.sispart.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.proyecto.apartahotel.sispart.entity.Huesped;
import com.proyecto.apartahotel.sispart.entity.Region;
import com.proyecto.apartahotel.sispart.service.interfa.IRegionService;

@RestController
@RequestMapping("/region")
public class RegionController {

	@Autowired
	private IRegionService regionService;

	@Secured({"ROLE_ADMINISTRADOR","ROLE_RECEPCIONISTA"})
	@GetMapping("/listarRegiones")
	public ResponseEntity<?> findAllRegion() {

		List<Region> findAll = null;
		Map<String, Object> response = new HashMap<>();

		try {

			findAll = regionService.findAll();

		} catch (DataAccessException e) {
			response.put("mensaje", "Error al listar los registros de la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
		}

		if (findAll.isEmpty()) {
			response.put("mensaje", "No existen registros en la base de datos");
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}

		return new ResponseEntity<List<Region>>(findAll, HttpStatus.OK);
	}

	@Secured({"ROLE_ADMINISTRADOR","ROLE_RECEPCIONISTA"})
	@GetMapping("/regionByNacion/{codNacionalidad}")
	public ResponseEntity<?> findAllRegionBynacionalidad(@PathVariable("codNacionalidad") Long codNacion) {

		List<Region> findAll = null;
		Map<String, Object> response = new HashMap<>();

		try {

			findAll = regionService.findAllByNacion(codNacion);

		} catch (DataAccessException e) {
			response.put("mensaje", "Error al listar los registros de la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
		}

		if (findAll.isEmpty()) {
			response.put("mensaje", "No existen registros en la base de datos");
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}

		return new ResponseEntity<List<Region>>(findAll, HttpStatus.OK);
	}

}
