package co.edu.uco.compuconnect.api.validator.agendareserva.common;

import co.edu.uco.compuconnect.api.validator.Result;
import co.edu.uco.compuconnect.api.validator.Validation;
import co.edu.uco.compuconnect.crosscutting.utils.UtilObject;
import co.edu.uco.compuconnect.crosscutting.utils.UtilText;
import co.edu.uco.compuconnect.dto.ReservaDTO;

public class ReservaValidation implements Validation<ReservaDTO> {
	
	private ReservaValidation() {
		super();
	}
	
	public static final Result validate(final ReservaDTO data) {
		return new ReservaValidation().execute(data);
	}
	
	@Override
    public final Result execute(ReservaDTO data) {
        var result = Result.create();

        if (UtilObject.isNull(data)) {
            result.addMessage("No es posible continuar con la reserva vacía");
        } else {
            if (UtilObject.isNull(data.getIdentificador())) {
                result.addMessage("No es posible continuar con el identificador de la reserva vacío");
            }
            if (UtilObject.isNull(data.getAutor())) {
                result.addMessage("El autor de la reserva no puede estar vacío");
            }
            if (UtilObject.isNull(data.getTipoReserva())) {
                result.addMessage("El tipo de reserva no puede estar vacío");
            }
            if (UtilObject.isNull(data.getFechaInicio())) {
                result.addMessage("La fecha de inicio de la reserva no puede estar vacía");
            }
            if (UtilObject.isNull(data.getFechaFin())) {
                result.addMessage("La fecha de fin de la reserva no puede estar vacía");
            }
            if (UtilObject.isNull(data.getFrecuencia())) {
                result.addMessage("La frecuencia de la reserva no puede estar vacía");
            }
            if (UtilObject.isNull(data.getAgenda())) {
                result.addMessage("La agenda de la reserva no puede estar vacío");
            }
            if (UtilText.getUtilText().isNull(data.getDescripcion())) {
                result.addMessage("La descripción de la reserva no puede estar vacía");
            }
            if (UtilObject.isNull(data.getHoraCreacion())) {
                result.addMessage("La hora de creación de la reserva no puede estar vacía");
            }
            if (UtilObject.isNull(data.getDetalle())) {
                result.addMessage("El detalle de la reserva no puede estar vacía");
            }
        }

        return result;
    }
}

