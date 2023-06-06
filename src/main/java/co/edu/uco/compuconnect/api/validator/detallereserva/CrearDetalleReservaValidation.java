package co.edu.uco.compuconnect.api.validator.detallereserva;

import java.util.List;

import co.edu.uco.compuconnect.api.validator.Result;
import co.edu.uco.compuconnect.api.validator.Validation;
import co.edu.uco.compuconnect.api.validator.detallereserva.common.DiaSemanalValidation;
import co.edu.uco.compuconnect.api.validator.detallereserva.common.HoraFinValidation;
import co.edu.uco.compuconnect.api.validator.detallereserva.common.HoraInicioValidation;
import co.edu.uco.compuconnect.api.validator.generalcommon.IdentificadorValidation;
import co.edu.uco.compuconnect.crosscutting.utils.UtilObject;
import co.edu.uco.compuconnect.dto.DetalleReservaDTO;

public class CrearDetalleReservaValidation implements Validation<List<DetalleReservaDTO>> {
	
	private CrearDetalleReservaValidation() {
		super();
	}
	
	public static Result validate(final List<DetalleReservaDTO> data) {
		return new CrearDetalleReservaValidation().execute(data);
	}

	@Override
	public Result execute(List<DetalleReservaDTO> data) {
		var result = Result.create();
		for (DetalleReservaDTO detalleReservaDTO : data) {
	        if (UtilObject.isNull(data)) {
	            result.addMessage("No es posible generar un detalle de reserva con los datos vacíos");
	        } else {
	            result.addMessages(IdentificadorValidation.validate(detalleReservaDTO.getIdentificador()).getMessages());
	            result.addMessages(DiaSemanalValidation.validate(detalleReservaDTO.getDia()).getMessages());
	            result.addMessages(HoraInicioValidation.validate(detalleReservaDTO.getHoraInicio()).getMessages());
	            result.addMessages(HoraFinValidation.validate(detalleReservaDTO.getHoraFin()).getMessages());
	        }
		}
		return result;
	}

}
