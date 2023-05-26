package co.edu.uco.compuconnect.api.validator.estadoperiodofuncionamiento;

import java.util.UUID;

import co.edu.uco.compuconnect.api.validator.Result;
import co.edu.uco.compuconnect.api.validator.Validation;
import co.edu.uco.compuconnect.crosscutting.utils.UtilObject;

public final class ConsultarEstadoPeriodoFuncionamientoValidation implements Validation<UUID> {

    private ConsultarEstadoPeriodoFuncionamientoValidation() {
        super();
    }

    public static Result validate(final UUID identificador) {
        return new ConsultarEstadoPeriodoFuncionamientoValidation().execute(identificador);
    }

    @Override
    public Result execute(final UUID identificador) {
        var result = Result.create();

        if (UtilObject.isNull(identificador)) {
            result.addMessage("No es posible consultar el detalle del estado periodo funcionamiento con el identificador vacío");
        }
        return result;
    }
}