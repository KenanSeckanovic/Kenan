/*
 * Copyright (C) 2023 - present Juergen Zimmermann, Hochschule Karlsruhe
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package com.acme.Krankenhaus.config;

import org.springframework.aot.hint.RuntimeHints;
import org.springframework.aot.hint.RuntimeHintsRegistrar;
import org.springframework.context.annotation.ImportRuntimeHints;

/**
 * "RuntimeHints" für GraalVM, damit die PEM- und CRT-Dateien zur Laufzeit gefunden werden.
 */
// https://stackoverflow.com/questions/...
// ...76287163/how-to-specify-the-location-of-a-keystore-file-with-spring-aot-processing
@ImportRuntimeHints(CertificateRuntimeHints.CertificateResourcesRegistrar.class)
public class CertificateRuntimeHints {

    /**
     * Standardkonstruktor für die Klasse CertificateRuntimeHints.
     * Wird automatisch erstellt, wenn keine zusätzlichen Initialisierungen benötigt werden.
     */
    public CertificateRuntimeHints() {
        // Standardkonstruktor - keine Logik erforderlich
    }

    /**
     * Registrirung der PEM- und CRT-Dateien für GraalVM.
     */
    static class CertificateResourcesRegistrar implements RuntimeHintsRegistrar {

        /**
         * Standardkonstruktor für die innere Klasse CertificateResourcesRegistrar.
         * Initialisiert die Klasse für die Registrierung der Ressourcenmuster.
         */
        public CertificateResourcesRegistrar() {
            // Standardkonstruktor - keine Logik erforderlich
        }

        /**
         * Registriert Muster für Ressourcen, damit PEM- und CRT-Dateien zur Laufzeit verfügbar sind.
         *
         * @param hints       das {@link RuntimeHints}-Objekt zur Registrierung der Ressourcenmuster
         * @param classLoader der {@link ClassLoader}, der verwendet wird
         */
        @Override
        public void registerHints(final RuntimeHints hints, final ClassLoader classLoader) {
            hints.resources().registerPattern("*.pem");
            hints.resources().registerPattern("*.crt");
        }
    }

}
