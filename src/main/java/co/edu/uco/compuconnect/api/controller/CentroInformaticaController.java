package co.edu.uco.compuconnect.api.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import co.edu.uco.compuconnect.api.controller.response.Response;
import co.edu.uco.compuconnect.api.validator.centroinformatica.CrearCentroInformaticaValidation;
import co.edu.uco.compuconnect.api.validator.centroinformatica.ModificarCentroInformaticaValidation;
import co.edu.uco.compuconnect.business.facade.CentroInformaticaFacade;
import co.edu.uco.compuconnect.business.facade.imp.CentroInformaticaFacadeImp;
import co.edu.uco.compuconnect.crosscutting.exceptions.CompuconnectException;
import co.edu.uco.compuconnect.dto.CentroInformaticaDTO;

@RestController
@RequestMapping("compuconnect/api/v2/centroinformatica")
@CrossOrigin
public final class CentroInformaticaController {

	private Logger log = LoggerFactory.getLogger(CentroInformaticaController.class);
	private CentroInformaticaFacade facade;

	@GetMapping("/dummy")
	public CentroInformaticaDTO dummy() {
		return CentroInformaticaDTO.create();
	}

	@GetMapping
	public ResponseEntity<Response<CentroInformaticaDTO>> list(CentroInformaticaDTO dto) {
		
		facade = new CentroInformaticaFacadeImp();
		List<CentroInformaticaDTO> lista = facade.read(dto);
		
		List<String> messages = new ArrayList<>();
		messages.add("Centros de informatica consultados correctamente");
		
		Response<CentroInformaticaDTO> response = new Response<>(lista, messages);
		return new ResponseEntity<>(response, HttpStatus.OK);
		
	}


	@GetMapping("/{id}")
	public CentroInformaticaDTO getById(@PathVariable UUID id) {
		return CentroInformaticaDTO.create().setIdentificador(id);
	}

	@PostMapping
	public ResponseEntity<Response<CentroInformaticaDTO>> create(@RequestBody CentroInformaticaDTO dto) {
		var statusCode = HttpStatus.OK;
		var response = new Response<CentroInformaticaDTO>();

		try {
			var result = CrearCentroInformaticaValidation.validate(dto);

			if (result.getMessages().isEmpty()) {
				facade = new CentroInformaticaFacadeImp();
				facade.create(dto);
				response.getMessages().add("El centro de Informática se ha creado correctamente");
			} else {
				statusCode = HttpStatus.BAD_REQUEST;
				response.setMessages(result.getMessages());
			}

		} catch (final CompuconnectException exception) {
			statusCode = HttpStatus.BAD_REQUEST;
			response.getMessages().add(exception.getUserMessage());
			log.error(exception.getType().toString().concat(exception.getTechnicalMessage()), exception);

			exception.printStackTrace();
		} catch (final Exception exception) {
			statusCode = HttpStatus.INTERNAL_SERVER_ERROR;
			response.getMessages().add(
					"Se ha presentado un problema inesperado. Por favor, intenta de nuevo y si el problema persiste, contacta al administrador de la aplicación");
			log.error("Se ha presentado un problema inesperado. Por favor validar la consola de errores...");
			exception.printStackTrace();
		}

		return new ResponseEntity<>(response, statusCode);
	}

	@PutMapping("/{id}")
	public ResponseEntity<Response<CentroInformaticaDTO>> update(@PathVariable UUID id,
			@RequestBody CentroInformaticaDTO dto) {
		var statusCode = HttpStatus.OK;
		var response = new Response<CentroInformaticaDTO>();

		try {
			dto.setIdentificador(id);

			var result = ModificarCentroInformaticaValidation.validate(dto);

			if (result.getMessages().isEmpty()) {
				facade = new CentroInformaticaFacadeImp();
				facade.update(dto);
				response.getMessages().add("El centro informatica se ha actualizado correctamente");
			} else {
				statusCode = HttpStatus.BAD_REQUEST;
				response.setMessages(result.getMessages());
			}

		} catch (final CompuconnectException exception) {
			statusCode = HttpStatus.BAD_REQUEST;
			response.getMessages().add(exception.getUserMessage());
			log.error(exception.getType().toString().concat(exception.getTechnicalMessage()), exception);
			exception.printStackTrace();
		} catch (final Exception exception) {
			statusCode = HttpStatus.INTERNAL_SERVER_ERROR;
			response.getMessages().add(
					"Se ha presentado un problema inesperado. Por favor, intenta de nuevo y si el problema persiste, contacta al administrador de la aplicación");
			log.error("Se ha presentado un problema inesperado. Por favor validar la consola de errores...");
			exception.printStackTrace();
		}

		return new ResponseEntity<>(response, statusCode);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Response<String>> delete(@PathVariable UUID id) {
		var statusCode = HttpStatus.OK;
		var response = new Response<String>();

		try {
			CentroInformaticaDTO dto = new CentroInformaticaDTO();
			dto.setIdentificador(id);
			facade = new CentroInformaticaFacadeImp();
			facade.delete(dto);
			response.getMessages().add("El centro de Informática se ha eliminado correctamente");
		} catch (final CompuconnectException exception) {
			statusCode = HttpStatus.BAD_REQUEST;
			response.getMessages().add(exception.getUserMessage());
			log.error(exception.getType().toString().concat(exception.getTechnicalMessage()), exception);

			exception.printStackTrace();
		} catch (final Exception exception) {
			statusCode = HttpStatus.INTERNAL_SERVER_ERROR;
			response.getMessages().add(
					"Se ha presentado un problema inesperado. Por favor, intenta de nuevo y si el problema persiste, contacta al administrador de la aplicación");
			log.error("Se ha presentado un problema inesperado. Por favor validar la consola de errores...");
			exception.printStackTrace();
		}

		return new ResponseEntity<>(response, statusCode);
	}

}
