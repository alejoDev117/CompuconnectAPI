package co.edu.uco.compuconnect.api.validator;
import java.util.List;


public interface Validation<T> {
	
	Result execute(T data);	

}