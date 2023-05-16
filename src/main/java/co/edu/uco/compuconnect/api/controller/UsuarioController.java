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
import co.edu.uco.compuconnect.api.validator.usuario.CrearUsuarioValidation;
import co.edu.uco.compuconnect.business.facade.UsuarioFacade;
import co.edu.uco.compuconnect.business.facade.imp.UsuarioFacadeImp;
import co.edu.uco.compuconnect.crosscutting.exceptions.CompuconnectException;
import co.edu.uco.compuconnect.dto.UsuarioDTO;

@RestController
@RequestMapping("compuconnect/api/v1/usuario")
public final class UsuarioController {

    private UsuarioFacade facade;

    public UsuarioController() {
        facade = new UsuarioFacadeImp();
    }

    @GetMapping("/dummy")
    public UsuarioDTO dummy() {
        return UsuarioDTO.create();
    }

    @GetMapping
    public ResponseEntity<Response<UsuarioDTO>> list() {
        List<UsuarioDTO> lista = new ArrayList<>();
        lista.add(UsuarioDTO.create());
        lista.add(UsuarioDTO.create());
        lista.add(UsuarioDTO.create());
        lista.add(UsuarioDTO.create());
        lista.add(UsuarioDTO.create());

        List<String> messages = new ArrayList<>();
        messages.add("Usuarios consultados exitosamente");

        Response<UsuarioDTO> response = new Response<>(lista, messages);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public UsuarioDTO getById(@PathVariable UUID id) {
        return UsuarioDTO.create().setIdentificador(id);
    }

    @PostMapping
    public ResponseEntity<Response<UsuarioDTO>> create(@RequestParam UsuarioDTO dto) {
        var statusCode = HttpStatus.OK;
        var response = new Response<UsuarioDTO>();

        try {
            var result = CrearUsuarioValidation.validate(dto);

            if (result.getMessages().isEmpty()) {
                facade.crear(dto);
                response.getMessages().add("El usuario se ha creado correctamente");
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
    public UsuarioDTO update(@PathVariable UUID id, @RequestParam UsuarioDTO dto) {
        return dto.setIdentificador(id);
    }

    @DeleteMapping("/{id}")
    public UsuarioDTO delete(@PathVariable UUID id) {
        return UsuarioDTO.create().setIdentificador(id);
    }
}