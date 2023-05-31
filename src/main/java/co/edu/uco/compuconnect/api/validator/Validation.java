package co.edu.uco.compuconnect.api.validator;


public interface Validation<T> {
	
	Result execute(T data);	

}