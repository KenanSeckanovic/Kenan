package com.acme.Krankenhaus.repository;

import com.acme.Krankenhaus.entity.Patient;

/**
 * Builder-Klasse zur Erstellung eines {@link Patient}-Objekts.
 * Ermöglicht die schrittweise Konfiguration der Attribute eines Patienten.
 */
public class PatientBuilder {
    private String vorname;
    private String nachname;
    private int alter;
    private String krankheitsbild;

    /**
     * Standardkonstruktor für die Instanz von PatientBuilder.
     */
    public PatientBuilder() {
        // Leerer Konstruktor
    }

    /**
     * Gibt eine neue Instanz des {@link PatientBuilder} zurück.
     *
     * @return eine neue Instanz des Builders
     */
    public static PatientBuilder getBuilder (){
        return new PatientBuilder();
    }

    /**
     * Setzt den Vornamen des Patienten.
     *
     * @param vorname der Vorname des Patienten
     * @return die aktuelle Instanz des Builders
     */
    public PatientBuilder setVorname(String vorname) {
        this.vorname = vorname;
        return this;
    }

    /**
     * Setzt den Nachnamen des Patienten.
     *
     * @param nachname der Nachname des Patienten
     * @return die aktuelle Instanz des Builders
     */
    public PatientBuilder setNachname(String nachname) {
        this.nachname = nachname;
        return this;
    }

    /**
     * Setzt das Alter des Patienten.
     *
     * @param alter das Alter des Patienten
     * @return die aktuelle Instanz des Builders
     */
    public PatientBuilder setAlter(int alter) {
        this.alter = alter;
        return this;
    }

    /**
     * Setzt das Krankheitsbild des Patienten.
     *
     * @param krankheitsbild das Krankheitsbild des Patienten
     * @return die aktuelle Instanz des Builders
     */
    public PatientBuilder setKrankheitsbild(String krankheitsbild) {
        this.krankheitsbild = krankheitsbild;
        return this;
    }

    /**
     * Erstellt ein neues {@link Patient}-Objekt mit den angegebenen Attributen.
     *
     * @return ein neues Patient-Objekt
     */
    public Patient createPatient() {
        return new Patient(vorname, nachname, alter, krankheitsbild);
    }
}
