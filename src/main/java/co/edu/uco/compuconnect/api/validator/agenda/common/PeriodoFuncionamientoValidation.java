package co.edu.uco.compuconnect.api.validator.agenda.common;

import co.edu.uco.compuconnect.api.validator.Result;
import co.edu.uco.compuconnect.api.validator.Validation;
import co.edu.uco.compuconnect.crosscutting.utils.UtilObject;
import co.edu.uco.compuconnect.dto.CentroInformaticaDTO;
import co.edu.uco.compuconnect.dto.PeriodoFuncionamientoDTO;

public class PeriodoFuncionamientoValidation implements Validation<PeriodoFuncionamientoDTO>{

	
	private PeriodoFuncionamientoValidation() {
		super();
	}
	
	public static final Result validate(final PeriodoFuncionamientoDTO data) {
		return new PeriodoFuncionamientoValidation().execute(data);
	}
	
	@Override
	public final Result execute(PeriodoFuncionamientoDTO data) {
		var result = Result.create();
		if (UtilObject.isNull(data)) {
            result.addMessage("No es posible continuar con el periodo funcionamiento vacío");
        } else if (UtilObject.isNull(data)) {
            result.addMessage("No es posible tener el periodo funcionamiento por defecto vacío");
        }

        return result;
	}
	

}
