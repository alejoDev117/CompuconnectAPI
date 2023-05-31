package co.edu.uco.compuconnect.api.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import co.edu.uco.compuconnect.api.controller.response.Response;
import co.edu.uco.compuconnect.business.facade.FrecuenciaFacade;
import co.edu.uco.compuconnect.business.facade.imp.FrecuenciaFacadeImp;
import co.edu.uco.compuconnect.dto.FrecuenciaDTO;

@RestController
@RequestMapping("compuconnect/api/v1/frecuencia")
public final class FrecuenciaController {

    private FrecuenciaFacade facade;


    @GetMapping("/dummy")
    public FrecuenciaDTO dummy() {
        return FrecuenciaDTO.create();
    }

    @GetMapping
	public ResponseEntity<Response<FrecuenciaDTO>> list(@RequestBody FrecuenciaDTO dto) {
		
		facade = new FrecuenciaFacadeImp();
		List<FrecuenciaDTO> lista = facade.consultar(dto);
		
		List<String> messages = new ArrayList<>();
		messages.add("Agendas reserva consultados correctamente");
		
		Response<FrecuenciaDTO> response = new Response<>(lista, messages);
		return new ResponseEntity<>(response, HttpStatus.OK);
		
	}


    @GetMapping("/{id}")
    public FrecuenciaDTO listById(@PathVariable UUID id) {
        return FrecuenciaDTO.create().setIdentificador(id);
    }



}
