package co.edu.uco.compuconnect.api.validator.agenda;

import co.edu.uco.compuconnect.api.validator.Result;
import co.edu.uco.compuconnect.api.validator.Validation;
import co.edu.uco.compuconnect.api.validator.agenda.common.NombreValidation;
import co.edu.uco.compuconnect.api.validator.agenda.common.PeriodoFuncionamientoValidation;
import co.edu.uco.compuconnect.api.validator.generalcommon.IdentificadorValidation;
import co.edu.uco.compuconnect.api.validator.reserva.common.CentroInformaticaValidation;
import co.edu.uco.compuconnect.crosscutting.utils.UtilObject;
import co.edu.uco.compuconnect.dto.AgendaDTO;

public final class ActualizarAgendaValidation implements Validation<AgendaDTO> {

    private ActualizarAgendaValidation() {
        super();
    }

    public static Result validate(final AgendaDTO data) {
        return new ActualizarAgendaValidation().execute(data);
    }

    @Override
    public Result execute(final AgendaDTO data) {
        var result = Result.create();

        if (UtilObject.isNull(data)) {
            result.addMessage("No es posible modificar la agenda con los datos vacíos");
        } else {
        	result.addMessages(IdentificadorValidation.validate(data.getIdentificador()).getMessages());
        	result.addMessages(PeriodoFuncionamientoValidation.validate(data.getPeriodoFuncionamiento()).getMessages());
        	result.addMessages(CentroInformaticaValidation.validate(data.getCentroInformatica()).getMessages());
        	result.addMessages(NombreValidation.validate(data.getNombre()).getMessages());
        }

        return result;
    }
}