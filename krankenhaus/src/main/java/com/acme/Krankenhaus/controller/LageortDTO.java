package com.acme.Krankenhaus.controller;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

record LageortDTO (
    @NotBlank
    String stadt,

    @NotBlank
    String strasse,

    @NotNull
    @Pattern(regexp = PLZ_PATTERN)
    String plz
) {
    public static final String PLZ_PATTERN = "^\\d{5}$";
}
