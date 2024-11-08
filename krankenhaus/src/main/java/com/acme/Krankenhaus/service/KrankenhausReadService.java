package com.acme.Krankenhaus.service;

import com.acme.Krankenhaus.entity.Krankenhaus;
import com.acme.Krankenhaus.repository.KrankenhausRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Map;

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
     * Sucht nach Krankenhäusern basierend auf den angegebenen Suchkriterien.
     * Unterstützte Kriterien: "name" und "standort".
     *
     * @param suchkriterien eine Map der Suchkriterien zur Filterung der Krankenhäuser
     * @return eine Liste der gefundenen Krankenhäuser
     * @throws NotFoundException wenn keine Krankenhäuser den Suchkriterien entsprechen
     */
    public @NonNull List<Krankenhaus> find(@NonNull final Map<String, List<String>> suchkriterien) {
        LOGGER.debug("find: suchkriterien={}", suchkriterien);

        if (suchkriterien.isEmpty()) {
            return krankenhausRepository.findAll();
        }

        if (suchkriterien.size() == 1) {
            final var namen = suchkriterien.get("name");
            if (namen != null && namen.size() == 1) {
                final var krankenhaeuser = krankenhausRepository.findByName(namen.getFirst());
                if (krankenhaeuser.isEmpty()) {
                    throw new NotFoundException(suchkriterien);
                }
                LOGGER.debug("find (name): {}", krankenhaeuser);
                return krankenhaeuser;
            }

            final var standort = suchkriterien.get("standort");
            if (standort != null && standort.size() == 1) {
                final var krankenhaeuser = krankenhausRepository
                    .findByStandort(standort.getFirst());
                if (krankenhaeuser.isEmpty()) {
                    throw new NotFoundException(suchkriterien);
                }
                LOGGER.debug("find (standort): {}", krankenhaeuser);
                return krankenhaeuser;
            }
        }

        final var krankenhaeuser = krankenhausRepository.find(suchkriterien);
        if (krankenhaeuser.isEmpty()) {
            throw new NotFoundException(suchkriterien);
        }
        LOGGER.debug("find: {}", krankenhaeuser);
        return krankenhaeuser;
    }

    /**
     * Sucht ein Krankenhaus anhand der angegebenen ID.
     *
     * @param id die ID des gesuchten Krankenhauses
     * @return das gefundene Krankenhaus
     * @throws IllegalArgumentException wenn kein Krankenhaus für die angegebene ID gefunden wurde
     */
    public @NonNull Krankenhaus findById(String id) {
        LOGGER.debug("Starte Suche nach Krankenhaus mit id: {}", id);

        Krankenhaus krankenhaus = krankenhausRepository.findByID(id)
            .orElseThrow(() -> new NotFoundException(id));

        LOGGER.debug("Suche nach Krankenhaus mit id beendet");
        return krankenhaus;
    }
}
