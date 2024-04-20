package com.proyecto.apartahotel.sispart.login.auth;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;
import org.springframework.stereotype.Component;

import com.proyecto.apartahotel.sispart.entity.UsuarioEmpleado;
import com.proyecto.apartahotel.sispart.service.interfa.IUsuarioEmpleadoService;

@Component
public class infoAdicionalToken implements TokenEnhancer {

	@Autowired
	private IUsuarioEmpleadoService usuarioService;

	@Override
	public OAuth2AccessToken enhance(OAuth2AccessToken accessToken, OAuth2Authentication authentication) {

		UsuarioEmpleado usuarioEmpleado = usuarioService.findByUsername(authentication.getName());
		Map<String, Object> info = new HashMap<>();
		List< Object> empleado = new ArrayList<>();
		
	    empleado.add(usuarioEmpleado.getEmpleado().getCodEmpleado());
	    empleado.add(usuarioEmpleado.getEmpleado().getNombre());
	    empleado.add(usuarioEmpleado.getEmpleado().getApellido());
	    
		info.put("cod_empleado",usuarioEmpleado.getEmpleado().getCodEmpleado());
		info.put("nombre", usuarioEmpleado.getEmpleado().getNombre());
		info.put("apellido", usuarioEmpleado.getEmpleado().getApellido());
		info.put("empleado", empleado);
		
		((DefaultOAuth2AccessToken) accessToken).setAdditionalInformation(info);
		return accessToken;
	}

}
