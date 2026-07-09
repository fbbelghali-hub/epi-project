package com.example.epi.securite;


import lombok.RequiredArgsConstructor;
import org.springframework.core.convert.converter.Converter;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class JwtAuthConverter implements Converter<Jwt, AbstractAuthenticationToken> {

    private final JwtAuthConverterProperties properties;

    @Override
    public AbstractAuthenticationToken convert(Jwt jwt) {
        Collection<GrantedAuthority> authorities = extractRoles(jwt);
        return new JwtAuthenticationToken(jwt, authorities, jwt.getClaimAsString("preferred_username"));
    }

    private Collection<GrantedAuthority> extractRoles(Jwt jwt) {
        Set<String> roles = new HashSet<>();


        Map<String, Object> realmAccess = jwt.getClaim("realm_access");
        if (realmAccess != null && realmAccess.get("roles") != null) {
            roles.addAll((List<String>) realmAccess.get("roles"));
        }


        Map<String, Object> resourceAccess = jwt.getClaim("resource_access");
        if (resourceAccess != null && properties.getResourceId() != null) {
            Object clientData = resourceAccess.get(properties.getResourceId());
            if (clientData instanceof Map) {
                Map<String, Object> clientAccess = (Map<String, Object>) clientData;
                if (clientAccess.get("roles") != null) {
                    roles.addAll((List<String>) clientAccess.get("roles"));
                }
            }
        }

        return roles.stream()
                .map(role -> new SimpleGrantedAuthority("ROLE_" + role.toUpperCase()))
                .collect(Collectors.toList());
    }
}
