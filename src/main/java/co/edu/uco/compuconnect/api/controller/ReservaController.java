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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import co.edu.uco.compuconnect.api.controller.response.Response;
import co.edu.uco.compuconnect.api.validator.reserva.GenerarReservaValidation;
import co.edu.uco.compuconnect.business.facade.ReservaFacade;
import co.edu.uco.compuconnect.business.facade.imp.ReservaFacadeImp;
import co.edu.uco.compuconnect.crosscutting.exceptions.CompuconnectException;
import co.edu.uco.compuconnect.dto.ReservaDTO;



@RestController
@RequestMapping("compuconnect/api/v1/reserva")
public final class ReservaController {

    private ReservaFacade facade;

    public ReservaController() {
        facade = new ReservaFacadeImp();
    }

    @GetMapping("/dummy")
    public ReservaDTO dummy() {
        return ReservaDTO.create();
    }

    @GetMapping
    public ResponseEntity<Response<ReservaDTO>> list(@RequestParam ReservaDTO dto) {
        List<ReservaDTO> lista = new ArrayList<>();
        lista.add(ReservaDTO.create());
        lista.add(ReservaDTO.create());
        lista.add(ReservaDTO.create());
        lista.add(ReservaDTO.create());
        lista.add(ReservaDTO.create());

        List<String> messages = new ArrayList<>();
        messages.add("Reservas consultadas exitosamente");

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
                facade.generar(dto);
                response.getMessages().add("La reserva se ha generado correctamente");
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
    public ReservaDTO update(@PathVariable UUID id, @RequestParam ReservaDTO dto) {
        return dto.setIdentificador(id);
    }

    @DeleteMapping("/{id}")
    public ReservaDTO cancel(@PathVariable UUID id) {
        return ReservaDTO.create().setIdentificador(id);
    }
}