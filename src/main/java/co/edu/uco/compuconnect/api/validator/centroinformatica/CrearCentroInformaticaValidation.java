package co.edu.uco.compuconnect.api.validator.centroinformatica;

import co.edu.uco.compuconnect.api.validator.Result;

import co.edu.uco.compuconnect.api.validator.Validation;
import co.edu.uco.compuconnect.api.validator.centroinformatica.common.PoseeVideoBeamValidation;
import co.edu.uco.compuconnect.api.validator.centroinformatica.common.UbicacionValidation;
import co.edu.uco.compuconnect.api.validator.frecuencia.common.NombreValidation;
import co.edu.uco.compuconnect.api.validator.generalcommon.IdentificadorValidation;
import co.edu.uco.compuconnect.crosscutting.utils.UtilObject;
import co.edu.uco.compuconnect.dto.CentroInformaticaDTO;

public final class CrearCentroInformaticaValidation implements Validation<CentroInformaticaDTO> {

    private CrearCentroInformaticaValidation() {
        super();
    }

    public static Result validate(final CentroInformaticaDTO data) {
        return new CrearCentroInformaticaValidation().execute(data);
    }

    @Override
    public Result execute(final CentroInformaticaDTO data) {
        var result = Result.create();

        if (UtilObject.isNull(data)) {
            result.addMessage("No es posible crear un centro informático con los datos vacíos");
        } else {
            result.addMessages(IdentificadorValidation.validate(data.getIdentificador()).getMessages());
            result.addMessages(NombreValidation.validate(data.getNombre()).getMessages());
            result.addMessages(UbicacionValidation.validate(data.getUbicacion()).getMessages());
            result.addMessages(PoseeVideoBeamValidation.validate(data.isPoseeVideoBeam()).getMessages());
        }

        return result;
    }
}
