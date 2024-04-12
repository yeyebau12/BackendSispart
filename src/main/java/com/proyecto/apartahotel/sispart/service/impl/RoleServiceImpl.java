package com.proyecto.apartahotel.sispart.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.proyecto.apartahotel.sispart.entity.Role;
import com.proyecto.apartahotel.sispart.repository.IRoleRepository;
import com.proyecto.apartahotel.sispart.service.interfa.IRoleService;

@Service
public class RoleServiceImpl implements IRoleService{
	
	@Autowired
	private IRoleRepository roleRepository;

	@Override
	public List<Role> findAll() {
		
		return roleRepository.findAll();
	}
	
	

}
