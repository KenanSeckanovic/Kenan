package com.acme.Krankenhaus.service;

import com.acme.Krankenhaus.entity.Krankenhaus;
import com.acme.Krankenhaus.repository.KrankenhausRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Service-Klasse zur Bereitstellung von Leseoperationen für {@link Krankenhaus}-Objekte.
 * Bietet Methoden zum Abrufen aller Krankenhäuser und zum Suchen nach einem Krankenhaus anhand einer ID.
 */
@Service
public class KrankenhausReadService {

    private static final Logger LOGGER = LoggerFactory.getLogger(KrankenhausReadService.class);
    private final KrankenhausRepository krankenhausRepository;

    /**
     * Konstruktor zur Initialisierung des KrankenhausReadService mit dem angegebenen Repository.
     *
     * @param krankenhausRepository das Repository zum Zugriff auf die Krankenhausdaten
     */
    public KrankenhausReadService( final KrankenhausRepository krankenhausRepository) {
        this.krankenhausRepository = krankenhausRepository;
    }

    /**
     * Ruft alle Krankenhäuser aus der Datenbank ab.
     *
     * @return eine Liste aller Krankenhäuser
     * @throws IllegalArgumentException wenn keine Krankenhäuser in der Datenbank gefunden wurden
     */
    public @NonNull List<Krankenhaus> getAll() {
        LOGGER.debug("Starte Abruf aller Krankenhaus");

        List<Krankenhaus> krankenhauser = krankenhausRepository.getAll();
        if (krankenhauser.isEmpty()) {
            throw new IllegalArgumentException("Keine Autohäuser in der Datenbank gefunden.");
        }

        return krankenhauser;
    }

    /**
     * Sucht ein Krankenhaus anhand der angegebenen ID.
     *
     * @param id die ID des gesuchten Krankenhauses
     * @return das gefundene Krankenhaus
     * @throws IllegalArgumentException wenn kein Krankenhaus für die angegebene ID gefunden wurde
     */
    public @NonNull Krankenhaus getByID(String id) {
        LOGGER.debug("Starte Suche nach Krankenhaus mit id: {}", id);

        Krankenhaus krankenhaus = krankenhausRepository.getByID(id)
            .orElseThrow(() -> new IllegalArgumentException("Kein Krankenhaus für die angegebene id gefunden."));

        LOGGER.debug("Suche nach Krankenhaus mit id beendet");
        return krankenhaus;
    }
}
