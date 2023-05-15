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
import co.edu.uco.compuconnect.api.validator.centroinformatica.CrearCentroInformaticaValidation;
import co.edu.uco.compuconnect.business.facade.CentroInformaticaFacade;
import co.edu.uco.compuconnect.business.facade.imp.CentroInformaticaFacadeImp;
import co.edu.uco.compuconnect.crosscutting.exceptions.CompuconnectException;
import co.edu.uco.compuconnect.dto.CentroInformaticaDTO;

@RestController
@RequestMapping("compuconnect/api/v1/centro-informatica")
public final class CentroInformaticaController {

    private CentroInformaticaFacade facade;

    public CentroInformaticaController() {
        facade = new CentroInformaticaFacadeImp();
    }

    @GetMapping("/dummy")
    public CentroInformaticaDTO dummy() {
        return CentroInformaticaDTO.create();
    }

    @GetMapping
    public ResponseEntity<Response<CentroInformaticaDTO>> list() {
        List<CentroInformaticaDTO> lista = new ArrayList<>();
        lista.add(CentroInformaticaDTO.create());
        lista.add(CentroInformaticaDTO.create());
        lista.add(CentroInformaticaDTO.create());
        lista.add(CentroInformaticaDTO.create());
        lista.add(CentroInformaticaDTO.create());

        List<String> messages = new ArrayList<>();
        messages.add("Centros de Informática consultados exitosamente");

        Response<CentroInformaticaDTO> response = new Response<>(lista, messages);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public CentroInformaticaDTO getById(@PathVariable UUID id) {
        return CentroInformaticaDTO.create().setIdentificador(id);
    }

    @PostMapping
    public ResponseEntity<Response<CentroInformaticaDTO>> create(@RequestParam CentroInformaticaDTO dto) {
        var statusCode = HttpStatus.OK;
        var response = new Response<CentroInformaticaDTO>();

        try {
            var result = CrearCentroInformaticaValidation.validate(dto);

            if (result.getMessages().isEmpty()) {
                facade.create(dto);
                response.getMessages().add("El centro de Informática se ha creado correctamente");
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
    public CentroInformaticaDTO update(@PathVariable UUID id, @RequestParam CentroInformaticaDTO dto) {
        return dto.setIdentificador(id);
    }

    @DeleteMapping("/{id}")
    public CentroInformaticaDTO delete(@PathVariable UUID id) {
        return CentroInformaticaDTO.create().setIdentificador(id);
    }
}
