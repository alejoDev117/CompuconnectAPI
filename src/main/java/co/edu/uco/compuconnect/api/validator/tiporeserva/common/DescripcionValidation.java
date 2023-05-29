package co.edu.uco.compuconnect.api.validator.tiporeserva.common;

import co.edu.uco.compuconnect.api.validator.Result;
import co.edu.uco.compuconnect.api.validator.Validation;
import co.edu.uco.compuconnect.crosscutting.utils.UtilText;

public class DescripcionValidation implements Validation<String> {

    private DescripcionValidation() {
        super();
    }

    public static Result validate(String data) {
        return new DescripcionValidation().execute(data);
    }

    @Override
    public Result execute(String data) {
        Result result = Result.create();

        if (UtilText.getUtilText().isNull(data)) {
            result.addMessage("La descripción del tipo reserva no puede estar vacía");
        }

        return result;
    }
}
