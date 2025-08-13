package org.arthurandrade.samsungfakestoreapi.config;

import org.arthurandrade.samsungfakestoreapi.domain.noData.GenericResponse;
import org.arthurandrade.samsungfakestoreapi.domain.noData.JsonMessage;
import org.arthurandrade.samsungfakestoreapi.util.MessageContextHolder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.MessageSource;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.resource.NoResourceFoundException;

import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

import static org.arthurandrade.samsungfakestoreapi.domain.enums.ResponseCodeEnum.TYPE_ERROR;

@RestControllerAdvice
public class GlobalExceptionHandler {

	private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

	private final MessageSource messageSource;

	public GlobalExceptionHandler(MessageSource messageSource) {
		this.messageSource = messageSource;
	}

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<GenericResponse> handleValidation(MethodArgumentNotValidException e) {
		String msg = e.getBindingResult().getFieldErrors().stream()
				.map((err) -> err.getField() + ": " + resolveMessage(err.getDefaultMessage(), null))
				.collect(Collectors.joining("; "));
		return buildResponse(HttpStatus.BAD_REQUEST, msg, null);
	}

	@ExceptionHandler(DataIntegrityViolationException.class)
	public ResponseEntity<GenericResponse> handleIntegrity(DataIntegrityViolationException e) {
		return buildResponse(HttpStatus.CONFLICT, "error.integrityViolation", e);
	}

	@ExceptionHandler(HttpRequestMethodNotSupportedException.class)
	public ResponseEntity<GenericResponse> handleMethodNotAllowed(HttpRequestMethodNotSupportedException e) {
		return buildResponse(HttpStatus.METHOD_NOT_ALLOWED, "error.methodNotAllowed", e);
	}

	@ExceptionHandler(NoResourceFoundException.class)
	public ResponseEntity<GenericResponse> handleNoResourceFoundException(Exception e) {
		return buildResponse(HttpStatus.NOT_FOUND, "error.resource.notFound", e);
	}

	@ExceptionHandler(DuplicateKeyException.class)
	public ResponseEntity<GenericResponse> handleDuplicateKeyException(Exception e) {
		return buildResponse(HttpStatus.CONFLICT, "error.user.alreadyExists", e);
	}

	@ExceptionHandler(Exception.class)
	public ResponseEntity<GenericResponse> handleGeneric(Exception e) {
		logger.error("Unknown error:", e);
		return buildResponse(HttpStatus.INTERNAL_SERVER_ERROR, "error.internal", e);
	}

	private ResponseEntity<GenericResponse> buildResponse(HttpStatus status, String rawMessage, Object context) {
		GenericResponse response = new GenericResponse(false);
		response.addMessage(new JsonMessage(resolveMessage(rawMessage, null), TYPE_ERROR));
		addUserMessages(response, context);
		return ResponseEntity.status(status).body(response);
	}

	private void addUserMessages(GenericResponse response, Object entity) {
		try {
			List<JsonMessage> messages = MessageContextHolder.getMessages();
			if(messages == null) {
				return;
			}

			messages.forEach((msg) -> {
                var text = resolveMessage(msg.message, msg.args != null ? msg.args.toArray() : null);
				response.addMessage(new JsonMessage(text, msg.type));
			});
			MessageContextHolder.clear();
		} catch(Exception ex) {
			if(entity != null) {
				logger.error("Error processing user's messages: {}", entity.getClass().getSimpleName(), ex);
                return;
			}
            logger.error("Error processing user's messages", ex);
		}
	}

	private String resolveMessage(String codeOrText, Object[] args) {
		try {
			return messageSource.getMessage(codeOrText, args, codeOrText, Locale.getDefault());
		} catch(Exception e) {
			return codeOrText;
		}
	}
}
