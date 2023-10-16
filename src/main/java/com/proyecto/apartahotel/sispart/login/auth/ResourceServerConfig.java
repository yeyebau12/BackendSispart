package com.proyecto.apartahotel.sispart.login.auth;

import org.springframework.http.HttpMethod;

import java.util.Arrays;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

@Configuration
@EnableResourceServer
public class ResourceServerConfig extends ResourceServerConfigurerAdapter {

	@Override
	public void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests().antMatchers(HttpMethod.POST, "/loginEmpleado/registroLoginEmpleado").permitAll()
				.antMatchers("/tipoDocumento/**").permitAll().antMatchers("/nacionalidad/**").permitAll()
				.antMatchers("/loginEmpleado/**").permitAll().antMatchers(HttpMethod.POST, "/empleados/crearEmpleado")
				.hasAnyRole("ADMINISTRADOR").antMatchers(HttpMethod.PUT, "/empleados/actualizarEmpleado/{codEmpleado}")
				.hasAnyRole("ADMINISTRADOR").antMatchers(HttpMethod.GET, "/empleados/listarEmpleados").permitAll()
				.antMatchers(HttpMethod.GET, "/huespedes/listarHuespedes").permitAll()
				.antMatchers(HttpMethod.POST, "/huespedes/crearHuesped").hasAnyRole("ADMINISTRADOR")
				.antMatchers(HttpMethod.DELETE, "/empleados/eliminarEmpleado/{codEmpleado}").hasAnyRole("ADMINISTRADOR")
				/*
				 * .antMatchers(HttpMethod.GET,
				 * "/loginEmpleado/listarLoginEmpleados").hasAnyRole("ADMINISTRADOR")
				 */
				.antMatchers(HttpMethod.POST, "/comentarios/crearComentario").permitAll()
				.antMatchers("/reservaciones/**").permitAll().antMatchers("/comentarios/**").permitAll()
				.antMatchers(HttpMethod.GET, "/habitacion/verHabitacion/{codHabitacion}",
						"/habitacion/listarHabitaciones/estado/{estadoHabitacion}")
				.permitAll().anyRequest().authenticated().and().cors().configurationSource(corsConfigurationSource());
	}

	@Bean
	public CorsConfigurationSource corsConfigurationSource() {

		CorsConfiguration corsConfig = new CorsConfiguration();
		corsConfig.setAllowedOriginPatterns(Arrays.asList("*"));
		corsConfig.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE", "OPTIONS"));
		corsConfig.setAllowCredentials(true);
		corsConfig.setAllowedHeaders(Arrays.asList("Content-Type", "Authorization"));

		UrlBasedCorsConfigurationSource corsSource = new UrlBasedCorsConfigurationSource();
		corsSource.registerCorsConfiguration("/**", corsConfig);

		return corsSource;
	}

	@Bean
	public FilterRegistrationBean<CorsFilter> corsFilter() {

		FilterRegistrationBean<CorsFilter> corsBean = new FilterRegistrationBean<CorsFilter>(
				new CorsFilter(corsConfigurationSource()));
		corsBean.setOrder(Ordered.HIGHEST_PRECEDENCE);
		return corsBean;
	}

}
