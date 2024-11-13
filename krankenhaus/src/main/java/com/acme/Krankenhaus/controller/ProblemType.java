package com.acme.Krankenhaus.controller;

/// Enum für ProblemDetail.type.
///
/// @author [Jürgen Zimmermann](mailto:Juergen.Zimmermann@h-ka.de)
enum ProblemType {
    /// Constraints als Fehlerursache.
    CONSTRAINTS("constraints"),

    /// Fehler, wenn z.B. Emailadresse bereits existiert.
    UNPROCESSABLE("unprocessable"),

    /// Fehler beim Header `If-Match`.
    PRECONDITION("precondition"),

    /// Fehler bei z.B. einer Patch-Operation.
    BAD_REQUEST("badRequest");

    private final String value;

    ProblemType(final String value) {
        this.value = value;
    }

    /// Den internen Wert eines Enums ermitteln.
    ///
    /// @return Der interne Wert
    String getValue() {
        return value;
    }
}
