package lol.bvlabs.yessir.infra.exception;

import java.net.URI;

import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import jakarta.persistence.EntityNotFoundException;

@RestControllerAdvice
public class GlobalExceptionHandler {
	
	/*
	@ExceptionHandler(EntityNotFoundException.class)
	public ResponseEntity<?> entityNotFoundHandler(EntityNotFoundException exeption, WebRequest request) {
        ProblemDetail problemDetail = ProblemDetail.forStatus(HttpStatus.NOT_FOUND);
        problemDetail.setTitle("Recurso não encontrado");
        problemDetail.setDetail(exeption.getMessage());
        URI instanceUri = URI.create(request.getDescription(false));
        problemDetail.setInstance(instanceUri);
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(problemDetail);
	}
	*/
	
	@ExceptionHandler(EntityNotFoundException.class)
	public ResponseEntity<?> entityNotFoundHandler() {
		return ResponseEntity.notFound().build();
	}
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<?> MethodArgumentNotValidHandler(MethodArgumentNotValidException exeption) {
		var erros = exeption.getFieldErrors();
		return ResponseEntity.badRequest().body(erros.stream().map(DadosErroValidacao::new).toList());
	}
	
	private record DadosErroValidacao(String field, String message) {
		public DadosErroValidacao(FieldError fieldError) {
			this(fieldError.getField(), fieldError.getDefaultMessage());
		}
	}

}
