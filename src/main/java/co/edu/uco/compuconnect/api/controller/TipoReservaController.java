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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import co.edu.uco.compuconnect.api.controller.response.Response;
import co.edu.uco.compuconnect.business.facade.TipoReservaFacade;
import co.edu.uco.compuconnect.business.facade.imp.ReservaFacadeImp;
import co.edu.uco.compuconnect.business.facade.imp.TipoReservaFacadeImp;
import co.edu.uco.compuconnect.dto.ReservaDTO;
import co.edu.uco.compuconnect.dto.TipoReservaDTO;

@RestController
@RequestMapping("compuconnect/api/v1/tiporeserva")
public final class TipoReservaController {

    private TipoReservaFacade facade;

 

    @GetMapping("/dummy")
    public TipoReservaDTO dummy() {
        return TipoReservaDTO.create();
    }

    @GetMapping
  	public ResponseEntity<Response<TipoReservaDTO>> list(@RequestBody TipoReservaDTO dto) {
  		
  		facade = new TipoReservaFacadeImp();
  		List<TipoReservaDTO> lista = facade.consultar(dto);
  		
  		List<String> messages = new ArrayList<>();
  		messages.add("Agendas reserva consultados correctamente");
  		
  		Response<TipoReservaDTO> response = new Response<>(lista, messages);
  		return new ResponseEntity<>(response, HttpStatus.OK);
  		
  	}

    @GetMapping("/{id}")
    public TipoReservaDTO listById(@PathVariable UUID id) {
        return TipoReservaDTO.create().setIdentificador(id);
    }
}