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
import co.edu.uco.compuconnect.api.validator.periodofuncionamiento.CrearPeriodoFuncionamientoValidation;
import co.edu.uco.compuconnect.business.facade.imp.PeriodoFuncionamientoFacadeImp;
import co.edu.uco.compuconnect.crosscutting.exceptions.CompuconnectException;
import co.edu.uco.compuconnect.dto.PeriodoFuncionamientoDTO;

@RestController
@RequestMapping("compuconnect/api/v1/periodo-funcionamiento")
public final class PeriodoFuncionamientoController {

    private PeriodoFuncionamientoFacadeImp facade;

    public PeriodoFuncionamientoController() {
        facade = new PeriodoFuncionamientoFacadeImp();
    }

    @GetMapping("/dummy")
    public PeriodoFuncionamientoDTO dummy() {
        return PeriodoFuncionamientoDTO.create();
    }

    @GetMapping
    public ResponseEntity<Response<PeriodoFuncionamientoDTO>> list() {
        List<PeriodoFuncionamientoDTO> lista = new ArrayList<>();
        lista.add(PeriodoFuncionamientoDTO.create());
        lista.add(PeriodoFuncionamientoDTO.create());
        lista.add(PeriodoFuncionamientoDTO.create());
        lista.add(PeriodoFuncionamientoDTO.create());
        lista.add(PeriodoFuncionamientoDTO.create());

        List<String> messages = new ArrayList<>();
        messages.add("Consulta exitosa");

        Response<PeriodoFuncionamientoDTO> response = new Response<>(lista, messages);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public PeriodoFuncionamientoDTO getById(@PathVariable UUID id) {
        return PeriodoFuncionamientoDTO.create().setIdentificador(id);
    }

    @PostMapping
    public ResponseEntity<Response<PeriodoFuncionamientoDTO>> create(@RequestBody PeriodoFuncionamientoDTO dto) {
        var statusCode = HttpStatus.OK;
        var response = new Response<PeriodoFuncionamientoDTO>();

        try {
            var result = CrearPeriodoFuncionamientoValidation.validate(dto);

            if (result.getMessages().isEmpty()) {
                facade.crear(dto);
                response.getMessages().add("El periodo funcionamiento se ha creado correctamente");
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
    public PeriodoFuncionamientoDTO update(@PathVariable UUID id, @RequestBody PeriodoFuncionamientoDTO dto) {
        return dto.setIdentificador(id);
    }

    @DeleteMapping("/{id}")
    public PeriodoFuncionamientoDTO delete(@PathVariable UUID id) {
        return PeriodoFuncionamientoDTO.create().setIdentificador(id);
    }
}
