package com.proyecto.apartahotel.sispart.service.interfa;

public interface IEmailService {

	public void sendEmail(String emailto, String body);
	
	public void sendEmailReserva(String emailto, String body);

}
