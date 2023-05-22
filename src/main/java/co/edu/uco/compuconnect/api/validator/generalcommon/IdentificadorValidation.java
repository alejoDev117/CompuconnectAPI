package co.edu.uco.compuconnect.api.validator.generalcommon;

import java.util.UUID;

import co.edu.uco.compuconnect.api.validator.Result;
import co.edu.uco.compuconnect.api.validator.Validation;
import co.edu.uco.compuconnect.crosscutting.utils.Messages.IdentificadorValidationMessage;
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
			result.addMessage(IdentificadorValidationMessage.IDENTIFICADOR_EMPTY_MESSAGE);
		}
		
		else if(UtilUUID.isDefault(data)) {
			result.addMessage(IdentificadorValidationMessage.IDENTIFICADOR_DEFAULT_MESSAGE);
		}
		
		return result;
	}


}
