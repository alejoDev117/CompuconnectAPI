package co.edu.uco.compuconnect.api.validator.agendareserva;

import co.edu.uco.compuconnect.api.validator.Result;
import co.edu.uco.compuconnect.api.validator.Validation;
import co.edu.uco.compuconnect.api.validator.agendareserva.common.AgendaValidation;
import co.edu.uco.compuconnect.api.validator.agendareserva.common.ReservaValidation;
import co.edu.uco.compuconnect.api.validator.generalcommon.IdentificadorValidation;
import co.edu.uco.compuconnect.crosscutting.utils.UtilObject;
import co.edu.uco.compuconnect.dto.AgendaReservaDTO;

public final class ConsultarAgendaReservaValidation implements Validation<AgendaReservaDTO> {

    private ConsultarAgendaReservaValidation() {
        super();
    }

    public static Result validate(final AgendaReservaDTO data) {
        return new ConsultarAgendaReservaValidation().execute(data);
    }

    @Override
    public Result execute(final AgendaReservaDTO data) {
        var result = Result.create();

        if (UtilObject.isNull(data)) {
            result.addMessage("No es posible consultar una agenda de reserva con los datos vacíos");
        } else {
            result.addMessages(IdentificadorValidation.validate(data.getIdentificador()).getMessages());
            result.addMessages(AgendaValidation.validate(data.getAgenda()).getMessages());
            result.addMessages(ReservaValidation.validate(data.getReserva()).getMessages());
        }

        return result;
    }
}
