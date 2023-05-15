package co.edu.uco.compuconnect.api.validator.reserva.common;

import java.util.Date;

import co.edu.uco.compuconnect.api.validator.Result;
import co.edu.uco.compuconnect.api.validator.Validation;
import co.edu.uco.compuconnect.crosscutting.utils.UtilObject;

public class FechaInicioValidation implements Validation<Date> {

    private FechaInicioValidation() {
        super();
    }

    public static Result validate(Date data) {
        return new FechaInicioValidation().execute(data);
    }

    @Override
    public Result execute(Date data) {
        Result result = Result.create();

        if (UtilObject.isNull(data)) {
            result.addMessage("La fecha de inicio de la reserva no puede estar vacía");
        } else {
            Date currentDate = new Date();
            if (data.before(currentDate)) {
                result.addMessage("La fecha de inicio de la reserva no puede ser anterior a la fecha actual");
            }
        }

        return result;
    }
}