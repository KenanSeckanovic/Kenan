package com.acme.Krankenhaus.entity;

import java.util.Objects;

/**
 * Repräsentiert einen Patienten mit Vorname, Nachname, Alter und Krankheitsbild.
 */
public class Patient {
    private String vorname;
    private String nachname;
    private int alter;
    private String krankheitsbild;

    /**
     * Konstruktor für einen Patienten.
     *
     * @param vorname       der Vorname des Patienten
     * @param nachname      der Nachname des Patienten
     * @param alter         das Alter des Patienten
     * @param krankheitsbild das Krankheitsbild des Patienten
     */
    public Patient(final String vorname, final String nachname, final int alter, final String krankheitsbild) {
        this.vorname = vorname;
        this.nachname = nachname;
        this.alter = alter;
        this.krankheitsbild = krankheitsbild;
    }

    /**
     * Gibt den Vornamen des Patienten zurück.
     *
     * @return Vorname des Patienten
     */
    public String getVorname() {
        return vorname;
    }

    /**
     * Setzt den Vornamen des Patienten.
     *
     * @param vorname der neue Vorname des Patienten
     */
    public void setVorname(String vorname) {
        this.vorname = vorname;
    }

    /**
     * Gibt den Nachnamen des Patienten zurück.
     *
     * @return Nachname des Patienten
     */
    public String getNachname() {
        return nachname;
    }

    /**
     * Setzt den Nachnamen des Patienten.
     *
     * @param nachname der neue Nachname des Patienten
     */
    public void setNachname(String nachname) {
        this.nachname = nachname;
    }

    /**
     * Gibt das Alter des Patienten zurück.
     *
     * @return Alter des Patienten
     */
    public int getAlter() {
        return alter;
    }

    /**
     * Setzt das Alter des Patienten.
     *
     * @param alter das neue Alter des Patienten
     */
    public void setAlter(int alter) {
        this.alter = alter;
    }

    /**
     * Gibt das Krankheitsbild des Patienten zurück.
     *
     * @return Krankheitsbild des Patienten
     */
    public String getKrankheitsbild() {
        return krankheitsbild;
    }

    /**
     * Setzt das Krankheitsbild des Patienten.
     *
     * @param krankheitsbild das neue Krankheitsbild des Patienten
     */
    public void setKrankheitsbild(String krankheitsbild) {
        this.krankheitsbild = krankheitsbild;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Patient patient = (Patient) o;
        return alter == patient.alter &&
            Objects.equals(vorname, patient.vorname) &&
            Objects.equals(nachname, patient.nachname) &&
            Objects.equals(krankheitsbild, patient.krankheitsbild);
    }

    @Override
    public int hashCode() {
        return Objects.hash(vorname, nachname, alter, krankheitsbild);
    }

    @Override
    public String toString() {
        return "Patient{" +
            "vorname='" + vorname + '\'' +
            ", nachname='" + nachname + '\'' +
            ", alter=" + alter +
            ", krankheitsbild='" + krankheitsbild + '\'' +
            '}';
    }
}
