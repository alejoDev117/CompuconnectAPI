package co.edu.uco.compuconnect.api.validator.tipoidentificacion;

import co.edu.uco.compuconnect.api.validator.Result;
import co.edu.uco.compuconnect.api.validator.Validation;
import co.edu.uco.compuconnect.api.validator.tipousuario.common.DescripcionValidation;
import co.edu.uco.compuconnect.api.validator.tipousuario.common.IdentificadorValidation;
import co.edu.uco.compuconnect.api.validator.tipousuario.common.NombreValidation;
import co.edu.uco.compuconnect.crosscutting.utils.UtilObject;
import co.edu.uco.compuconnect.dto.TipoUsuarioDTO;

public final class ConsultarTipoIdentificacionValidation implements Validation<TipoUsuarioDTO> {

    private ConsultarTipoIdentificacionValidation() {
        super();
    }

    public static Result validate(final TipoUsuarioDTO data) {
        return new ConsultarTipoIdentificacionValidation().execute(data);
    }

    @Override
    public Result execute(final TipoUsuarioDTO data) {
        var result = Result.create();

        if (UtilObject.isNull(data)) {
            result.addMessage("No es posible consultar un tipo de identificacion con los datos vacíos");
        } else {
            result.addMessages(IdentificadorValidation.validate(data.getIdentificador()).getMessages());
            result.addMessages(NombreValidation.validate(data.getNombre()).getMessages());
            result.addMessages(DescripcionValidation.validate(data.getDescripcion()).getMessages());
        }

        return result;
    }
}