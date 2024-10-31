package com.acme.Krankenhaus.controller;

import com.acme.Krankenhaus.entity.Krankenhaus;
import com.acme.Krankenhaus.service.KrankenhausReadService;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(GetKrankenhausController.API_PATH)
@OpenAPIDefinition(info = @Info(title = "Krankenhaus API", version = "v1"))
public class GetKrankenhausController {

    public static final String API_PATH = "/krankenhaus";
    private static final Logger LOGGER = LoggerFactory.getLogger(GetKrankenhausController.class);

    private final KrankenhausReadService krankenhausReadService;

    public GetKrankenhausController(final KrankenhausReadService krankenhausReadService) {
        this.krankenhausReadService = krankenhausReadService;
    }

    @GetMapping(produces = APPLICATION_JSON_VALUE)
    @Operation(summary = "Suche alle Krankenhäuser", tags = "Suchen")
    @ApiResponse(responseCode = "200", description = "Alle Krankenhäuser erfolgreich gefunden")
    @ApiResponse(responseCode = "404", description = "Keine Krankenhäuser in der Datenbank gefunden")
    public List<Krankenhaus> getAll() {
        LOGGER.info("Suche nach allen Krankenhäuser in der Datenbank gestartet");

        final List<Krankenhaus> krankenhaus = krankenhausReadService.getAll();
        LOGGER.info("Suche nach allen Krankenhäuser in der Datenbank abgeschlossen");
        return krankenhaus;
    }

    @GetMapping(path = "/{id}", produces = APPLICATION_JSON_VALUE)
    @Operation(summary = "Suche Krankenhaus nach id", tags = "Suchen")
    @ApiResponse(responseCode = "200", description = "krankenhaus erfolgreich gefunden")
    @ApiResponse(responseCode = "404", description = "Kein Krankenhaus mit dieser id gefunden")
    public Krankenhaus getByID(@PathVariable final String id) {
        LOGGER.info("Suche nach Krankenhaus mit id: {}", id);

        final Krankenhaus krankenhaus = krankenhausReadService.getByID(id);
        LOGGER.info("Suche nach Krankenhaus mit id {} abgeschlossen", id);
        return krankenhaus;
    }

}
