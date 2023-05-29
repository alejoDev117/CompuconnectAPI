package co.edu.uco.compuconnect.api.validator.reserva.common;

import java.util.UUID;

import co.edu.uco.compuconnect.api.validator.Result;
import co.edu.uco.compuconnect.api.validator.Validation;
import co.edu.uco.compuconnect.crosscutting.utils.UtilObject;
import co.edu.uco.compuconnect.crosscutting.utils.UtilText;
import co.edu.uco.compuconnect.crosscutting.utils.UtilUUID;
import co.edu.uco.compuconnect.dto.UsuarioDTO;

public class AutorValidation implements Validation<UsuarioDTO> {

    private AutorValidation() {
        super();
    }

    public static Result validate(UsuarioDTO data) {
        return new AutorValidation().execute(data);
    }

    @Override
    public Result execute(UsuarioDTO data) {
        Result result = Result.create();

        if (UtilObject.isNull(data)) {
            result.addMessage("No es posible continuar con el usuario vacío");
        } else {
            if (UtilObject.isNull(data.getIdentificador())) {
                result.addMessage("No es posible continuar con el identificador del usuario vacío");
            }
            if (UtilObject.isNull(data.getTipoUsuario())) {
                result.addMessage("El tipo de usuario no puede ser nulo");
            }
            if (UtilText.getUtilText().isNull(data.getNombre())) {
                result.addMessage("El nombre del usuario no puede estar vacío");
            }
            if (UtilObject.isNull(data.getTipoIdentificacion())) {
                result.addMessage("El tipo de identificación del usuario no puede estar vacío");
            }
            if (UtilText.getUtilText().isNull(data.getIdentificacion())) {
                result.addMessage("El número de identificación del usuario no puede estar vacío");
            }
            if (UtilText.getUtilText().isNull(data.getCorreoInstitucional())) {
                result.addMessage("El correo institucional del usuario no puede estar vacío");
            }
        }

        return result;
    }
}