package com.proyecto.apartahotel.sispart.login.service;

import java.util.List;

import com.proyecto.apartahotel.sispart.login.entity.UsuarioEmpleado;

public interface IUsuarioEmpleadoService {

	public List<UsuarioEmpleado> findAll();
	
	public void save(UsuarioEmpleado userEmpleado);
	
	public UsuarioEmpleado findByUsername(String username);
}
