package com.acme.Krankenhaus.controller;

import com.acme.Krankenhaus.entity.Krankenhaus;
import com.acme.Krankenhaus.service.KrankenhausReadService;
import com.acme.Krankenhaus.service.NotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.lang.NonNull;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

/**
 * REST-Controller, der Endpunkte für Krankenhausdaten bereitstellt.
 * Dieser Controller bietet Methoden zum Abrufen aller Krankenhäuser sowie eines bestimmten
 * Krankenhauses anhand der ID.
 */
@RestController
@RequestMapping(GetKrankenhausController.API_PATH)
public class GetKrankenhausController {
    /// API ///
    public static final String API_PATH = "/krankenhaus";

    private static final Logger LOGGER = LoggerFactory.getLogger(GetKrankenhausController.class);

    private final KrankenhausReadService krankenhausReadService;

    /**
     * Konstruktor für die Initialisierung des Controllers mit dem erforderlichen Service.
     *
     * @param krankenhausReadService der Service, der Krankenhausdaten bereitstellt
     */
    public GetKrankenhausController(final KrankenhausReadService krankenhausReadService) {
        this.krankenhausReadService = krankenhausReadService;
    }

    /**
     * Endpunkt zum Abrufen aller Krankenhäuser, die den angegebenen Suchkriterien entsprechen.
     *
     * @param suchkriterien ein MultiValueMap von Suchkriterien, die zur Filterung der Krankenhäuser verwendet werden
     * @return eine Liste aller Krankenhäuser, die den Suchkriterien entsprechen
     */
    @GetMapping(produces = APPLICATION_JSON_VALUE)
    public List<Krankenhaus> get(@RequestParam @NonNull final MultiValueMap<String, String> suchkriterien) {
        LOGGER.debug("get: suchkriterien={}", suchkriterien);
        final List<Krankenhaus> krankenhaus = krankenhausReadService.find(suchkriterien);
        LOGGER.debug("get: {}", krankenhaus);
        return krankenhaus;
    }

    /**
     * Endpunkt zum Abrufen eines Krankenhauses anhand der eindeutigen ID.
     *
     * @param id die ID des gesuchten Krankenhauses
     * @return das Krankenhaus mit der angegebenen ID, falls vorhanden
     * @throws NotFoundException wenn kein Krankenhaus mit der angegebenen ID gefunden wurde
     */
    @GetMapping(path = "/{id}", produces = APPLICATION_JSON_VALUE)
    public Krankenhaus getByID(@PathVariable final String id) {
        LOGGER.info("Suche nach Krankenhaus mit id: {}", id);
        final Krankenhaus krankenhaus = krankenhausReadService.findById(id);
        LOGGER.info("Suche nach Krankenhaus mit id {} abgeschlossen", id);
        return krankenhaus;
    }
}
