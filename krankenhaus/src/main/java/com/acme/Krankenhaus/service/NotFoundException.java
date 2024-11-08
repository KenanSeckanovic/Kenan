
package com.acme.Krankenhaus.service;

import java.util.List;
import java.util.Map;

/// [RuntimeException], falls kein Krankenhaus gefunden wurde.
public final class NotFoundException extends RuntimeException {
    /// Fehlerhafte ID
    private final String id;

    ///  Fehlerhafte Suchkriterien
    private final Map<String, List<String>> suchkriterien;

    /// Standardkonstruktor für den [KrankenhausReadService], wenn alle Krankenhäuser gesucht werden, aber keine existieren.
    NotFoundException() {
        super("Keine Krankenhäuser gefunden.");
        id = null;
        suchkriterien = null;
    }

    /// Konstruktor für den [KrankenhausReadService] bei fehlerhafter ID.
    ///
    /// @param id Die fehlerhafte ID
    NotFoundException(final String id) {
        super("Kein Krankenhaus mit der ID " + id + " gefunden.");
        this.id = id;
        suchkriterien = null;
    }

    /// Konstruktor für den [KrankenhausReadService] bei fehlerhaften Suchkriterien.
    ///
    /// @param suchkriterien Die fehlerhaften Suchkriterien
    NotFoundException(final Map<String, List<String>> suchkriterien) {
        super("Keine Krankenhäuser gefunden.");
        id = null;
        this.suchkriterien = suchkriterien;
    }

    /// id ermitteln.
    ///
    /// @return Die fehlerhafte id.
    public String getId() {
        return id;
    }

    /// Suchkriterien ermitteln.
    ///
    /// @return Die fehlerhaften Suchkriterien.
    public Map<String, List<String>> getSuchkriterien() {
        return suchkriterien;
    }
}
