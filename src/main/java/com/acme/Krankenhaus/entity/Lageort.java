package com.acme.Krankenhaus.entity;

import java.util.Objects;

/**
 * Repräsentiert den Standort eines Krankenhauses mit Stadt, Straße und Postleitzahl.
 */
public class Lageort {
    private String stadt;
    private String strasse;
    private String plz;

    /**
     * Konstruktor für einen Lageort.
     *
     * @param stadt   die Stadt, in der sich der Lageort befindet
     * @param strasse die Straße des Lageorts
     * @param plz     die Postleitzahl des Lageorts
     */
    public Lageort(final String stadt, final String strasse, final String plz) {
        this.stadt = stadt;
        this.strasse = strasse;
        this.plz = plz;
    }

    /**
     * Gibt die Stadt des Lageorts zurück.
     *
     * @return Stadt des Lageorts
     */
    public String getStadt() {
        return stadt;
    }

    /**
     * Setzt die Stadt des Lageorts.
     *
     * @param stadt die neue Stadt des Lageorts
     */
    public void setStadt(String stadt) {
        this.stadt = stadt;
    }

    /**
     * Gibt die Straße des Lageorts zurück.
     *
     * @return Straße des Lageorts
     */
    public String getStrasse() {
        return strasse;
    }

    /**
     * Setzt die Straße des Lageorts.
     *
     * @param strasse die neue Straße des Lageorts
     */
    public void setStrasse(String strasse) {
        this.strasse = strasse;
    }

    /**
     * Gibt die Postleitzahl (PLZ) des Lageorts zurück.
     *
     * @return PLZ des Lageorts
     */
    public String getPlz() {
        return plz;
    }

    /**
     * Setzt die Postleitzahl (PLZ) des Lageorts.
     *
     * @param plz die neue PLZ des Lageorts
     */
    public void setPlz(String plz) {
        this.plz = plz;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Lageort lageort)) return false;
        return Objects.equals(stadt, lageort.stadt) &&
            Objects.equals(strasse, lageort.strasse) &&
            Objects.equals(plz, lageort.plz);
    }

    @Override
    public int hashCode() {
        return Objects.hash(stadt, strasse, plz);
    }

    @Override
    public String toString() {
        return "Lageort{" +
            "stadt='" + stadt + '\'' +
            ", strasse='" + strasse + '\'' +
            ", plz='" + plz + '\'' +
            '}';
    }
}
