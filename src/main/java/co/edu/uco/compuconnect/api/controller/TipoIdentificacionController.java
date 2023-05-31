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
import co.edu.uco.compuconnect.business.facade.TipoIdentificacionFacade;
import co.edu.uco.compuconnect.business.facade.imp.ReservaFacadeImp;
import co.edu.uco.compuconnect.business.facade.imp.TipoIdentificacionFacadeImp;
import co.edu.uco.compuconnect.dto.ReservaDTO;
import co.edu.uco.compuconnect.dto.TipoIdentificacionDTO;

@RestController
@RequestMapping("compuconnect/api/v1/tipoidentificacion")
public final class TipoIdentificacionController {

    private TipoIdentificacionFacade facade;


    @GetMapping("/dummy")
    public TipoIdentificacionDTO dummy() {
        return TipoIdentificacionDTO.create();
    }

    @GetMapping
  	public ResponseEntity<Response<TipoIdentificacionDTO>> list(@RequestBody TipoIdentificacionDTO dto) {
  		
  		facade = new TipoIdentificacionFacadeImp();
  		List<TipoIdentificacionDTO> lista = facade.consultar(dto);
  		
  		List<String> messages = new ArrayList<>();
  		messages.add("Agendas reserva consultados correctamente");
  		
  		Response<TipoIdentificacionDTO> response = new Response<>(lista, messages);
  		return new ResponseEntity<>(response, HttpStatus.OK);
  		
  	}

    @GetMapping("/{id}")
    public TipoIdentificacionDTO listById(@PathVariable UUID id) {
        return TipoIdentificacionDTO.create().setIdentificador(id);
    }
}