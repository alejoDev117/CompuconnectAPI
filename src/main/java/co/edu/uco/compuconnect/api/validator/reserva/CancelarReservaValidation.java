package co.edu.uco.compuconnect.api.validator.reserva;

import co.edu.uco.compuconnect.api.validator.Result;
import co.edu.uco.compuconnect.api.validator.Validation;
import co.edu.uco.compuconnect.api.validator.reserva.common.AutorValidation;
import co.edu.uco.compuconnect.api.validator.reserva.common.IdentificadorValidation;
import co.edu.uco.compuconnect.crosscutting.utils.UtilObject;
import co.edu.uco.compuconnect.dto.ReservaDTO;

public final class CancelarReservaValidation implements Validation<ReservaDTO> {

    private CancelarReservaValidation() {
        super();
    }

    public static Result validate(final ReservaDTO data) {
        return new CancelarReservaValidation().execute(data);
    }

    @Override
    public Result execute(final ReservaDTO data) {
        var result = Result.create();

        if (UtilObject.isNull(data)) {
            result.addMessage("No es posible cancelar la reserva con los datos vacíos");
        } else {
            result.addMessages(IdentificadorValidation.validate(data.getIdentificador()).getMessages());
            result.addMessages(AutorValidation.validate(data.getAutor()).getMessages());
        }

        return result;
    }
}