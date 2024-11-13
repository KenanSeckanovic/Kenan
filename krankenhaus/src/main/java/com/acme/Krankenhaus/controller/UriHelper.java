package com.acme.Krankenhaus.controller;

import jakarta.servlet.http.HttpServletRequest;
import java.net.URI;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ProblemDetail;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import static com.acme.Krankenhaus.controller.KrankenhausGetController.API_PATH;

@Component
class UriHelper {
    private static final Logger LOGGER = LoggerFactory.getLogger(UriHelper.class);

    private static final String X_FORWARDED_PROTO = "X-Forwarded-Proto";
    private static final String X_FORWARDED_HOST = "x-forwarded-host";
    private static final String X_FORWARDED_PREFIX = "x-forwarded-prefix";
    private static final String KRANKENHAUSER_PREFIX = "/Krankenhaus";

    UriHelper() {
    }

    URI getBaseUri(final HttpServletRequest request) {
        final var forwardedHost = request.getHeader(X_FORWARDED_HOST);
        if (forwardedHost != null) {
            // Forwarding durch Kubernetes Ingress Controller oder Spring Cloud Gateway
            return getBaseUriForwarded(request, forwardedHost);
        }

        // KEIN Forwarding von einem API-Gateway
        // URI aus Schema, Host, Port und Pfad
        final var uriComponents = ServletUriComponentsBuilder.fromRequestUri(request).build();
        final var baseUri =
            uriComponents.getScheme() + "://" + uriComponents.getHost() + ':' + uriComponents.getPort() + API_PATH;
        LOGGER.debug("getBaseUri (ohne Forwarding): baseUri={}", baseUri);
        return URI.create(baseUri);
    }

    private URI getBaseUriForwarded(final HttpServletRequest request, final String forwardedHost) {
        // x-forwarded-host = Hostname des API-Gateways

        // "https" oder "http"
        final var forwardedProto = request.getHeader(X_FORWARDED_PROTO);
        if (forwardedProto == null) {
            throw new IllegalStateException("Kein '" + X_FORWARDED_PROTO + "' im Header");
        }

        var forwardedPrefix = request.getHeader(X_FORWARDED_PREFIX);
        // x-forwarded-prefix: null bei Kubernetes Ingress Controller bzw. "/kunden" bei Spring Cloud Gateway
        if (forwardedPrefix == null) {
            LOGGER.trace("getBaseUriForwarded: Kein '{}' im Header", X_FORWARDED_PREFIX);
            forwardedPrefix = KRANKENHAUSER_PREFIX;
        }
        final var baseUri = forwardedProto + "://" + forwardedHost + forwardedPrefix + '/' + API_PATH;
        LOGGER.debug("getBaseUriForwarded: baseUri={}", baseUri);
        return URI.create(baseUri);
    }
}
