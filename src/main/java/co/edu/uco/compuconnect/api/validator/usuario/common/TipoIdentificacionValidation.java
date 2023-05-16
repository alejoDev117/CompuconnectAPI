package co.edu.uco.compuconnect.api.validator.usuario.common;

import co.edu.uco.compuconnect.api.validator.Result;
import co.edu.uco.compuconnect.api.validator.Validation;
import co.edu.uco.compuconnect.crosscutting.utils.UtilObject;
import co.edu.uco.compuconnect.dto.TipoIdentificacionDTO;

public class TipoIdentificacionValidation implements Validation<TipoIdentificacionDTO> {

    private TipoIdentificacionValidation() {
        super();
    }

    public static final Result validate(final TipoIdentificacionDTO data) {
        return new TipoIdentificacionValidation().execute(data);
    }

    @Override
    public final Result execute(TipoIdentificacionDTO data) {
        var result = Result.create();

        if (UtilObject.isNull(data)) {
            result.addMessage("No es posible continuar con el tipo de identificación vacío");
        } else if (UtilObject.isNull(data)) {
            result.addMessage("No es posible tener el tipo de identificación por defecto vacío");
        }

        return result;
    }
}