package co.edu.uco.compuconnect.api.validator.agenda.common;

import co.edu.uco.compuconnect.api.validator.Result;
import co.edu.uco.compuconnect.api.validator.Validation;
import co.edu.uco.compuconnect.crosscutting.utils.UtilObject;
import co.edu.uco.compuconnect.crosscutting.utils.UtilText;
import co.edu.uco.compuconnect.dto.CentroInformaticaDTO;

public class CentroInformaticaValidation implements Validation<CentroInformaticaDTO> {
	
	private CentroInformaticaValidation() {
		super();
	}
	
	public static final Result validate(final CentroInformaticaDTO data) {
		return new CentroInformaticaValidation().execute(data);
	}
	
	@Override
    public final Result execute(CentroInformaticaDTO data) {
        var result = Result.create();

        if (UtilObject.isNull(data)) {
            result.addMessage("No es posible continuar con el centro de informática vacío");
        } else {
            if (UtilObject.isNull(data.getIdentificador())) {
                result.addMessage("No es posible continuar con el identificador del centro de informática vacío");
            }
            if (UtilText.isNull(data.getNombre())) {
                result.addMessage("El nombre del centro de informática no puede estar vacío");
            }
            if (UtilText.isNull(data.getUbicacion())) {
                result.addMessage("La ubicación del centro de informática no puede estar vacía");
            }
        }

        return result;
    }

}

