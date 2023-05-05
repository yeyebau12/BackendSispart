package com.proyecto.apartahotel.sispart.login.service;

import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.proyecto.apartahotel.sispart.login.entity.UsuarioEmpleado;
import com.proyecto.apartahotel.sispart.login.repository.IUsuarioEmpleadoReposistory;

@Service
public class UsuarioEmpleadoServiceImpl implements UserDetailsService, IUsuarioEmpleadoService {

	private Logger logger = LoggerFactory.getLogger(UsuarioEmpleado.class);

	@Autowired
	private IUsuarioEmpleadoReposistory usuarioEmpleadoRepository;

	@Override
	@Transactional(readOnly = true)
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		UsuarioEmpleado usuarioEmpleado = usuarioEmpleadoRepository.findByUserName(username);

		if (usuarioEmpleado == null) {
			logger.error("ERROR EN EL LOGIN: No existe el usuario '" + username + "' en el sistema!.");
			throw new UsernameNotFoundException(
					"ERROR EN EL LOGIN: No existe el usuario '" + username + "' en el sistema!.");
		}

		List<GrantedAuthority> authorities = usuarioEmpleado.getRol().stream()
				.map(role -> new SimpleGrantedAuthority(role.getNombre()))
				.peek(authority -> logger.info("Role: " + authority.getAuthority())).collect(Collectors.toList());
		return new User(usuarioEmpleado.getUserName(), usuarioEmpleado.getContraseña(), usuarioEmpleado.getEnabled(),
				true, true, true, authorities);
	}

	@Override
	@Transactional(readOnly = true)
	public List<UsuarioEmpleado> findAll() {

		return usuarioEmpleadoRepository.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public UsuarioEmpleado findByUsername(String username) {

		return usuarioEmpleadoRepository.findByUserName(username);
	}

	@Override
	@Transactional
	public void save(UsuarioEmpleado userEmpleado) {

		usuarioEmpleadoRepository.save(userEmpleado);

	}

}
