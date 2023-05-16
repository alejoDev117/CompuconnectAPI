package co.edu.uco.compuconnect.api.validator.tiporeserva;

import co.edu.uco.compuconnect.api.validator.Result;
import co.edu.uco.compuconnect.api.validator.Validation;
import co.edu.uco.compuconnect.api.validator.tiporeserva.common.DescripcionValidation;
import co.edu.uco.compuconnect.api.validator.tiporeserva.common.IdentificadorValidation;
import co.edu.uco.compuconnect.api.validator.tiporeserva.common.NombreValidation;
import co.edu.uco.compuconnect.crosscutting.utils.UtilObject;
import co.edu.uco.compuconnect.dto.TipoReservaDTO;

public final class ConsultarTipoReservaValidation implements Validation<TipoReservaDTO> {

    private ConsultarTipoReservaValidation() {
        super();
    }

    public static Result validate(final TipoReservaDTO data) {
        return new ConsultarTipoReservaValidation().execute(data);
    }

    @Override
    public Result execute(final TipoReservaDTO data) {
        var result = Result.create();

        if (UtilObject.isNull(data)) {
            result.addMessage("No es posible consultar un tipo de reserva con los datos vacíos");
        } else {
            result.addMessages(IdentificadorValidation.validate(data.getIdentificador()).getMessages());
            result.addMessages(NombreValidation.validate(data.getNombre()).getMessages());
            result.addMessages(DescripcionValidation.validate(data.getDescripcion()).getMessages());
        }

        return result;
    }
}
