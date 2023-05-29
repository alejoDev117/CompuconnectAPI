package co.edu.uco.compuconnect.api.validator.reserva.common;

import co.edu.uco.compuconnect.api.validator.Result;
import co.edu.uco.compuconnect.api.validator.Validation;
import co.edu.uco.compuconnect.crosscutting.utils.UtilObject;
import co.edu.uco.compuconnect.crosscutting.utils.UtilText;
import co.edu.uco.compuconnect.dto.FrecuenciaDTO;

public class FrecuenciaValidation implements Validation<FrecuenciaDTO> {

    private FrecuenciaValidation() {
        super();
    }

    public static Result validate(FrecuenciaDTO data) {
        return new FrecuenciaValidation().execute(data);
    }

    @Override
    public Result execute(FrecuenciaDTO data) {
        Result result = Result.create();

        if (UtilObject.isNull(data)) {
            result.addMessage("La frecuencia no puede estar vacía");
        } else {
            if (UtilObject.isNull(data.getIdentificador())) {
                result.addMessage("El identificador de la frecuencia no puede estar vacío");
            }
            if (UtilText.getUtilText().isNull(data.getNombre())) {
                result.addMessage("El nombre de la frecuencia no puede estar vacío");
            }
            if (UtilText.getUtilText().isNull(data.getDescripcion())) {
                result.addMessage("La descripción de la frecuencia no puede estar vacía");
            }
        }

        return result;
    }
}