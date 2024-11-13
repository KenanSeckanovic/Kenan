package com.acme.Krankenhaus.controller;

import com.acme.Krankenhaus.entity.Lageort;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.springframework.lang.NonNull;

import java.util.List;

record KrankenhausDTO (
    @Email
    @NotNull
    String email,

    @NotBlank
    String name,

    @NotNull
    Lageort lageort,

    @Min(MIN_KAPAZITAET)
    @Max(MAX_KAPAZITAET)
    int kapazitaet,

    List<PatientDTO> patient
) {
    public static final long MIN_KAPAZITAET = 100;

    public static final long MAX_KAPAZITAET = 1000;
}
