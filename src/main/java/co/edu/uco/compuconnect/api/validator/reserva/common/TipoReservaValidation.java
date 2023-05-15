package co.edu.uco.compuconnect.api.validator.reserva.common;

import java.util.Date;
import java.util.UUID;

import co.edu.uco.compuconnect.api.validator.Result;
import co.edu.uco.compuconnect.api.validator.Validation;
import co.edu.uco.compuconnect.crosscutting.utils.UtilObject;
import co.edu.uco.compuconnect.crosscutting.utils.UtilText;
import co.edu.uco.compuconnect.crosscutting.utils.UtilUUID;
import co.edu.uco.compuconnect.dto.TipoReservaDTO;

public class TipoReservaValidation implements Validation<TipoReservaDTO> {

    private TipoReservaValidation() {
        super();
    }

    public static Result validate(TipoReservaDTO data) {
        return new TipoReservaValidation().execute(data);
    }

    @Override
    public Result execute(TipoReservaDTO data) {
        Result result = Result.create();

        if (UtilObject.isNull(data)) {
            result.addMessage("No es posible continuar con el tipo de reserva vacío");
        } else {
            if (UtilObject.isNull(data.getIdentificador())) {
                result.addMessage("No es posible continuar con el identificador del tipo reserva vacío");
            }
            if (UtilText.isNull(data.getNombre())) {
                result.addMessage("El nombre del tipo de reserva no puede estar vacío");
            }
            if (UtilText.isNull(data.getDescripcion())) {
                result.addMessage("La descripción del tipo de reserva no puede estar vacía");
            }
        }

        return result;
    }
}
