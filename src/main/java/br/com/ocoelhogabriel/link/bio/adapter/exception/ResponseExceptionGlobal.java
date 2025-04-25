package br.com.ocoelhogabriel.link.bio.adapter.exception;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.security.SignatureException;
import java.text.ParseException;

import org.apache.catalina.connector.ClientAbortException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageConversionException;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.exceptions.TokenExpiredException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;

import br.com.ocoelhogabriel.link.bio.shared.ResponseUtil;
import jakarta.persistence.EntityNotFoundException;

@ControllerAdvice
public class ResponseExceptionGlobal {
    private static final Logger log = LoggerFactory.getLogger(ResponseExceptionGlobal.class);

    protected ResponseExceptionGlobal() {
        super();
    }

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<Object> handleEntityNotFound(EntityNotFoundException ex) {
        log.error("EntityNotFoundException: {}", ex.getMessage(), ex);
        return ResponseUtil.notFound(ex.getMessage(), ex);
    }

    @ExceptionHandler(UsernameNotFoundException.class)
    public ResponseEntity<Object> handleUsernameNotFoundException(UsernameNotFoundException ex) {
        log.error("UsernameNotFoundException: {} - ", ex.getMessage(), ex);
        return ResponseUtil.notFound(ex.getMessage(), ex);
    }

    @ExceptionHandler(IOException.class)
    public ResponseEntity<Object> handleIOException(IOException ex) {
        log.error("IOException: {} - ", ex.getMessage(), ex);
        return ResponseUtil.badRequest(ex.getMessage(), ex);
    }

    @ExceptionHandler(NoSuchAlgorithmException.class)
    public ResponseEntity<Object> handleNoSuchAlgorithmException(NoSuchAlgorithmException ex) {
        log.error("NoSuchAlgorithmException: {}{} - ", ex.getMessage(), ex);
        return ResponseUtil.badRequest(ex.getMessage(), ex);
    }

    @ExceptionHandler(NullPointerException.class)
    public ResponseEntity<Object> handleNullPointerException(NullPointerException ex) {
        log.error("NullPointerException: {} - ", ex.getMessage(), ex);
        return ResponseUtil.badRequest(ex.getMessage(), ex);
    }

    @ExceptionHandler(TokenExpiredException.class)
    public ResponseEntity<Object> handleTokenExpiredException(TokenExpiredException ex) {
        log.error("TokenExpiredException: {} - ", ex.getMessage(), ex);
        return ResponseUtil.unauthorized(ex.getMessage(), ex);
    }

    @ExceptionHandler(JWTVerificationException.class)
    public ResponseEntity<Object> handleJWTVerificationException(JWTVerificationException ex) {
        log.error("JWTVerificationException: {} - ", ex.getMessage(), ex);
        return ResponseUtil.unauthorized(ex.getMessage(), ex);
    }

    @ExceptionHandler(JWTCreationException.class)
    public ResponseEntity<Object> handleJWTCreationException(JWTCreationException ex) {
        log.error("JWTCreationException: {} - ", ex.getMessage(), ex);
        return ResponseUtil.unauthorized(ex.getMessage(), ex);
    }

    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<Object> handleAccessDeniedException(AccessDeniedException ex) {
        log.error("AccessDeniedException: {} - ", ex.getMessage(), ex);
        return ResponseUtil.unauthorized(ex.getMessage(), ex);
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<Object> handleRuntimeException(RuntimeException ex) {
        log.error("RuntimeException: {} - ", ex.getMessage(), ex);
        return ResponseUtil.serverError(ex.getMessage(), ex);
    }

    @ExceptionHandler(HttpMessageConversionException.class)
    public ResponseEntity<Object> handleHttpMessageConversionException(HttpMessageConversionException ex) {
        log.error("HttpMessageConversionException: {} - ", ex.getMessage(), ex);
        return ResponseUtil.serverError(ex.getMessage(), ex);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> handleGenericException(Exception ex) {
        log.error("Exception: {} - ", ex.getMessage(), ex);
        return ResponseUtil.serverError(ex.getMessage(), ex);
    }

    @ExceptionHandler(ClientAbortException.class)
    public void handleClientAbortException(ClientAbortException ex) {
        log.debug("ClientAbortException: {}", ex.getMessage());
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public void handleIllegalArgumentException(IllegalArgumentException ex) {
        log.debug("IllegalArgumentException: {} - ", ex.getMessage(), ex);
    }

    @ExceptionHandler(SignatureException.class)
    public void handleSignatureException(SignatureException ex) {
        log.debug("SignatureException: {} - ", ex.getMessage(), ex);
    }

    @ExceptionHandler(AssertionError.class)
    public void handleAssertionError(AssertionError ex) {
        log.debug("AssertionError: {} - ", ex.getMessage(), ex);
    }

    @ExceptionHandler(ParseException.class)
    public void handleParseException(ParseException ex) {
        log.debug("ParseException: {} - ", ex.getMessage(), ex);
    }

    @ExceptionHandler(JsonMappingException.class)
    public void handleJsonMappingException(JsonMappingException ex) {
        log.debug("JsonMappingException: {} - ", ex.getMessage(), ex);
    }

    @ExceptionHandler(JsonProcessingException.class)
    public void handleJsonProcessingException(JsonProcessingException ex) {
        log.debug("JsonProcessingException: {} - ", ex.getMessage(), ex);
    }
}
