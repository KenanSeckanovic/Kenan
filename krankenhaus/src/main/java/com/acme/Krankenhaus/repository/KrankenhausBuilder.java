package com.acme.Krankenhaus.repository;

import com.acme.Krankenhaus.entity.Krankenhaus;
import com.acme.Krankenhaus.entity.Lageort;
import com.acme.Krankenhaus.entity.Patient;

import java.util.List;

/**
 * Builder-Klasse zur Erstellung eines {@link Krankenhaus}-Objekts.
 * Ermöglicht die schrittweise Konfiguration der Attribute eines Krankenhauses.
 */
public class KrankenhausBuilder {

    private String name;
    private Lageort lageort;
    private int kapazitaet;
    private List<Patient> patient;

    /**
     * Standardkonstruktor für die Klasse {@link KrankenhausBuilder}.
     * Initialisiert eine neue Instanz des Builders ohne Voreinstellungen.
     */
    public KrankenhausBuilder() {
        // Standardkonstruktor
    }

    /**
     * Gibt eine neue Instanz des {@link KrankenhausBuilder} zurück.
     *
     * @return eine neue Instanz des Builders
     */
    public static KrankenhausBuilder getBuilder (){
        return new KrankenhausBuilder();
    }

    /**
     * Setzt den Namen des Krankenhauses.
     *
     * @param name der Name des Krankenhauses
     * @return die aktuelle Instanz des Builders
     */
    public KrankenhausBuilder setName(String name) {
        this.name = name;
        return this;
    }

    /**
     * Setzt den Lageort des Krankenhauses.
     *
     * @param lageort der Standort des Krankenhauses
     * @return die aktuelle Instanz des Builders
     */
    public KrankenhausBuilder setLageort(Lageort lageort) {
        this.lageort = lageort;
        return this;
    }

    /**
     * Setzt die Kapazität des Krankenhauses.
     *
     * @param kapazitaet die Kapazität des Krankenhauses
     * @return die aktuelle Instanz des Builders
     */
    public KrankenhausBuilder setKapazitaet(int kapazitaet) {
        this.kapazitaet = kapazitaet;
        return this;
    }

    /**
     * Setzt die Liste der Patienten im Krankenhaus.
     *
     * @param patient die Patientenliste des Krankenhauses
     * @return die aktuelle Instanz des Builders
     */
    public KrankenhausBuilder setPatient(List<Patient> patient) {
        this.patient = patient;
        return this;
    }

    /**
     * Erstellt ein neues {@link Krankenhaus}-Objekt mit den angegebenen Attributen.
     *
     * @return ein neues Krankenhaus-Objekt
     */
    public Krankenhaus createKrankenhaus() {
        return new Krankenhaus(name, lageort, kapazitaet, patient);
    }
}
