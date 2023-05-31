package co.edu.uco.compuconnect.api.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import co.edu.uco.compuconnect.api.controller.response.Response;
import co.edu.uco.compuconnect.api.validator.agenda.ActualizarAgendaValidation;
import co.edu.uco.compuconnect.api.validator.agenda.CrearAgendaValidation;
import co.edu.uco.compuconnect.business.facade.AgendaFacade;
import co.edu.uco.compuconnect.business.facade.imp.AgendaFacadeImp;
import co.edu.uco.compuconnect.business.facade.imp.CentroInformaticaFacadeImp;
import co.edu.uco.compuconnect.crosscutting.exceptions.CompuconnectException;
import co.edu.uco.compuconnect.dto.AgendaDTO;
import co.edu.uco.compuconnect.dto.CentroInformaticaDTO;

@RestController
@RequestMapping("compuconnect/api/v1/agenda")
public final class AgendaController {

	
	private Logger log = LoggerFactory.getLogger(AgendaController.class);
    private AgendaFacade facade;


    @GetMapping("/dummy")
    public AgendaDTO dummy() {
        return AgendaDTO.create();
    }

	@GetMapping
	public ResponseEntity<Response<AgendaDTO>> list(@RequestBody AgendaDTO dto) {
		
		facade = new AgendaFacadeImp();
		List<AgendaDTO> lista = facade.consultar(dto);
		
		List<String> messages = new ArrayList<>();
		messages.add("Agendas consultadas correctamente");
		
		Response<AgendaDTO> response = new Response<>(lista, messages);
		return new ResponseEntity<>(response, HttpStatus.OK);
		
	}


    @GetMapping("/{id}")
    public AgendaDTO getById(@PathVariable UUID id) {
        return AgendaDTO.create().setIdentificador(id);
    }

    @PostMapping
    public ResponseEntity<Response<AgendaDTO>> create(@RequestBody AgendaDTO dto) {
        var statusCode = HttpStatus.OK;
        var response = new Response<AgendaDTO>();

        try {
            var result = CrearAgendaValidation.validate(dto);

            if (result.getMessages().isEmpty()) {
            	facade = new AgendaFacadeImp();
                facade.registrar(dto);
                response.getMessages().add("La agenda se ha creado correctamente");
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
            response.getMessages().add("Se ha presentado un problema inesperado. Por favor, intenta de nuevo y si el problema persiste, contacta al administrador de la aplicación");
            log.error("Se ha presentado un problema inesperado. Por favor validar la consola de errores...");
            exception.printStackTrace();
        }

        return new ResponseEntity<>(response, statusCode);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Response<AgendaDTO>> update(@PathVariable UUID id, @RequestBody AgendaDTO dto) {
        var statusCode = HttpStatus.OK;
        var response = new Response<AgendaDTO>();

        try {
            dto.setIdentificador(id);

            var result = ActualizarAgendaValidation.validate(dto);

            if (result.getMessages().isEmpty()) {
                facade.modificar(dto);
                response.getMessages().add("La agenda se ha actualizado correctamente");
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
            response.getMessages().add("Se ha presentado un problema inesperado. Por favor, intenta de nuevo y si el problema persiste, contacta al administrador de la aplicación");
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
            AgendaDTO dto = new AgendaDTO();
            dto.setIdentificador(id);
            facade.eliminar(dto);
            response.getMessages().add("La agenda se ha eliminado correctamente");
        } catch (final CompuconnectException exception) {
            statusCode = HttpStatus.BAD_REQUEST;
            response.getMessages().add(exception.getUserMessage());
            log.error(exception.getType().toString().concat(exception.getTechnicalMessage()), exception);
      
            exception.printStackTrace();
        } catch (final Exception exception) {
            statusCode = HttpStatus.INTERNAL_SERVER_ERROR;
            response.getMessages().add("Se ha presentado un problema inesperado. Por favor, intenta de nuevo y si el problema persiste, contacta al administrador de la aplicación");
            log.error("Se ha presentado un problema inesperado. Por favor validar la consola de errores...");
            exception.printStackTrace();
        }

        return new ResponseEntity<>(response, statusCode);
    }
}
