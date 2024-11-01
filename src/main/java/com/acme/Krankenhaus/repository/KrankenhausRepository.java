package com.acme.Krankenhaus.repository;

import com.acme.Krankenhaus.entity.Krankenhaus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import static com.acme.Krankenhaus.repository.KrankenhausDB.KRANKENHAUSER;

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
    public @NonNull List<Krankenhaus> getAll() {
        return KRANKENHAUSER;
    }

    /**
     * Sucht ein Krankenhaus anhand der angegebenen ID.
     *
     * @param id die ID des gesuchten Krankenhauses
     * @return ein {@link Optional} mit dem gefundenen Krankenhaus,
     *         oder ein leeres Optional, wenn kein Krankenhaus mit der angegebenen ID gefunden wurde
     */
    public @NonNull Optional<Krankenhaus> getByID(@NonNull final String id) {
        LOGGER.debug("Suche nach Krankenhaus mit id: {}", id);

        Optional<Krankenhaus> krankenhaus = KRANKENHAUSER.stream()
            .filter(a -> Objects.equals(id, a.getUuid().toString()))
            .findFirst();

        LOGGER.debug("Ergebnis der Suche nach id {}: {}", id, krankenhaus);
        return krankenhaus;
    }
}
