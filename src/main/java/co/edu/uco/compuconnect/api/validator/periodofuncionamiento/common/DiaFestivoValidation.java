package co.edu.uco.compuconnect.api.validator.periodofuncionamiento.common;

import co.edu.uco.compuconnect.api.validator.Result;
import co.edu.uco.compuconnect.api.validator.Validation;
import co.edu.uco.compuconnect.crosscutting.utils.UtilObject;
import co.edu.uco.compuconnect.crosscutting.utils.UtilText;
import co.edu.uco.compuconnect.dto.DiaFestivoDTO;

public class DiaFestivoValidation implements Validation<DiaFestivoDTO> {
	
	private DiaFestivoValidation() {
		super();
	}
	
	public static final Result validate(final DiaFestivoDTO data) {
		return new DiaFestivoValidation().execute(data);
	}
	
	@Override
    public final Result execute(DiaFestivoDTO data) {
        var result = Result.create();

        if (UtilObject.isNull(data)) {
            result.addMessage("No es posible continuar con el dia festivo vacío");
        } else {
            if (UtilObject.isNull(data.getIdentificador())) {
                result.addMessage("No es posible continuar con el identificador del dia festivo vacío");
            }
            if (UtilText.getUtilText().isNull(data.getNombre())) {
                result.addMessage("El nombre del dia festivo no puede estar vacío");
            }
            if (UtilObject.isNull(data.getFecha())) {
                result.addMessage("La fecha del dia festivo no puede estar vacía");
            }
        }

        return result;
    }


}
