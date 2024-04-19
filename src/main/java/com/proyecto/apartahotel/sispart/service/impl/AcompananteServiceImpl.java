package com.proyecto.apartahotel.sispart.service.impl;

import java.util.Calendar;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.proyecto.apartahotel.sispart.entity.TipDocumento;
import com.proyecto.apartahotel.sispart.repository.IAcompanantesRepository;
import com.proyecto.apartahotel.sispart.service.interfa.IAcompananteService;

@Service
public class AcompananteServiceImpl implements IAcompananteService{
	
	@Autowired
	private IAcompanantesRepository acompananteRepository;

	@Override
	public boolean existsByTipoDocumentoAndNumDocumento(TipDocumento tipoDocumento, Long numDocumento) {
		
		return acompananteRepository.existsByTipoDocumentoAndNumDocumento(tipoDocumento, numDocumento);
	}

	@Override
	public int calcEdad(Date fechaNacimiento) {
        Calendar fechaNacimientoCal = Calendar.getInstance();
        fechaNacimientoCal.setTime(fechaNacimiento);
        Calendar fechaActualCal = Calendar.getInstance();

        int edad = fechaActualCal.get(Calendar.YEAR) - fechaNacimientoCal.get(Calendar.YEAR);
        int mesActual = fechaActualCal.get(Calendar.MONTH) + 1;
        int mesNacimiento = fechaNacimientoCal.get(Calendar.MONTH) + 1;

        // Comparar el mes de nacimiento con el mes actual para ajustar la edad
        if (mesActual < mesNacimiento || (mesActual == mesNacimiento && fechaActualCal.get(Calendar.DAY_OF_MONTH) < fechaNacimientoCal.get(Calendar.DAY_OF_MONTH))) {
            edad--;
        }

        return edad;
    }

}
