package co.edu.uco.compuconnect.api.validator.centroinformatica.common;

import co.edu.uco.compuconnect.api.validator.Result;
import co.edu.uco.compuconnect.api.validator.Validation;

public class PoseeVideoBeamValidation implements Validation<Boolean> {

    private PoseeVideoBeamValidation() {
        super();
    }

    public static Result validate(Boolean data) {
        return new PoseeVideoBeamValidation().execute(data);
    }

    @Override
    public Result execute(Boolean data) {
        Result result = Result.create();

        if (data == null) {
            result.addMessage("La propiedad 'poseeVideoBeam' del centro informático no puede ser nula");
        }

        return result;
    }
}