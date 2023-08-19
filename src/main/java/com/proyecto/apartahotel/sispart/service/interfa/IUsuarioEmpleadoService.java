package com.proyecto.apartahotel.sispart.service.interfa;

import java.util.List;

import com.proyecto.apartahotel.sispart.entity.UsuarioEmpleado;

public interface IUsuarioEmpleadoService {

	public List<UsuarioEmpleado> findAll();
	
	public void save(UsuarioEmpleado userEmpleado);
	
	public UsuarioEmpleado findByUsername(String username);
}
