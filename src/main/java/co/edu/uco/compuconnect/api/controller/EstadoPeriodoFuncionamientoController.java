package co.edu.uco.compuconnect.api.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import co.edu.uco.compuconnect.api.controller.response.Response;
import co.edu.uco.compuconnect.business.facade.EstadoPeriodoFuncionamientoFacade;
import co.edu.uco.compuconnect.business.facade.imp.EstadoPeriodoFuncionamientoFacadeImp;
import co.edu.uco.compuconnect.dto.EstadoPeriodoFuncionamientoDTO;
import java.util.List;
import java.util.UUID;
import java.util.ArrayList;

@RestController
@RequestMapping("compuconnect/api/v1/estadoperiodofuncionamiento")
public final class EstadoPeriodoFuncionamientoController {

    private EstadoPeriodoFuncionamientoFacade facade;

    
    @GetMapping("/dummy")
    public EstadoPeriodoFuncionamientoDTO dummy() {
        return EstadoPeriodoFuncionamientoDTO.create();
    }

    @GetMapping
	public ResponseEntity<Response<EstadoPeriodoFuncionamientoDTO>> list(@RequestBody EstadoPeriodoFuncionamientoDTO dto) {
		
		facade = new EstadoPeriodoFuncionamientoFacadeImp();
		List<EstadoPeriodoFuncionamientoDTO> lista = facade.consultar(dto);
		
		List<String> messages = new ArrayList<>();
		messages.add("Agendas reserva consultados correctamente");
		
		Response<EstadoPeriodoFuncionamientoDTO> response = new Response<>(lista, messages);
		return new ResponseEntity<>(response, HttpStatus.OK);
		
	}


    @GetMapping("/{id}")
    public EstadoPeriodoFuncionamientoDTO listById(@PathVariable UUID id) {
        return EstadoPeriodoFuncionamientoDTO.create().setIdentificador(id);
    }
}