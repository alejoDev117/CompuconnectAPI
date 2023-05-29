package co.edu.uco.compuconnect.api.validator.agendareserva.common;

import co.edu.uco.compuconnect.api.validator.Result;
import co.edu.uco.compuconnect.api.validator.Validation;
import co.edu.uco.compuconnect.crosscutting.utils.UtilObject;
import co.edu.uco.compuconnect.crosscutting.utils.UtilText;
import co.edu.uco.compuconnect.dto.AgendaDTO;

public class AgendaValidation implements Validation<AgendaDTO> {
	
	private AgendaValidation() {
		super();
	}
	
	public static final Result validate(final AgendaDTO data) {
		return new AgendaValidation().execute(data);
	}
	
	@Override
    public final Result execute(AgendaDTO data) {
        var result = Result.create();

        if (UtilObject.isNull(data)) {
            result.addMessage("No es posible continuar con la agenda vacía");
        } else {
            if (UtilObject.isNull(data.getIdentificador())) {
                result.addMessage("No es posible continuar con el identificador de la agenda vacío");
            }
            if (UtilObject.isNull(data.getPeriodoFuncionamiento())) {
                result.addMessage("El periodo de funcionamiento de la agenda no puede estar vacío");
            }
            if (UtilObject.isNull(data.getCentroInformatica())) {
                result.addMessage("El centro de informática de la agenda no puede estar vacío");
            }
            if (UtilText.getUtilText().isNull(data.getNombre())) {
                result.addMessage("El nombre de la agenda no puede estar vacío");
            }
        }

        return result;
    }
}

