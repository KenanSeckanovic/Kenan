package com.acme.Krankenhaus.controller;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

record PatientDTO (
    @NotBlank
    String vorname,

    @NotBlank
    @Pattern(regexp = NACHNAME_PATTERN)
    String nachname,

    @Min(MIN_ALTER)
    @Max(MAX_ALTER)
    int alter,

    @NotBlank
    String krankheitsbild
) {
    public static final String NACHNAME_PATTERN =
        "(o'|von|von der|von und zu|van)?[A-ZÄÖÜ][a-zäöüß]+(-[A-ZÄÖÜ][a-zäöüß]+)?";

    public static final long MIN_ALTER = 18;

    public static final long MAX_ALTER = 100;
}
