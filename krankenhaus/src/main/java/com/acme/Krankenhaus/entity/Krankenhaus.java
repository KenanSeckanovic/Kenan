package com.acme.Krankenhaus.entity;

import java.util.List;
import java.util.Objects;
import java.util.UUID;

/**
 * Repräsentiert ein Krankenhaus mit Standort und Patienteninformationen.
 */
public class Krankenhaus {
    private UUID uuid;
    private String email;
    private String name;
    private Lageort lageort;
    private int kapazitaet;
    private List<Patient> patient;

    /**
     * Konstruktor für ein Krankenhaus.
     *
     * @param name       der Name des Krankenhauses
     * @param lageort    der Standort des Krankenhauses
     * @param kapazitaet die maximale Kapazität des Krankenhauses
     * @param patient    der aktuelle Patient im Krankenhaus
     */
    public Krankenhaus(final String email, final String name, final Lageort lageort, final int kapazitaet, final List<Patient> patient) {
        this.uuid = UUID.randomUUID();
        this.email = email;
        this.name = name;
        this.lageort = lageort;
        this.kapazitaet = kapazitaet;
        this.patient = patient;
    }

    /**
     * Gibt die UUID des Krankenhauses zurück.
     *
     * @return UUID des Krankenhauses
     */
    public UUID getUuid() {
        return uuid;
    }

    public void setUuid(final UUID uuid) { this.uuid = uuid;}

    public String getEmail() { return email; }

    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Gibt den Namen des Krankenhauses zurück.
     *
     * @return Name des Krankenhauses
     */
    public String getName() {
        return name;
    }

    /**
     * Setzt den Namen des Krankenhauses.
     *
     * @param name der neue Name des Krankenhauses
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gibt den Standort des Krankenhauses zurück.
     *
     * @return Standort des Krankenhauses
     */
    public Lageort getLageort() {
        return lageort;
    }

    /**
     * Setzt den Standort des Krankenhauses.
     *
     * @param lageort der neue Standort des Krankenhauses
     */
    public void setLageort(Lageort lageort) {
        this.lageort = lageort;
    }

    /**
     * Gibt die Kapazität des Krankenhauses zurück.
     *
     * @return Kapazität des Krankenhauses
     */
    public int getKapazitaet() {
        return kapazitaet;
    }

    /**
     * Setzt die Kapazität des Krankenhauses.
     *
     * @param kapazitaet die neue Kapazität des Krankenhauses
     */
    public void setKapazitaet(int kapazitaet) {
        this.kapazitaet = kapazitaet;
    }

    /**
     * Gibt den aktuellen Patienten im Krankenhaus zurück.
     *
     * @return aktueller Patient
     */
    public List<Patient> getPatient() {
        return patient;
    }

    /**
     * Setzt den aktuellen Patienten im Krankenhaus.
     *
     * @param patient der neue aktuelle Patient
     */
    public void setPatient(List<Patient> patient) {
        this.patient = patient;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Krankenhaus that)) return false;
        return Objects.equals(uuid, that.uuid);
    }

    @Override
    public int hashCode() {
        return Objects.hash(uuid);
    }

    @Override
    public String toString() {
        return "Krankenhaus{" +
            "uuid=" + uuid +
            ", email='" + email + '\'' +
            ", name='" + name + '\'' +
            ", lageort='" + lageort + '\'' +
            ", kapazitaet=" + kapazitaet +
            ", patient=" + patient +
            '}';
    }
}
