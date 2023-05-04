package com.proyecto.apartahotel.sispart.tipDocumento.controlle;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.proyecto.apartahotel.sispart.tipDocumento.entity.TipDocumento;
import com.proyecto.apartahotel.sispart.tipDocumento.service.ITipDocumentoService;

@RestController
@RequestMapping("/tipoDocumento")
public class TipDocumentoController {

	@Autowired
	private ITipDocumentoService tipDocumentoService;

	@GetMapping("/listarTipoDocumentos")
	public ResponseEntity<?> findAll() {

		List<TipDocumento> findAll = null;
		Map<String, Object> response = new HashMap<>();

		try {
			findAll = tipDocumentoService.findAll();
		} catch (DataAccessException e) {
			response.put("mensaje", "Error al listar los registros de la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}

		if (findAll.isEmpty()) {
			response.put("mensaje", "No existen registros en la base de datos");
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}

		return new ResponseEntity<List<TipDocumento>>(findAll, HttpStatus.OK);
	}

}
