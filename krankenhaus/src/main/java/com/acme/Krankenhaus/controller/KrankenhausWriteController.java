package com.acme.Krankenhaus.controller;

import com.acme.Krankenhaus.controller.ProblemType;
import com.acme.Krankenhaus.controller.UriHelper;
import com.acme.Krankenhaus.controller.KrankenhausDTO;
import com.acme.Krankenhaus.service.EmailExistsException;
import com.acme.Krankenhaus.service.EmailExistsException;
import com.acme.Krankenhaus.service.KrankenhausWriteService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import jakarta.validation.groups.Default;
import java.net.URI;
import java.util.UUID;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import static com.acme.Krankenhaus.controller.KrankenhausGetController.API_PATH;
import static com.acme.Krankenhaus.controller.KrankenhausGetController.ID_PATTERN;
import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.NO_CONTENT;
import static org.springframework.http.HttpStatus.UNPROCESSABLE_ENTITY;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.http.ResponseEntity.created;

// Maven: ![Klassendiagramm](../../../../../../generated-docs/KundeWriteController.svg)
@Controller
@Validated
@RequestMapping(API_PATH)
@SuppressWarnings({"ClassFanOutComplexity", "java:S1075"})
class KrankenhausWriteController {
    private static final Logger LOGGER = LoggerFactory.getLogger(KrankenhausWriteController.class);
    private static final String PROBLEM_PATH = "/problem/";

    private final KrankenhausWriteService service;
    private final KrankenhausMapper mapper;
    private final UriHelper uriHelper;

    KrankenhausWriteController(final KrankenhausWriteService service, final KrankenhausMapper mapper, final UriHelper uriHelper) {
        this.service = service;
        this.mapper = mapper;
        this.uriHelper = uriHelper;
    }

    @PostMapping(consumes = APPLICATION_JSON_VALUE)
    @Operation(summary = "Einen neuen Kunden anlegen", tags = "Neuanlegen")
    @ApiResponse(responseCode = "201", description = "Kunde neu angelegt")
    @ApiResponse(responseCode = "400", description = "Syntaktische Fehler im Request-Body")
    @ApiResponse(responseCode = "422", description = "Ungültige Werte oder Email vorhanden")
    ResponseEntity<Void> post(
        @RequestBody final KrankenhausDTO krankenhausDTO,
        final HttpServletRequest request
    ) {
        LOGGER.debug("post: {}", krankenhausDTO);

        final var krankenhausInput = mapper.toKrankenhaus(krankenhausDTO);
        final var krankenhaus = service.create(krankenhausInput);
        final var baseUri = uriHelper.getBaseUri(request).toString();
        final var location = URI.create(baseUri + '/' + krankenhaus.getUuid());
        return created(location).build();
    }

    @PutMapping(path = "{id:" + ID_PATTERN + "}", consumes = APPLICATION_JSON_VALUE)
    @ResponseStatus(NO_CONTENT)
    @Operation(summary = "Ein Krankenhaus mit neuen Werten aktualisieren", tags = "Aktualisieren")
    @ApiResponse(responseCode = "204", description = "Aktualisiert")
    @ApiResponse(responseCode = "400", description = "Syntaktische Fehler im Request-Body")
    @ApiResponse(responseCode = "404", description = "Krankenhaus nicht vorhanden")
    @ApiResponse(responseCode = "422", description = "Ungültige Werte oder Email vorhanden")
    void put(
        @PathVariable final UUID id,
        @RequestBody @Valid final KrankenhausDTO krankenhausDTO
    ) {
        LOGGER.debug("put: id={}, {}", id, krankenhausDTO);
        final var krankenhausInput = mapper.toKrankenhaus(krankenhausDTO);
        service.update(krankenhausInput, id);
    }

    @DeleteMapping(path = "{id:" + ID_PATTERN + "}")
    @ResponseStatus(NO_CONTENT)
    @Operation(summary = "Ein Krankenhaus anhand der ID loeschen", tags = "Loeschen")
    @ApiResponse(responseCode = "204", description = "Gelöscht")
    void deleteById(@PathVariable final UUID id)  {
        LOGGER.debug("deleteById: id={}", id);
        service.deleteById(id);
    }

    @ExceptionHandler
    ProblemDetail onConstraintViolations(
        final MethodArgumentNotValidException ex,
        final HttpServletRequest request
    ) {
        LOGGER.debug("onConstraintViolations: {}", ex.getMessage());

        final var detailMessages = ex.getDetailMessageArguments();
        final var detail = detailMessages == null
            ? "Constraint Violations"
            : ((String) detailMessages[1]).replace(", and ", ", ");
        final var problemDetail = ProblemDetail.forStatusAndDetail(UNPROCESSABLE_ENTITY, detail);
        problemDetail.setType(URI.create(PROBLEM_PATH + ProblemType.CONSTRAINTS.getValue()));
        problemDetail.setInstance(URI.create(request.getRequestURL().toString()));

        return problemDetail;
    }

    @ExceptionHandler
    ProblemDetail onEmailExists(final EmailExistsException ex, final HttpServletRequest request) {
        LOGGER.debug("onEmailExists: {}", ex.getMessage());
        final var problemDetail = ProblemDetail.forStatusAndDetail(UNPROCESSABLE_ENTITY, ex.getMessage());
        problemDetail.setType(URI.create(PROBLEM_PATH + ProblemType.CONSTRAINTS.getValue()));
        problemDetail.setInstance(URI.create(request.getRequestURL().toString()));
        return problemDetail;
    }

    @ExceptionHandler
    ProblemDetail onMessageNotReadable(
        final HttpMessageNotReadableException ex,
        final HttpServletRequest request
    ) {
        LOGGER.debug("onMessageNotReadable: {}", ex.getMessage());
        final var problemDetail = ProblemDetail.forStatusAndDetail(BAD_REQUEST, ex.getMessage());
        problemDetail.setType(URI.create(PROBLEM_PATH + ProblemType.BAD_REQUEST.getValue()));
        problemDetail.setInstance(URI.create(request.getRequestURL().toString()));
        return problemDetail;
    }
}
