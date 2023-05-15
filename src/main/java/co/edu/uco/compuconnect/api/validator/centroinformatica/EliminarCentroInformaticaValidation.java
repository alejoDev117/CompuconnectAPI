package co.edu.uco.compuconnect.api.validator.centroinformatica;

import java.util.UUID;

import co.edu.uco.compuconnect.api.validator.Result;
import co.edu.uco.compuconnect.api.validator.Validation;
import co.edu.uco.compuconnect.crosscutting.utils.UtilObject;

public final class EliminarCentroInformaticaValidation implements Validation<UUID> {

    private EliminarCentroInformaticaValidation() {
        super();
    }

    public static Result validate(final UUID id) {
        return new EliminarCentroInformaticaValidation().execute(id);
    }

    @Override
    public Result execute(final UUID id) {
        var result = Result.create();

        if (UtilObject.isNull(id)) {
            result.addMessage("No es posible eliminar el centro informático con el identificador vacío");
        }

        return result;
    }
}