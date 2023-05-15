package co.edu.uco.compuconnect.api.validator.reserva.common;

import java.util.UUID;

import co.edu.uco.compuconnect.api.validator.Result;
import co.edu.uco.compuconnect.api.validator.Validation;
import co.edu.uco.compuconnect.crosscutting.utils.UtilObject;
import co.edu.uco.compuconnect.crosscutting.utils.UtilUUID;

public class IdentificadorValidation implements Validation<UUID>{
	
	private IdentificadorValidation() {
		super();
	}
	
	public static final Result validate(final UUID data) {
		return new IdentificadorValidation().execute(data);
	}
	
	@Override
	public final Result execute(UUID data) {
		var result = Result.create();
		
		if(UtilObject.isNull(data)) {
			result.addMessage("No es posible continuar con el identificador de la reserva vacio");
		}
		
		else if(UtilUUID.isDefault(data)) {
			result.addMessage("No es posible tener el identificador por defecto de la reserva");
		}
		
		return result;
	}


}
