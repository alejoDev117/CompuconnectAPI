package co.edu.uco.compuconnect.api.validator.detallereserva.common;

import java.time.LocalTime;

import co.edu.uco.compuconnect.api.validator.Result;
import co.edu.uco.compuconnect.api.validator.Validation;
import co.edu.uco.compuconnect.crosscutting.utils.UtilObject;


public class HoraInicioValidation implements Validation<LocalTime> {

    private HoraInicioValidation() {
        super();
    }

    public static Result validate(final LocalTime data) {
        return new HoraInicioValidation().execute(data);
    }

    @Override
    public Result execute(final LocalTime data) {
        var result = Result.create();

        if (UtilObject.isNull(data)) {
            result.addMessage("No es posible continuar con la hora de inicio vacía");
        }

        return result;
    }
}

