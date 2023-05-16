package co.edu.uco.compuconnect.api.validator.usuario.common;

import co.edu.uco.compuconnect.api.validator.Result;
import co.edu.uco.compuconnect.api.validator.Validation;
import co.edu.uco.compuconnect.crosscutting.utils.UtilObject;
import co.edu.uco.compuconnect.dto.TipoUsuarioDTO;

public class TipoUsuarioValidation implements Validation<TipoUsuarioDTO> {

    private TipoUsuarioValidation() {
        super();
    }

    public static final Result validate(final TipoUsuarioDTO data) {
        return new TipoUsuarioValidation().execute(data);
    }

    @Override
    public final Result execute(TipoUsuarioDTO data) {
        var result = Result.create();

        if (UtilObject.isNull(data)) {
            result.addMessage("No es posible continuar con el tipo de usuario vacío");
        } else if (UtilObject.isNull(data)) {
            result.addMessage("No es posible tener el tipo de usuario por defecto vacío");
        }

        return result;
    }
}