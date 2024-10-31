package com.acme.Krankenhaus.repository;

import com.acme.Krankenhaus.entity.Krankenhaus;
import java.math.BigDecimal;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import java.time.LocalDate;
import java.util.Currency;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import static java.math.BigDecimal.ZERO;
import static java.util.Collections.emptyList;
import static java.util.Locale.GERMANY;

/// Emulation der Datenbasis für persistente Krankenhausn.
///
@SuppressWarnings({"UtilityClassCanBeEnum", "UtilityClass", "MagicNumber", "RedundantSuppression", "java:S1192"})
final class KrankenhausDB {
    /// Liste der Krankenhausn zur Emulation der DB.
    @SuppressWarnings("StaticCollection")
    static final List<Krankenhaus> KRANKENHAUSER;

    static {
        KRANKENHAUSER = Stream.of(
                // admin
                KrankenhausBuilder.getBuilder()
                    .setKapazitaet(200)
                    .setName("Baden-Baden")
                    .setLageort(
                        LageortBuilder.getBuilder().setStadt("Baden-Baden").setPlz("76532").setStrasse("Kleine Str. 8").createLageort())
                    .setPatient(Stream.of(
                        PatientBuilder.getBuilder().setAlter(80).setKrankheitsbild("Diabetes").setNachname("Jauch").setVorname("Günther").createPatient()
                        ).collect(Collectors.toList())
                    ).createKrankenhaus())
            .collect(Collectors.toList()); // NOSONAR
    }

    private KrankenhausDB() {
    }
}
