package co.edu.uco.compuconnect.api.validator.diasemanal;

import java.util.UUID;

import co.edu.uco.compuconnect.api.validator.Result;
import co.edu.uco.compuconnect.api.validator.Validation;
import co.edu.uco.compuconnect.crosscutting.utils.UtilObject;

public final class ConsultarDiaSemanalValidation implements Validation<UUID> {

    private ConsultarDiaSemanalValidation() {
        super();
    }

    public static Result validate(final UUID identificador) {
        return new ConsultarDiaSemanalValidation().execute(identificador);
    }
    
    
    @Override
    public Result execute(final UUID identificador) {
        var result = Result.create();

        if (UtilObject.isNull(identificador)) {
            result.addMessage("No es posible consultar el detalle del día semanal con el identificador vacío");
        }

        return result;
}}