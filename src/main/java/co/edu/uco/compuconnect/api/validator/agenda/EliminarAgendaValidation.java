package co.edu.uco.compuconnect.api.validator.agenda;

import java.util.UUID;

import co.edu.uco.compuconnect.api.validator.Result;
import co.edu.uco.compuconnect.api.validator.Validation;
import co.edu.uco.compuconnect.crosscutting.utils.UtilObject;

public final class EliminarAgendaValidation implements Validation<UUID> {

    private EliminarAgendaValidation() {
        super();
    }

    public static Result validate(final UUID id) {
        return new EliminarAgendaValidation().execute(id);
    }

    @Override
    public Result execute(final UUID id) {
        var result = Result.create();

        if (UtilObject.isNull(id)) {
            result.addMessage("No es posible eliminar la agenda con el identificador vacío");
        }

        return result;
    }
}