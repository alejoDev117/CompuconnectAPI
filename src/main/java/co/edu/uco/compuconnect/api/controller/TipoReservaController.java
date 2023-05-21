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
import co.edu.uco.compuconnect.business.facade.TipoReservaFacade;
import co.edu.uco.compuconnect.business.facade.imp.TipoReservaFacadeImp;
import co.edu.uco.compuconnect.dto.TipoReservaDTO;

@RestController
@RequestMapping("compuconnect/api/v1/tiporeserva")
public final class TipoReservaController {

    private TipoReservaFacade facade;

    public TipoReservaController() {
        facade = new TipoReservaFacadeImp();
    }

    @GetMapping("/dummy")
    public TipoReservaDTO dummy() {
        return TipoReservaDTO.create();
    }

    @GetMapping("/list")
    public ResponseEntity<Response<TipoReservaDTO>> list(@RequestParam TipoReservaDTO dto) {
        List<TipoReservaDTO> lista = new ArrayList<>();
        lista.add(TipoReservaDTO.create());
        lista.add(TipoReservaDTO.create());
        lista.add(TipoReservaDTO.create());
        lista.add(TipoReservaDTO.create());
        lista.add(TipoReservaDTO.create());

        List<String> messages = new ArrayList<>();
        messages.add("Tipos de reserva consultados exitosamente");

        Response<TipoReservaDTO> response = new Response<>(lista, messages);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public TipoReservaDTO listById(@PathVariable UUID id) {
        return TipoReservaDTO.create().setIdentificador(id);
    }
}