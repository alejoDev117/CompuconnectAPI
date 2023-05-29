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
import co.edu.uco.compuconnect.api.validator.detallereserva.CrearDetalleReservaValidation;
import co.edu.uco.compuconnect.business.facade.DetalleReservaFacade;
import co.edu.uco.compuconnect.business.facade.imp.DetalleReservaFacadeImp;
import co.edu.uco.compuconnect.crosscutting.exceptions.CompuconnectException;
import co.edu.uco.compuconnect.dto.DetalleReservaDTO;

@RestController
@RequestMapping("compuconnect/api/v1/detalle-reserva")
public final class DetalleReservaController {

	private Logger log = LoggerFactory.getLogger(DetalleReservaController.class);
    private DetalleReservaFacade facade;

    public DetalleReservaController() {
        facade = new DetalleReservaFacadeImp();
    }

    @GetMapping("/dummy")
    public DetalleReservaDTO dummy() {
        return DetalleReservaDTO.create();
    }

    @GetMapping
    public ResponseEntity<Response<DetalleReservaDTO>> list() {
        List<DetalleReservaDTO> lista = new ArrayList<>();
        lista.add(DetalleReservaDTO.create());
        lista.add(DetalleReservaDTO.create());
        lista.add(DetalleReservaDTO.create());
        lista.add(DetalleReservaDTO.create());
        lista.add(DetalleReservaDTO.create());

        List<String> messages = new ArrayList<>();
        messages.add("Consulta exitosa");

        Response<DetalleReservaDTO> response = new Response<>(lista, messages);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public DetalleReservaDTO getById(@PathVariable UUID id) {
        return DetalleReservaDTO.create().setIdentificador(id);
    }

    @PostMapping
    public ResponseEntity<Response<DetalleReservaDTO>> create(@RequestBody DetalleReservaDTO dto) {
        var statusCode = HttpStatus.OK;
        var response = new Response<DetalleReservaDTO>();

        try {
            var result = CrearDetalleReservaValidation.validate(dto);

            if (result.getMessages().isEmpty()) {
                facade.crear(dto);
                response.getMessages().add("El detalle de reserva se ha creado correctamente");
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
    public DetalleReservaDTO update(@PathVariable UUID id, @RequestBody DetalleReservaDTO dto) {
        return dto.setIdentificador(id);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Response<String>> delete(@PathVariable UUID id) {
        var statusCode = HttpStatus.OK;
        var response = new Response<String>();

        try {
            DetalleReservaDTO dto = new DetalleReservaDTO();
            dto.setIdentificador(id);
            facade.eliminar(dto);
            response.getMessages().add("El detalle reserva se ha eliminado correctamente");
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

