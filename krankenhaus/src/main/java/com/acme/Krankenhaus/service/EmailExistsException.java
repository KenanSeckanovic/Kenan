package com.acme.Krankenhaus.service;


public class EmailExistsException extends RuntimeException {

    private final String email;

    EmailExistsException(@SuppressWarnings("ParameterHidesMemberVariable") final String email) {
        super("Die Emailadresse " + email + " existiert bereits");
        this.email = email;
    }

    public String getEmail() {
        return email;
    }
}
