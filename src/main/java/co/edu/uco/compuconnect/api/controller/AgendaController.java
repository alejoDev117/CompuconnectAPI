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
import co.edu.uco.compuconnect.api.validator.agenda.CrearAgendaValidation;
import co.edu.uco.compuconnect.business.facade.AgendaFacade;
import co.edu.uco.compuconnect.business.facade.imp.AgendaFacadeImp;
import co.edu.uco.compuconnect.crosscutting.exceptions.CompuconnectException;
import co.edu.uco.compuconnect.dto.AgendaDTO;

@RestController
@RequestMapping("compuconnect/api/v1/agenda")
public final class AgendaController {

    private AgendaFacade facade;

    public AgendaController() {
        facade = new AgendaFacadeImp();
    }

    @GetMapping("/dummy")
    public AgendaDTO dummy() {
        return AgendaDTO.create();
    }

    @GetMapping
    public ResponseEntity<Response<AgendaDTO>> list() {
        List<AgendaDTO> lista = new ArrayList<>();
        lista.add(AgendaDTO.create());
        lista.add(AgendaDTO.create());
        lista.add(AgendaDTO.create());
        lista.add(AgendaDTO.create());
        lista.add(AgendaDTO.create());

        List<String> messages = new ArrayList<>();
        messages.add("consulta exitosa");

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
                facade.registrar(dto);;
                response.getMessages().add("La agenda se ha creado correctamente");
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
    public AgendaDTO update(@PathVariable UUID id, @RequestBody AgendaDTO dto) {
        return dto.setIdentificador(id);
    }

    @DeleteMapping("/{id}")
    public AgendaDTO delete(@PathVariable UUID id) {
        return AgendaDTO.create().setIdentificador(id);
    }
}
