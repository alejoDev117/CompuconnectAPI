package co.edu.uco.compuconnect.api.validator.reserva.common;

import java.util.Date;

import co.edu.uco.compuconnect.api.validator.Result;
import co.edu.uco.compuconnect.api.validator.Validation;
import co.edu.uco.compuconnect.crosscutting.utils.UtilObject;

public class FechaFinValidation implements Validation<Date> {

    private FechaFinValidation() {
        super();
    }

    public static Result validate(Date data) {
        return new FechaFinValidation().execute(data);
    }

    @Override
    public Result execute(Date data) {
        Result result = Result.create();

        if (UtilObject.isNull(data)) {
            result.addMessage("La fecha de fin de la reserva no puede estar vacía");
        }

        return result;
    }
}