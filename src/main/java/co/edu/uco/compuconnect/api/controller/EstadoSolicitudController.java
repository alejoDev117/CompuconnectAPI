package co.edu.uco.compuconnect.api.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("compuconnect/api/estadosolicitud")
public class EstadoSolicitudController {

	@GetMapping
	public String text() {
		return "hola Mundo";	
		
	}
}
