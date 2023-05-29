package co.edu.uco.compuconnect.api.validator.detallereserva.common;

import co.edu.uco.compuconnect.api.validator.Result;
import co.edu.uco.compuconnect.api.validator.Validation;
import co.edu.uco.compuconnect.crosscutting.utils.UtilObject;
import co.edu.uco.compuconnect.crosscutting.utils.UtilText;
import co.edu.uco.compuconnect.dto.DiaSemanalDTO;

public class DiaSemanalValidation implements Validation<DiaSemanalDTO> {
	
	private DiaSemanalValidation() {
		super();
	}
	
	public static Result validate(final DiaSemanalDTO data) {
		return new DiaSemanalValidation().execute(data);
	}
	
	@Override
    public Result execute(DiaSemanalDTO data) {
        var result = Result.create();

        if (UtilObject.isNull(data)) {
            result.addMessage("No es posible continuar con el día semanal vacío");
        } else {
            if (UtilObject.isNull(data.getIdentificador())) {
                result.addMessage("No es posible continuar con el identificador del día semanal vacío");
            }
            if (UtilText.getUtilText().isNull(data.getNombre())) {
                result.addMessage("El nombre del día semanal no puede estar vacío");
            }
        }

        return result;
    }
}
