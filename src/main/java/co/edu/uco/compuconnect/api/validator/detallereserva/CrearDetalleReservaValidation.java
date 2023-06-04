package co.edu.uco.compuconnect.api.validator.detallereserva;

import co.edu.uco.compuconnect.api.validator.Result;
import co.edu.uco.compuconnect.api.validator.Validation;
import co.edu.uco.compuconnect.api.validator.detallereserva.common.DiaSemanalValidation;
import co.edu.uco.compuconnect.api.validator.detallereserva.common.HoraFinValidation;
import co.edu.uco.compuconnect.api.validator.detallereserva.common.HoraInicioValidation;
import co.edu.uco.compuconnect.api.validator.generalcommon.IdentificadorValidation;
import co.edu.uco.compuconnect.crosscutting.utils.UtilObject;
import co.edu.uco.compuconnect.dto.DetalleReservaDTO;

public class CrearDetalleReservaValidation implements Validation<DetalleReservaDTO> {
	
	private CrearDetalleReservaValidation() {
		super();
	}
	
	public static Result validate(final DetalleReservaDTO data) {
		return new CrearDetalleReservaValidation().execute(data);
	}
	
	@Override
    public Result execute(final DetalleReservaDTO data) {
        var result = Result.create();

        if (UtilObject.isNull(data)) {
            result.addMessage("No es posible generar un detalle de reserva con los datos vacíos");
        } else {
            result.addMessages(IdentificadorValidation.validate(data.getIdentificador()).getMessages());
            result.addMessages(DiaSemanalValidation.validate(data.getDia()).getMessages());
            result.addMessages(HoraInicioValidation.validate(data.getHoraInicio()).getMessages());
            result.addMessages(HoraFinValidation.validate(data.getHoraFin()).getMessages());
        }

        return result;
    }
}
