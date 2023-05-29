package co.edu.uco.compuconnect.api.validator.centroinformatica.common;

import co.edu.uco.compuconnect.api.validator.Result;
import co.edu.uco.compuconnect.api.validator.Validation;
import co.edu.uco.compuconnect.crosscutting.utils.UtilText;

public class UbicacionValidation implements Validation<String> {

    private UbicacionValidation() {
        super();
    }

    public static Result validate(String data) {
        return new UbicacionValidation().execute(data);
    }

    @Override
    public Result execute(String data) {
        Result result = Result.create();

        if (UtilText.getUtilText().isNull(data)) {
            result.addMessage("La ubicación del centro informático no puede estar vacía");
        }

        return result;
    }
}