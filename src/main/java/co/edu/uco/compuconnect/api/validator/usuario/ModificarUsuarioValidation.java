package co.edu.uco.compuconnect.api.validator.usuario;

import co.edu.uco.compuconnect.api.validator.Result;
import co.edu.uco.compuconnect.api.validator.Validation;
import co.edu.uco.compuconnect.api.validator.generalcommon.IdentificadorValidation;
import co.edu.uco.compuconnect.api.validator.usuario.common.CorreoInstitucionalValidation;
import co.edu.uco.compuconnect.api.validator.usuario.common.IdentificacionValidation;
import co.edu.uco.compuconnect.api.validator.usuario.common.NombreValidation;
import co.edu.uco.compuconnect.api.validator.usuario.common.TipoIdentificacionValidation;
import co.edu.uco.compuconnect.api.validator.usuario.common.TipoUsuarioValidation;
import co.edu.uco.compuconnect.crosscutting.utils.UtilObject;
import co.edu.uco.compuconnect.dto.UsuarioDTO;

public final class ModificarUsuarioValidation implements Validation<UsuarioDTO> {

    private ModificarUsuarioValidation() {
        super();
    }

    public static Result validate(final UsuarioDTO data) {
        return new ModificarUsuarioValidation().execute(data);
    }

    @Override
    public Result execute(final UsuarioDTO data) {
        var result = Result.create();

        if (UtilObject.isNull(data)) {
            result.addMessage("No es posible modificar un usuario con los datos vacíos");
        } else {
            result.addMessages(IdentificadorValidation.validate(data.getIdentificador()).getMessages());
            result.addMessages(TipoUsuarioValidation.validate(data.getTipoUsuario()).getMessages());
            result.addMessages(NombreValidation.validate(data.getNombre()).getMessages());
            result.addMessages(TipoIdentificacionValidation.validate(data.getTipoIdentificacion()).getMessages());
            result.addMessages(IdentificacionValidation.validate(data.getIdentificacion()).getMessages());
            result.addMessages(CorreoInstitucionalValidation.validate(data.getCorreoInstitucional()).getMessages());
        }

        return result;
    }
}