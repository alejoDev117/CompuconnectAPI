package co.edu.uco.compuconnect.api.validator.usuario.common;

import co.edu.uco.compuconnect.api.validator.Result;
import co.edu.uco.compuconnect.api.validator.Validation;
import co.edu.uco.compuconnect.crosscutting.utils.UtilText;

public class CorreoInstitucionalValidation implements Validation<String> {

    private CorreoInstitucionalValidation() {
        super();
    }

    public static final Result validate(final String data) {
        return new CorreoInstitucionalValidation().execute(data);
    }

    @Override
    public Result execute(String data) {
        Result result = Result.create();

        if (UtilText.getUtilText().isNull(data)) {
            result.addMessage("El correo institucional del usuario no puede estar vacío");
        }

        return result;
    }
}