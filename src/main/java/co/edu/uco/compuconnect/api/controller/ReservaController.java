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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import co.edu.uco.compuconnect.api.controller.response.Response;
import co.edu.uco.compuconnect.api.validator.reserva.GenerarReservaValidation;
import co.edu.uco.compuconnect.api.validator.reserva.ModificarReservaValidation;
import co.edu.uco.compuconnect.business.facade.ReservaFacade;
import co.edu.uco.compuconnect.business.facade.imp.ReservaFacadeImp;
import co.edu.uco.compuconnect.crosscutting.exceptions.CompuconnectException;
import co.edu.uco.compuconnect.dto.ReservaDTO;



@RestController
@RequestMapping("compuconnect/api/v1/reserva")
public final class ReservaController {
	
	private Logger log = LoggerFactory.getLogger(ReservaController.class);
    private ReservaFacade facade;

  
    @GetMapping("/dummy")
    public ReservaDTO dummy() {
        return ReservaDTO.create();
    }

    @GetMapping
	public ResponseEntity<Response<ReservaDTO>> list(@RequestBody ReservaDTO dto) {
		
		facade = new ReservaFacadeImp();
		List<ReservaDTO> lista = facade.consultar(dto);
		
		List<String> messages = new ArrayList<>();
		messages.add("Agendas reserva consultados correctamente");
		
		Response<ReservaDTO> response = new Response<>(lista, messages);
		return new ResponseEntity<>(response, HttpStatus.OK);
		
	}

    @GetMapping("/{id}")
    public ReservaDTO listById(@PathVariable UUID id) {
        return ReservaDTO.create().setIdentificador(id);
    }

    @PostMapping
    public ResponseEntity<Response<ReservaDTO>> create(@RequestParam ReservaDTO dto) {
        var statusCode = HttpStatus.OK;
        var response = new Response<ReservaDTO>();

        try {
            var result = GenerarReservaValidation.validate(dto);

            if (result.getMessages().isEmpty()) {
            	facade = new ReservaFacadeImp();
                facade.generar(dto);
                response.getMessages().add("La reserva se ha generado correctamente");
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
    public ResponseEntity<Response<ReservaDTO>> update(@PathVariable UUID id, @RequestBody ReservaDTO dto) {
        var statusCode = HttpStatus.OK;
        var response = new Response<ReservaDTO>();

        try {
            dto.setIdentificador(id);

            var result = ModificarReservaValidation.validate(dto);

            if (result.getMessages().isEmpty()) {
            	facade = new ReservaFacadeImp();
                facade.modificar(dto);
                response.getMessages().add("La reserva se ha actualizado correctamente");
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
            ReservaDTO dto = new ReservaDTO();
            dto.setIdentificador(id);
            facade = new ReservaFacadeImp();
            facade.cancelar(dto);
            response.getMessages().add("La reserva se ha eliminado/cancelado correctamente");
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
