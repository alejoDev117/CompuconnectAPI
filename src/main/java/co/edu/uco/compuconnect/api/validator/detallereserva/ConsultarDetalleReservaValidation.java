package co.edu.uco.compuconnect.api.validator.detallereserva;

import java.util.UUID;

import co.edu.uco.compuconnect.api.validator.Result;
import co.edu.uco.compuconnect.api.validator.Validation;
import co.edu.uco.compuconnect.crosscutting.utils.UtilObject;

public final class ConsultarDetalleReservaValidation implements Validation<UUID> {

    private ConsultarDetalleReservaValidation() {
        super();
    }

    public static Result validate(final UUID id) {
        return new ConsultarDetalleReservaValidation().execute(id);
    }

    @Override
    public Result execute(final UUID id) {
        var result = Result.create();

        if (UtilObject.isNull(id)) {
            result.addMessage("No es posible consultar el detalle de reserva con el identificador vacío");
        }

        return result;
    }
}

