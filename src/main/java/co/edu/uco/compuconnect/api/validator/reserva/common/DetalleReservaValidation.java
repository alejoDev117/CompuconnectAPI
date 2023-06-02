package co.edu.uco.compuconnect.api.validator.reserva.common;

import co.edu.uco.compuconnect.api.validator.Result;
import co.edu.uco.compuconnect.api.validator.Validation;
import co.edu.uco.compuconnect.crosscutting.utils.UtilObject;
import co.edu.uco.compuconnect.dto.DetalleReservaDTO;

public class DetalleReservaValidation implements Validation<DetalleReservaDTO> {

    private DetalleReservaValidation() {
        super();
    }

    public static Result validate(DetalleReservaDTO data) {
        return new DetalleReservaValidation().execute(data);
    }

    @Override
    public Result execute(DetalleReservaDTO data) {
        Result result = Result.create();

        if (UtilObject.isNull(data)) {
            result.addMessage("No es posible continuar con el detalle de reserva vacío");
        } else {
            if (UtilObject.isNull(data.getIdentificador())) {
                result.addMessage("No es posible continuar con el identificador del detalle de reserva vacío");
            }
            if (UtilObject.isNull(data.getDia())) {
                result.addMessage("No es posible continuar con el día del detalle de reserva vacío");
            }
            if (UtilObject.isNull(data.getHoraInicio())) {
                result.addMessage("No es posible continuar con la hora de inicio del detalle de reserva vacía");
            }
            if (UtilObject.isNull(data.getHorFin())) {
                result.addMessage("No es posible continuar con la hora de fin del detalle de reserva vacía");
            }
        }

        return result;
    }
}
