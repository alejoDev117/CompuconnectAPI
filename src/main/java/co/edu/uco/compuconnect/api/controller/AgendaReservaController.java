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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import co.edu.uco.compuconnect.api.controller.response.Response;
import co.edu.uco.compuconnect.api.validator.agendareserva.CrearAgendaReservaValidation;
import co.edu.uco.compuconnect.business.facade.AgendaReservaFacade;
import co.edu.uco.compuconnect.business.facade.imp.AgendaReservaFacadeImp;
import co.edu.uco.compuconnect.crosscutting.exceptions.CompuconnectException;
import co.edu.uco.compuconnect.dto.AgendaReservaDTO;

@RestController
@RequestMapping("compuconnect/api/v1/agendareserva")
public final class AgendaReservaController {
	
	private Logger log = LoggerFactory.getLogger(AgendaReservaController.class);
    private AgendaReservaFacade facade;
    

    @GetMapping("/dummy")
    public AgendaReservaDTO dummy() {
        return AgendaReservaDTO.create();
    }

	@GetMapping
	public ResponseEntity<Response<AgendaReservaDTO>> list(@RequestBody AgendaReservaDTO dto) {
		
		facade = new AgendaReservaFacadeImp();
		List<AgendaReservaDTO> lista = facade.consultar(dto);
		
		List<String> messages = new ArrayList<>();
		messages.add("Agendas reserva consultados correctamente");
		
		Response<AgendaReservaDTO> response = new Response<>(lista, messages);
		return new ResponseEntity<>(response, HttpStatus.OK);
		
	}

    @GetMapping("/{id}")
    public AgendaReservaDTO getById(@PathVariable UUID id) {
        return AgendaReservaDTO.create().setIdentificador(id);
    }

    @PostMapping
    public ResponseEntity<Response<AgendaReservaDTO>> create(@RequestBody AgendaReservaDTO dto) {
        var statusCode = HttpStatus.OK;
        var response = new Response<AgendaReservaDTO>();

        try {
            var result = CrearAgendaReservaValidation.validate(dto);

            if (result.getMessages().isEmpty()) {
            	facade = new AgendaReservaFacadeImp();
                facade.crear(dto);
                response.getMessages().add("La agenda de reserva se ha creado correctamente");
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
            AgendaReservaDTO dto = new AgendaReservaDTO();
            dto.setIdentificador(id);
            facade = new AgendaReservaFacadeImp();
            facade.eliminar(dto);
            response.getMessages().add("La agenda reserva se ha eliminado correctamente");
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

