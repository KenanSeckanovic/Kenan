package com.acme.Krankenhaus.repository;

import com.acme.Krankenhaus.entity.Krankenhaus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import static com.acme.Krankenhaus.repository.KrankenhausDB.KRANKENHAUSER;
import static java.util.Collections.emptyList;

/**
 * Repository-Klasse für den Zugriff auf {@link Krankenhaus}-Daten.
 * Diese Klasse stellt Methoden zum Abrufen von Krankenhausdaten aus einer statischen Datenquelle bereit.
 */
@Repository
public class KrankenhausRepository {
    private static final Logger LOGGER = LoggerFactory.getLogger(KrankenhausRepository.class);

    /**
     * Konstruktor für das KrankenhausRepository.
     * Aktuell keine speziellen Initialisierungen erforderlich.
     */
    public KrankenhausRepository() {
        // Kein spezieller Initialisierungsbedarf
    }

    /**
     * Ruft alle Krankenhäuser ab.
     *
     * @return eine Liste aller verfügbaren {@link Krankenhaus}-Objekte
     */
    public @NonNull List<Krankenhaus> findAll() {
        return KRANKENHAUSER;
    }

    /**
     * Sucht ein Krankenhaus anhand der angegebenen ID.
     *
     * @param id die ID des gesuchten Krankenhauses
     * @return ein {@link Optional} mit dem gefundenen Krankenhaus,
     *         oder ein leeres Optional, wenn kein Krankenhaus mit der angegebenen ID gefunden wurde
     */
    public @NonNull Optional<Krankenhaus> findByID(@NonNull final String id) {
        LOGGER.debug("Suche nach Krankenhaus mit id: {}", id);

        Optional<Krankenhaus> krankenhaus = KRANKENHAUSER.stream()
            .filter(a -> Objects.equals(id, a.getUuid().toString()))
            .findFirst();

        LOGGER.debug("Ergebnis der Suche nach id {}: {}", id, krankenhaus);
        return krankenhaus;
    }

    /**
     * Sucht nach Krankenhäusern basierend auf den angegebenen Suchkriterien.
     * Unterstützte Kriterien: "standort" und "name".
     *
     * @param suchkriterien eine Map von Suchkriterien zur Filterung
     * @return eine Liste der gefundenen Krankenhäuser oder eine leere Liste, wenn keine Übereinstimmungen vorhanden sind
     */
    public @NonNull List<Krankenhaus> find(final Map<String, ? extends List<String>> suchkriterien) {
        LOGGER.debug("find: suchkriterien={}", suchkriterien);

        if (suchkriterien.isEmpty()) {
            return findAll();
        }

        for (final var entry : suchkriterien.entrySet()) {
            switch (entry.getKey()) {
                case "standort" -> {
                    return findByStandort(entry.getValue().getFirst());
                }
                case "name" -> {
                    return findByName(entry.getValue().getFirst());
                }
                default -> {
                    LOGGER.debug("find: ungueltiges Suchkriterium={}", entry.getKey());
                    return emptyList();
                }
            }
        }

        return emptyList();
    }

    /**
     * Sucht nach Krankenhäusern, deren Namen das angegebene Schlüsselwort enthalten.
     *
     * @param name der Name oder ein Teil davon, nach dem gesucht wird
     * @return eine Liste der gefundenen {@link Krankenhaus}-Objekte mit übereinstimmendem Namen
     */
    public @NonNull List<Krankenhaus> findByName(final String name) {
        LOGGER.debug("findByName: name={}", name);
        final var krankenhaeuser = KRANKENHAUSER.stream()
            .filter(krankenhaus -> krankenhaus.getName().contains(name))
            .toList();
        LOGGER.debug("findByName: krankenhaeuser={}", krankenhaeuser);
        return krankenhaeuser;
    }

    /**
     * Sucht nach Krankenhäusern, die sich im angegebenen Standort befinden.
     *
     * @param standort der Standort (Stadt) des Krankenhauses
     * @return eine Liste der gefundenen {@link Krankenhaus}-Objekte mit übereinstimmendem Standort
     */
    public @NonNull List<Krankenhaus> findByStandort(final String standort) {
        LOGGER.debug("findByStandort: standort={}", standort);
        final var krankenhaeuser = KRANKENHAUSER.stream()
            .filter(krankenhaus -> krankenhaus.getLageort().getStadt().contains(standort))
            .toList();
        LOGGER.debug("findByStandort: krankenhaeuser={}", krankenhaeuser);
        return krankenhaeuser;
    }
}
