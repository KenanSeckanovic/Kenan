package com.acme.Krankenhaus.service;

import com.acme.Krankenhaus.entity.Krankenhaus;
import com.acme.Krankenhaus.repository.KrankenhausRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class KrankenhausReadService {

    private static final Logger LOGGER = LoggerFactory.getLogger(KrankenhausReadService.class);
    private final KrankenhausRepository krankenhausRepository;

    public KrankenhausReadService( final KrankenhausRepository krankenhausRepository) {
        this.krankenhausRepository = krankenhausRepository;
    }

    public @NonNull List<Krankenhaus> getAll() {
        LOGGER.debug("Starte Abruf aller Krankenhaus");

        List<Krankenhaus> krankenhauser = krankenhausRepository.getAll();
        if (krankenhauser.isEmpty()) {
            throw new IllegalArgumentException("Keine Autohäuser in der Datenbank gefunden.");
        }

        return krankenhauser;
    }

    public @NonNull Krankenhaus getByID(String id) {
        LOGGER.debug("Starte Suche nach Krankenhaus mit id: {}", id);

        Krankenhaus krankenhaus = krankenhausRepository.getByID(id)
            .orElseThrow(() -> new IllegalArgumentException("Kein Krankenhaus für die angegebene id gefunden."));

        LOGGER.debug("Suche nach Krankenhaus mit id beendet");
        return krankenhaus;
    }
}
