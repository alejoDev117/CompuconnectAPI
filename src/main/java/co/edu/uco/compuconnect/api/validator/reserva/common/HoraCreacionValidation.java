package co.edu.uco.compuconnect.api.validator.reserva.common;

import java.util.Date;

import co.edu.uco.compuconnect.api.validator.Result;
import co.edu.uco.compuconnect.api.validator.Validation;
import co.edu.uco.compuconnect.crosscutting.utils.UtilObject;

public class HoraCreacionValidation implements Validation<Date> {

    private HoraCreacionValidation() {
        super();
    }

    public static Result validate(Date data) {
        return new HoraCreacionValidation().execute(data);
    }

    @Override
    public Result execute(Date data) {
        Result result = Result.create();

        if (UtilObject.isNull(data)) {
            result.addMessage("La hora de creación no puede estar vacía");
        }

        return result;
      
    }
}