package com.proyecto.apartahotel.sispart.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.proyecto.apartahotel.sispart.entity.UsuarioEmpleado;

@Repository
public interface IUsuarioEmpleadoReposistory extends JpaRepository<UsuarioEmpleado, Integer> {

	public UsuarioEmpleado findByUserName(String userName);
}
