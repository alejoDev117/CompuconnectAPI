package co.edu.uco.compuconnect.api.validator.usuario.common;

import co.edu.uco.compuconnect.api.validator.Result;
import co.edu.uco.compuconnect.api.validator.Validation;
import co.edu.uco.compuconnect.crosscutting.utils.UtilObject;

public class IdentificacionValidation implements Validation<String> {

    private IdentificacionValidation() {
        super();
    }

    public static final Result validate(final String data) {
        return new IdentificacionValidation().execute(data);
    }

    @Override
    public final Result execute(String data) {
        var result = Result.create();

        if (UtilObject.isNull(data)) {
            result.addMessage("No es posible continuar con el número de identificación vacío");
        }

        return result;
    }
}
