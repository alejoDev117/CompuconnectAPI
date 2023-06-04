package co.edu.uco.compuconnect.api.validator.reserva;

import co.edu.uco.compuconnect.api.validator.Result;

import co.edu.uco.compuconnect.api.validator.Validation;
import co.edu.uco.compuconnect.api.validator.generalcommon.IdentificadorValidation;
import co.edu.uco.compuconnect.api.validator.reserva.common.AgendaValidation;
import co.edu.uco.compuconnect.api.validator.reserva.common.AutorValidation;
import co.edu.uco.compuconnect.api.validator.reserva.common.DescripcionValidation;
import co.edu.uco.compuconnect.api.validator.reserva.common.FechaFinValidation;
import co.edu.uco.compuconnect.api.validator.reserva.common.FechaInicioValidation;
import co.edu.uco.compuconnect.api.validator.reserva.common.FrecuenciaValidation;
import co.edu.uco.compuconnect.api.validator.reserva.common.HoraCreacionValidation;
import co.edu.uco.compuconnect.api.validator.reserva.common.TipoReservaValidation;
import co.edu.uco.compuconnect.crosscutting.utils.UtilObject;
import co.edu.uco.compuconnect.dto.ReservaDTO;

public final class ConsultarReservaValidation implements Validation<ReservaDTO> {

    private ConsultarReservaValidation() {
        super();
    }

    public static Result validate(final ReservaDTO data) {
        return new ConsultarReservaValidation().execute(data);
    }

    @Override
    public Result execute(final ReservaDTO data) {
        var result = Result.create();

        if (UtilObject.isNull(data)) {
            result.addMessage("No es posible consultar una reserva con los datos vacíos");
        } else {
        	result.addMessages(IdentificadorValidation.validate(data.getIdentificador()).getMessages());
            result.addMessages(FechaInicioValidation.validate(data.getFechaInicio()).getMessages());
            result.addMessages(FechaFinValidation.validate(data.getFechaFin()).getMessages());
            result.addMessages(AgendaValidation.validate(data.getAgenda()).getMessages());
            result.addMessages(TipoReservaValidation.validate(data.getTipoReserva()).getMessages());
            result.addMessages(AutorValidation.validate(data.getAutor()).getMessages());
            result.addMessages(DescripcionValidation.validate(data.getDescripcion()).getMessages());
            result.addMessages(HoraCreacionValidation.validate(data.getHoraCreacion()).getMessages());
            result.addMessages(FrecuenciaValidation.validate(data.getFrecuencia()).getMessages());
        }

        return result;
    }
}
