package com.acme.Krankenhaus.repository;

import com.acme.Krankenhaus.entity.Krankenhaus;
import com.acme.Krankenhaus.service.KrankenhausReadService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import static com.acme.Krankenhaus.repository.KrankenhausDB.KRANKENHAUSER;
@Repository
public class KrankenhausRepository {

    private static final Logger LOGGER = LoggerFactory.getLogger(KrankenhausRepository.class);

    public KrankenhausRepository() {

    }

    public @NonNull List<Krankenhaus> getAll() {
        return KRANKENHAUSER;
    }

    public @NonNull Optional<Krankenhaus> getByID(@NonNull final String id) {
        LOGGER.debug("Suche nach Krankenhaus mit id: {}", id);

        Optional<Krankenhaus> krankenhaus = KRANKENHAUSER.stream()
            .filter(a -> Objects.equals(id, a.getUuid().toString()))
            .findFirst();

        LOGGER.debug("Ergebnis der Suche nach id {}: {}", id, krankenhaus);
        return krankenhaus;
    }
}
