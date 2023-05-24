package co.edu.uco.compuconnect.api.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

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
import co.edu.uco.compuconnect.api.validator.agendareserva.CrearAgendaReservaValidation;
import co.edu.uco.compuconnect.business.facade.AgendaReservaFacade;
import co.edu.uco.compuconnect.business.facade.imp.AgendaReservaFacadeImp;
import co.edu.uco.compuconnect.crosscutting.exceptions.CompuconnectException;
import co.edu.uco.compuconnect.dto.AgendaReservaDTO;

@RestController
@RequestMapping("compuconnect/api/v1/agenda-reserva")
public final class AgendaReservaController {

    private AgendaReservaFacade facade;

    public AgendaReservaController() {
        facade = new AgendaReservaFacadeImp();
    }

    @GetMapping("/dummy")
    public AgendaReservaDTO dummy() {
        return AgendaReservaDTO.create();
    }

    @GetMapping
    public ResponseEntity<Response<AgendaReservaDTO>> list() {
        List<AgendaReservaDTO> lista = new ArrayList<>();
        lista.add(AgendaReservaDTO.create());
        lista.add(AgendaReservaDTO.create());
        lista.add(AgendaReservaDTO.create());
        lista.add(AgendaReservaDTO.create());
        lista.add(AgendaReservaDTO.create());

        List<String> messages = new ArrayList<>();
        messages.add("Consulta exitosa");

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
                facade.crear(dto);
                response.getMessages().add("La agenda de reserva se ha creado correctamente");
            } else {
                statusCode = HttpStatus.BAD_REQUEST;
                response.setMessages(result.getMessages());
            }

        } catch (final CompuconnectException exception) {
            statusCode = HttpStatus.BAD_REQUEST;
            response.getMessages().add(exception.getUserMessage());
            System.err.println(exception.getTechnicalMessage());
            System.err.println(exception.getType());
            exception.printStackTrace();
        } catch (final Exception exception) {
            statusCode = HttpStatus.INTERNAL_SERVER_ERROR;
            response.getMessages().add("Se ha presentado un problema inesperado. Por favor, intenta de nuevo y si el problema persiste, contacta al administrador de la aplicación");
            System.err.println(exception.getMessage());
            exception.printStackTrace();
        }

        return new ResponseEntity<>(response, statusCode);
    }

    @PutMapping("/{id}")
    public AgendaReservaDTO update(@PathVariable UUID id, @RequestBody AgendaReservaDTO dto) {
        return dto.setIdentificador(id);
    }

    @DeleteMapping("/{id}")
    public AgendaReservaDTO delete(@PathVariable UUID id) {
        return AgendaReservaDTO.create().setIdentificador(id);
    }
}

