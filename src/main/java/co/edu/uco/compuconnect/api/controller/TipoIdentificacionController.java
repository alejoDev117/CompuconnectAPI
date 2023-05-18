package co.edu.uco.compuconnect.api.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import co.edu.uco.compuconnect.api.controller.response.Response;
import co.edu.uco.compuconnect.business.facade.TipoIdentificacionFacade;
import co.edu.uco.compuconnect.business.facade.imp.TipoIdentificacionFacadeImp;
import co.edu.uco.compuconnect.dto.TipoIdentificacionDTO;
import co.edu.uco.compuconnect.dto.TipoUsuarioDTO;

@RestController
@RequestMapping("compuconnect/api/v1/tipousuario")
public final class TipoIdentificacionController {

    private TipoIdentificacionFacade facade;

    public TipoIdentificacionController() {
        facade = new TipoIdentificacionFacadeImp();
    }

    @GetMapping("/dummy")
    public TipoUsuarioDTO dummy() {
        return TipoUsuarioDTO.create();
    }

    @GetMapping
    public ResponseEntity<Response<TipoIdentificacionDTO>> list(@RequestParam TipoIdentificacionDTO dto) {
        List<TipoIdentificacionDTO> lista = new ArrayList<>();
        lista.add(TipoIdentificacionDTO.create());
        lista.add(TipoIdentificacionDTO.create());
        lista.add(TipoIdentificacionDTO.create());
        lista.add(TipoIdentificacionDTO.create());
        lista.add(TipoIdentificacionDTO.create());

        List<String> messages = new ArrayList<>();
        messages.add("Tipos de usuario consultados exitosamente");

        Response<TipoIdentificacionDTO> response = new Response<>(lista, messages);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public TipoIdentificacionDTO listById(@PathVariable UUID id) {
        return TipoIdentificacionDTO.create().setIdentificador(id);
    }
}