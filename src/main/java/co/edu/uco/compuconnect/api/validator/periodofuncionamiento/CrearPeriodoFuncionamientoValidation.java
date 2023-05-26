package co.edu.uco.compuconnect.api.validator.periodofuncionamiento;

import co.edu.uco.compuconnect.api.validator.Result;
import co.edu.uco.compuconnect.api.validator.Validation;
import co.edu.uco.compuconnect.api.validator.generalcommon.IdentificadorValidation;
import co.edu.uco.compuconnect.api.validator.periodofuncionamiento.common.DiaFestivoValidation;
import co.edu.uco.compuconnect.api.validator.periodofuncionamiento.common.EstadoPeriodoFuncionamientoValidation;
import co.edu.uco.compuconnect.api.validator.periodofuncionamiento.common.FechaFinValidation;
import co.edu.uco.compuconnect.api.validator.periodofuncionamiento.common.FechaInicioValidation;
import co.edu.uco.compuconnect.api.validator.periodofuncionamiento.common.NombreValidation;
import co.edu.uco.compuconnect.crosscutting.utils.UtilObject;
import co.edu.uco.compuconnect.dto.PeriodoFuncionamientoDTO;

public class CrearPeriodoFuncionamientoValidation implements Validation<PeriodoFuncionamientoDTO> {
	
	private CrearPeriodoFuncionamientoValidation() {
		super();
	}
	
	public static Result validate(final PeriodoFuncionamientoDTO data) {
		return new CrearPeriodoFuncionamientoValidation().execute(data);
	}
	
	@Override
    public Result execute(final PeriodoFuncionamientoDTO data) {
        var result = Result.create();

        if (UtilObject.isNull(data)) {
            result.addMessage("No es posible generar un nuevo periodo funcionamiento con los datos vacíos");
        } else {
            result.addMessages(IdentificadorValidation.validate(data.getIdentificador()).getMessages());
            result.addMessages(DiaFestivoValidation.validate(data.getDiaFestivo()).getMessages());
            result.addMessages(EstadoPeriodoFuncionamientoValidation.validate(data.getEstado()).getMessages());
            result.addMessages(FechaInicioValidation.validate(data.getFechaInicio()).getMessages());
            result.addMessages(FechaFinValidation.validate(data.getFechaFin()).getMessages());
            result.addMessages(NombreValidation.validate(data.getNombre()).getMessages());
        }

        return result;
    }
}
