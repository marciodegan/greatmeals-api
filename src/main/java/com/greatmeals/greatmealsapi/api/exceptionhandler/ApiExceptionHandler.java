package com.greatmeals.greatmealsapi.api.exceptionhandler;

import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import com.greatmeals.greatmealsapi.domain.exception.EntidadeEmUsoException;
import com.greatmeals.greatmealsapi.domain.exception.EntidadeNaoEncontradaException;
import com.greatmeals.greatmealsapi.domain.exception.NegocioException;
import org.flywaydb.core.internal.util.ExceptionUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.stream.Collectors;

@ControllerAdvice
public class ApiExceptionHandler extends ResponseEntityExceptionHandler {

    @Override
    protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        // capturamos a HttpMessageNotReadableException, pega a rootCause
        Throwable rootCause = ExceptionUtils.getRootCause(ex);

        // se a causa raiz for uma InvalidFormatException, retorna com o método especialista em tratá-la.
        if (rootCause instanceof InvalidFormatException) {
            return handlInvalidFormatException((InvalidFormatException) rootCause, headers, status, request);
        }
        // se não, trata de forma genérica.

        ProblemType problemType = ProblemType.MENSAGEM_INCOMPREENSIVEL;
        String detail = "O corpo da requisição está inválido.";

        Problem problem = createProblem(status, problemType, detail);

        return handleExceptionInternal(ex, problem, new HttpHeaders(), status, request);
    }

    private ResponseEntity<Object> handlInvalidFormatException(InvalidFormatException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {

        String path = ex.getPath().stream()
                .map(ref -> ref.getFieldName())
                .collect(Collectors.joining("."));

        ProblemType problemType = ProblemType.MENSAGEM_INCOMPREENSIVEL;

        String detail = String.format("A propriedade '%s' recebeu o valor '%s' que é de um tipo inválido." +
                 "Corrija e informe um valor compatível com o tipo %s.",
                path, ex.getValue(), ex.getTargetType().getSimpleName());

        Problem problem = createProblem(status, problemType, detail);

        return handleExceptionInternal(ex, problem, headers, status, request);
    }

    @ExceptionHandler(EntidadeNaoEncontradaException.class)
    public ResponseEntity<?> handleEntitdadeNaoEncontradaException(
            EntidadeNaoEncontradaException ex, WebRequest request) {

        HttpStatus status = HttpStatus.NOT_FOUND;
        String detail = ex.getMessage();
        ProblemType problemType = ProblemType.ENTIDADE_NAO_ENCONTRADA;

        Problem problem = createProblem(status, problemType, detail);

        return handleExceptionInternal(ex, problem, new HttpHeaders(), status, request);
    }

    @ExceptionHandler(NegocioException.class)
    public ResponseEntity<?> handleNegocioException(
            NegocioException ex, WebRequest request) {
        HttpStatus status = HttpStatus.BAD_REQUEST;
        String detail = ex.getMessage();
        ProblemType problemType = ProblemType.ERRO_NEGOCIO;

        Problem problem = createProblem(status, problemType, detail);

        return handleExceptionInternal(ex, problem, new HttpHeaders(), status, request);
    }

    @ExceptionHandler(EntidadeEmUsoException.class)
    public ResponseEntity<?> handleEntidadeEmUsoException(
            EntidadeEmUsoException ex, WebRequest request) {

        HttpStatus status = HttpStatus.CONFLICT;
        String detail = ex.getMessage();
        ProblemType problemType = ProblemType.ENTIDADE_EM_USO;

        Problem problem = createProblem(status, problemType, detail);

        return handleExceptionInternal(ex, problem, new HttpHeaders(), status, request);

    }

    @Override
    protected ResponseEntity<Object> handleExceptionInternal(Exception ex, Object body, HttpHeaders headers, HttpStatus status, WebRequest request) {
        if (body == null) {
            body = new Problem(status.value(), status.getReasonPhrase());

        } else if (body instanceof String) {
            body = new Problem(status.value(), (String) body);
        }

        return super.handleExceptionInternal(ex, body, headers, status, request);
    }

    private Problem createProblem(HttpStatus status, ProblemType problemType, String detail) {
        return new Problem(status.value(), problemType.getTitle(), problemType.getUri(), detail);
    }


}
