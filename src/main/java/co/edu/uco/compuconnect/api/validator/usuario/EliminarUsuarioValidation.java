package co.edu.uco.compuconnect.api.validator.usuario;

import java.util.UUID;

import co.edu.uco.compuconnect.api.validator.Result;
import co.edu.uco.compuconnect.api.validator.Validation;
import co.edu.uco.compuconnect.crosscutting.utils.UtilObject;

public final class EliminarUsuarioValidation implements Validation<UUID> {

    private EliminarUsuarioValidation() {
        super();
    }

    public static Result validate(final UUID id) {
        return new EliminarUsuarioValidation().execute(id);
    }

    @Override
    public Result execute(final UUID id) {
        var result = Result.create();

        if (UtilObject.isNull(id)) {
            result.addMessage("No es posible eliminar el usuario con el identificador vacío");
        }

        return result;
    }
}