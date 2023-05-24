package co.edu.uco.compuconnect.api.validator.agendareserva;

import java.util.UUID;

import co.edu.uco.compuconnect.api.validator.Result;
import co.edu.uco.compuconnect.api.validator.Validation;
import co.edu.uco.compuconnect.crosscutting.utils.UtilObject;

public final class EliminarAgendaReservaValidation implements Validation<UUID> {

    private EliminarAgendaReservaValidation() {
        super();
    }

    public static Result validate(final UUID id) {
        return new EliminarAgendaReservaValidation().execute(id);
    }

    @Override
    public Result execute(final UUID id) {
        var result = Result.create();

        if (UtilObject.isNull(id)) {
            result.addMessage("No es posible eliminar la agenda reserva con el identificador vacío");
        }

        return result;
    }
}