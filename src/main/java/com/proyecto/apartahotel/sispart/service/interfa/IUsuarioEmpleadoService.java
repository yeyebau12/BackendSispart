package com.proyecto.apartahotel.sispart.service.interfa;


import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.proyecto.apartahotel.sispart.entity.UsuarioEmpleado;



public interface IUsuarioEmpleadoService {

	public List<UsuarioEmpleado> findAll();
	
	public Page<UsuarioEmpleado> findAll(Pageable pageable);

	public UsuarioEmpleado getOne(Long codUsuarioEmpleado);

	public void delete(Long codUsuarioEmpleado);

	public boolean existsById(Long codUsuarioEmpleado);
	
	public boolean existsByUserName(String userName);
	
	public void save(UsuarioEmpleado userEmpleado);
	
	public UsuarioEmpleado findByUsername(String username);
}
