package com.acme.Krankenhaus.repository;

import com.acme.Krankenhaus.entity.Krankenhaus;
import com.acme.Krankenhaus.entity.Lageort;
import com.acme.Krankenhaus.entity.Patient;

import java.util.List;

public class KrankenhausBuilder {
    private String name;
    private Lageort lageort;
    private int kapazitaet;
    private List<Patient> patient;

    public static KrankenhausBuilder getBuilder (){
        return new KrankenhausBuilder();
    }

    public KrankenhausBuilder setName(String name) {
        this.name = name;
        return this;
    }

    public KrankenhausBuilder setLageort(Lageort lageort) {
        this.lageort = lageort;
        return this;
    }

    public KrankenhausBuilder setKapazitaet(int kapazitaet) {
        this.kapazitaet = kapazitaet;
        return this;
    }


    public KrankenhausBuilder setPatient(List<Patient> patient) {
        this.patient = patient;
        return this;
    }

    public Krankenhaus createKrankenhaus() {
        return new Krankenhaus(name, lageort, kapazitaet, patient);
    }
}
