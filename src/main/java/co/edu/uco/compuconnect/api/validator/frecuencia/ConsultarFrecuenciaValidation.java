package co.edu.uco.compuconnect.api.validator.frecuencia;

import co.edu.uco.compuconnect.api.validator.Result;
import co.edu.uco.compuconnect.api.validator.Validation;
import co.edu.uco.compuconnect.api.validator.frecuencia.common.DescripcionValidation;
import co.edu.uco.compuconnect.api.validator.frecuencia.common.NombreValidation;
import co.edu.uco.compuconnect.api.validator.generalcommon.IdentificadorValidation;
import co.edu.uco.compuconnect.crosscutting.utils.UtilObject;
import co.edu.uco.compuconnect.dto.FrecuenciaDTO;

public final class ConsultarFrecuenciaValidation implements Validation<FrecuenciaDTO> {

    private ConsultarFrecuenciaValidation() {
        super();
    }

    public static Result validate(final FrecuenciaDTO data) {
        return new ConsultarFrecuenciaValidation().execute(data);
    }

    @Override
    public Result execute(final FrecuenciaDTO data) {
        var result = Result.create();

        if (UtilObject.isNull(data)) {
            result.addMessage("No es posible consultar una frecuencia con los datos vacíos");
        } else {
            result.addMessages(IdentificadorValidation.validate(data.getIdentificador()).getMessages());
            result.addMessages(NombreValidation.validate(data.getNombre()).getMessages());
            result.addMessages(DescripcionValidation.validate(data.getDescripcion()).getMessages());
        }

        return result;
    }
}