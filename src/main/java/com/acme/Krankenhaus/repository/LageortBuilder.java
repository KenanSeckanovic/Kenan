package com.acme.Krankenhaus.repository;

import com.acme.Krankenhaus.entity.Lageort;

public class LageortBuilder {
    private String stadt;
    private String strasse;
    private String plz;

    public static LageortBuilder getBuilder (){
        return new LageortBuilder();
    }

    public LageortBuilder setStadt(String stadt) {
        this.stadt = stadt;
        return this;
    }

    public LageortBuilder setStrasse(String strasse) {
        this.strasse = strasse;
        return this;
    }

    public LageortBuilder setPlz(String plz) {
        this.plz = plz;
        return this;
    }

    public Lageort createLageort() {
        return new Lageort(stadt, strasse, plz);
    }
}
