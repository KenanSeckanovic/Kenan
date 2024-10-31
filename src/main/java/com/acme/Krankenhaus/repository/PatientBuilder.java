package com.acme.Krankenhaus.repository;

import com.acme.Krankenhaus.entity.Patient;

public class PatientBuilder {
    private String vorname;
    private String nachname;
    private int alter;
    private String krankheitsbild;

    public static PatientBuilder getBuilder (){
        return new PatientBuilder();
    }

    public PatientBuilder setVorname(String vorname) {
        this.vorname = vorname;
        return this;
    }

    public PatientBuilder setNachname(String nachname) {
        this.nachname = nachname;
        return this;
    }

    public PatientBuilder setAlter(int alter) {
        this.alter = alter;
        return this;
    }

    public PatientBuilder setKrankheitsbild(String krankheitsbild) {
        this.krankheitsbild = krankheitsbild;
        return this;
    }

    public Patient createPatient() {
        return new Patient(vorname, nachname, alter, krankheitsbild);
    }
}
