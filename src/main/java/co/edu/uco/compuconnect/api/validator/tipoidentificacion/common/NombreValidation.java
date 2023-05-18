package co.edu.uco.compuconnect.api.validator.tipoidentificacion.common;

import co.edu.uco.compuconnect.api.validator.Result;
import co.edu.uco.compuconnect.api.validator.Validation;
import co.edu.uco.compuconnect.crosscutting.utils.UtilText;

public class NombreValidation implements Validation<String>{
	
	  private NombreValidation() {
	        super();
	    }

	    public static Result validate(String data) {
	        return new NombreValidation().execute(data);
	    }

	    @Override
	    public Result execute(String data) {
	        Result result = Result.create();

	        if (UtilText.isNull(data)) {
	            result.addMessage("El nombre del tipo identificacion no puede estar vacío");
	        }

	        return result;
	    }
}
