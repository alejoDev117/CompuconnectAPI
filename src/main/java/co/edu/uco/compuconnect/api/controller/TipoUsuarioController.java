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
import co.edu.uco.compuconnect.business.facade.TipoUsuarioFacade;
import co.edu.uco.compuconnect.business.facade.imp.TipoUsuarioFacadeImp;
import co.edu.uco.compuconnect.dto.TipoUsuarioDTO;

@RestController
@RequestMapping("compuconnect/api/v1/tipousuario")
public final class TipoUsuarioController {

    private TipoUsuarioFacade facade;


    @GetMapping("/dummy")
    public TipoUsuarioDTO dummy() {
        return TipoUsuarioDTO.create();
    }

    @GetMapping
  	public ResponseEntity<Response<TipoUsuarioDTO>> list(@RequestBody TipoUsuarioDTO dto) {
  		
  		facade = new TipoUsuarioFacadeImp();
  		List<TipoUsuarioDTO> lista = facade.consultar(dto);
  		
  		List<String> messages = new ArrayList<>();
  		messages.add("Agendas reserva consultados correctamente");
  		
  		Response<TipoUsuarioDTO> response = new Response<>(lista, messages);
  		return new ResponseEntity<>(response, HttpStatus.OK);
  		
  	}

    @GetMapping("/{id}")
    public TipoUsuarioDTO listById(@PathVariable UUID id) {
        return TipoUsuarioDTO.create().setIdentificador(id);
    }
}