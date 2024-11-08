package com.acme.Krankenhaus.repository;

import com.acme.Krankenhaus.entity.Lageort;

/**
 * Builder-Klasse zur Erstellung eines {@link Lageort}-Objekts.
 * Ermöglicht die schrittweise Konfiguration der Attribute eines Lageorts.
 */
public class LageortBuilder {
    private String stadt;
    private String strasse;
    private String plz;

    /**
     * Standardkonstruktor für die Instanz von LageortBuilder.
     */
    public LageortBuilder() {
        // Leerer Konstruktor
    }

    /**
     * Gibt eine neue Instanz des {@link LageortBuilder} zurück.
     *
     * @return eine neue Instanz des Builders
     */
    public static LageortBuilder getBuilder (){
        return new LageortBuilder();
    }

    /**
     * Setzt die Stadt des Lageorts.
     *
     * @param stadt die Stadt des Lageorts
     * @return die aktuelle Instanz des Builders
     */
    public LageortBuilder setStadt(String stadt) {
        this.stadt = stadt;
        return this;
    }

    /**
     * Setzt die Straße des Lageorts.
     *
     * @param strasse die Straße des Lageorts
     * @return die aktuelle Instanz des Builders
     */
    public LageortBuilder setStrasse(String strasse) {
        this.strasse = strasse;
        return this;
    }

    /**
     * Setzt die Postleitzahl des Lageorts.
     *
     * @param plz die Postleitzahl des Lageorts
     * @return die aktuelle Instanz des Builders
     */
    public LageortBuilder setPlz(String plz) {
        this.plz = plz;
        return this;
    }

    /**
     * Erstellt ein neues {@link Lageort}-Objekt mit den angegebenen Attributen.
     *
     * @return ein neues Lageort-Objekt
     */
    public Lageort createLageort() {
        return new Lageort(stadt, strasse, plz);
    }
}
