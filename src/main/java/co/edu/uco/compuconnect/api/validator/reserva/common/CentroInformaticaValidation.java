package co.edu.uco.compuconnect.api.validator.reserva.common;

import co.edu.uco.compuconnect.api.validator.Result;
import co.edu.uco.compuconnect.api.validator.Validation;
import co.edu.uco.compuconnect.crosscutting.utils.UtilObject;
import co.edu.uco.compuconnect.crosscutting.utils.UtilText;
import co.edu.uco.compuconnect.dto.CentroInformaticaDTO;

public class CentroInformaticaValidation implements Validation<CentroInformaticaDTO> {

    private CentroInformaticaValidation() {
        super();
    }

    public static Result validate(CentroInformaticaDTO data) {
        return new CentroInformaticaValidation().execute(data);
    }

    @Override
    public Result execute(CentroInformaticaDTO data) {
        Result result = Result.create();

        if (UtilObject.isNull(data)) {
            result.addMessage("No es posible continuar con el centro de informática vacío");
        } else {
            if (UtilObject.isNull(data.getIdentificador())) {
                result.addMessage("No es posible continuar con el identificador del centro de informática vacío");
            }
            if (UtilText.getUtilText().isNull(data.getNombre())) {
                result.addMessage("El nombre del centro de informática no puede estar vacío");
            }
            if (UtilText.getUtilText().isNull(data.getUbicacion())) {
                result.addMessage("La ubicación del centro de informática no puede estar vacía");
            }
        }

        return result;
    }
}