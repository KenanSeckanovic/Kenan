package com.acme.Krankenhaus.service;

import com.acme.Krankenhaus.entity.Krankenhaus;
import com.acme.Krankenhaus.repository.KrankenhausRepository;
import jakarta.validation.Valid;
import java.util.Objects;
import java.util.UUID;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

// Maven: ![Klassendiagramm](../../../../../../generated-docs/KundeWriteService.svg)
@Service
public class KrankenhausWriteService {
    private static final Logger LOGGER = LoggerFactory.getLogger(KrankenhausWriteService.class);

    private final KrankenhausRepository repo;

    KrankenhausWriteService(final KrankenhausRepository repo) {
        this.repo = repo;
    }

    public Krankenhaus create(final Krankenhaus krankenhaus) {
        LOGGER.debug("create: {}", krankenhaus);

        if (repo.isEmailExisting(krankenhaus.getEmail())) {
            throw new EmailExistsException(krankenhaus.getEmail());
        }

        final var krankenhausDB = repo.create(krankenhaus);
        LOGGER.debug("create: {}", krankenhausDB);
        return krankenhausDB;
    }

    public void update(@Valid final Krankenhaus krankenhaus, final UUID id) {
        LOGGER.debug("update: {}", krankenhaus);
        LOGGER.debug("update: id={}", id);

        // Ist die neue Email bei einem///ANDEREN* Kunden vorhanden?
        final var email = krankenhaus.getEmail();
        final var krankenhausDb = repo
            .findByID(id.toString())
            .orElseThrow(() -> new NotFoundException(id.toString()));
        if (!Objects.equals(email, krankenhausDb.getEmail()) && repo.isEmailExisting(email)) {
            LOGGER.debug("update: lageort {} existiert", email);
            throw new EmailExistsException(email);
        }

        krankenhaus.setUuid(id);
        repo.update(krankenhaus);
    }

    public void deleteById(final UUID id) {
        LOGGER.debug("deleteById: id={}", id);
        repo.deleteById(id);
    }
}
