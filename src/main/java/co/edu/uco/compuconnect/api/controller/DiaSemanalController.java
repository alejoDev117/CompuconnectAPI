package co.edu.uco.compuconnect.api.controller;

import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import co.edu.uco.compuconnect.api.controller.response.Response;
import co.edu.uco.compuconnect.business.facade.DiaSemanalFacade;
import co.edu.uco.compuconnect.business.facade.imp.DiaSemanalFacadeImp;
import co.edu.uco.compuconnect.dto.DiaSemanalDTO;
import java.util.List;
import java.util.ArrayList;

@RestController
@RequestMapping("compuconnect/api/v1/diasemanal")
public final class DiaSemanalController {

    private DiaSemanalFacade facade;

    public DiaSemanalController() {
        facade = new DiaSemanalFacadeImp();
    }

    @GetMapping("/dummy")
    public DiaSemanalDTO dummy() {
        return DiaSemanalDTO.create();
    }

    @GetMapping("/list")
    public ResponseEntity<Response<DiaSemanalDTO>> list() {
        List<DiaSemanalDTO> lista = new ArrayList<>();
        lista.add(DiaSemanalDTO.create());
        lista.add(DiaSemanalDTO.create());
        lista.add(DiaSemanalDTO.create());
        lista.add(DiaSemanalDTO.create());
        lista.add(DiaSemanalDTO.create());

        List<String> messages = new ArrayList<>();
        messages.add("Días semanales consultados exitosamente");

        Response<DiaSemanalDTO> response = new Response<>(lista, messages);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public DiaSemanalDTO listById(@PathVariable UUID id) {
    	return DiaSemanalDTO.create().setIdentificador(id);
}
}
