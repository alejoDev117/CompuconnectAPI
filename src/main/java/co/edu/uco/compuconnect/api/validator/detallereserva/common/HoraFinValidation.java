package co.edu.uco.compuconnect.api.validator.detallereserva.common;

import java.time.LocalTime;

import co.edu.uco.compuconnect.api.validator.Result;
import co.edu.uco.compuconnect.api.validator.Validation;
import co.edu.uco.compuconnect.crosscutting.utils.UtilObject;

public class HoraFinValidation implements Validation<LocalTime> {

    private HoraFinValidation() {
        super();
    }

    public static Result validate(final LocalTime data) {
        return new HoraFinValidation().execute(data);
    }

    @Override
    public Result execute(final LocalTime data) {
        var result = Result.create();

        if (UtilObject.isNull(data)) {
            result.addMessage("No es posible continuar con la hora fin vacía");
        }

        return result;
    }
}
