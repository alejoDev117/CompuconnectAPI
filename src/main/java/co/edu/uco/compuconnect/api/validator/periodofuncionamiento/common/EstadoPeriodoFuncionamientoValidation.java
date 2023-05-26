package co.edu.uco.compuconnect.api.validator.periodofuncionamiento.common;

import co.edu.uco.compuconnect.api.validator.Result;
import co.edu.uco.compuconnect.api.validator.Validation;
import co.edu.uco.compuconnect.crosscutting.utils.UtilObject;
import co.edu.uco.compuconnect.crosscutting.utils.UtilText;
import co.edu.uco.compuconnect.dto.EstadoPeriodoFuncionamientoDTO;

public class EstadoPeriodoFuncionamientoValidation implements Validation<EstadoPeriodoFuncionamientoDTO> {
	
	private EstadoPeriodoFuncionamientoValidation() {
		super();
	}
	
	public static final Result validate(final EstadoPeriodoFuncionamientoDTO data) {
		return new EstadoPeriodoFuncionamientoValidation().execute(data);
	}
	
	@Override
    public final Result execute(EstadoPeriodoFuncionamientoDTO data) {
        var result = Result.create();

        if (UtilObject.isNull(data)) {
            result.addMessage("No es posible continuar con el estado periodo funcionamiento vacío");
        } else {
            if (UtilObject.isNull(data.getIdentificador())) {
                result.addMessage("No es posible continuar con el identificador del estado periodo funcionamiento vacío");
            }
            if (UtilText.isNull(data.getNombre())) {
                result.addMessage("El nombre del estado periodo funcionamiento no puede estar vacío");
            }
            if (UtilObject.isNull(data.getDescripcion())) {
                result.addMessage("La descripcion del estado periodo funcionamiento no puede estar vacía");
            }
        }

        return result;
    }


}
