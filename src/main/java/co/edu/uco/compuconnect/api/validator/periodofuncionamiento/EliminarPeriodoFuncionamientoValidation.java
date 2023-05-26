package co.edu.uco.compuconnect.api.validator.periodofuncionamiento;

import java.util.UUID;

import co.edu.uco.compuconnect.api.validator.Result;
import co.edu.uco.compuconnect.api.validator.Validation;
import co.edu.uco.compuconnect.crosscutting.utils.UtilObject;

public final class EliminarPeriodoFuncionamientoValidation implements Validation<UUID> {

    private EliminarPeriodoFuncionamientoValidation() {
        super();
    }

    public static Result validate(final UUID id) {
        return new EliminarPeriodoFuncionamientoValidation().execute(id);
    }

    @Override
    public Result execute(final UUID id) {
        var result = Result.create();

        if (UtilObject.isNull(id)) {
            result.addMessage("No es posible eliminar el periodo funcionamiento con el identificador vacío");
        }

        return result;
    }
}
