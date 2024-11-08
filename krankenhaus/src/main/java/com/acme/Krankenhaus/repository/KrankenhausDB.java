package com.acme.Krankenhaus.repository;

import com.acme.Krankenhaus.entity.Krankenhaus;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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
