package com.acme.Krankenhaus.controller;

import com.acme.Krankenhaus.service.NotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import static org.springframework.http.HttpStatus.NOT_FOUND;

/// Handler für allgemeine Exceptions.
@ControllerAdvice
class CommonExceptionHandler {
    private static final Logger LOGGER = LoggerFactory.getLogger(CommonExceptionHandler.class);

    /// Konstruktor mit _package private_ für _Spring_.
    CommonExceptionHandler() {
    }

    /// [ExceptionHandler], wenn ein Krankenhaus gesucht wird, aber nicht vorhanden ist.
    ///
    /// @param ex Die zugehörige [NotFoundException].
    @ExceptionHandler
    @ResponseStatus(NOT_FOUND)
    void onNotFound(final NotFoundException ex) {
        LOGGER.debug("onNotFound: {}", ex.getMessage());
    }
}
