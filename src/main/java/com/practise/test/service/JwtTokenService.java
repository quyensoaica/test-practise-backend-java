package com.practise.test.service;

import com.nimbusds.jose.*;
import com.nimbusds.jose.crypto.MACSigner;
import com.nimbusds.jose.crypto.MACVerifier;
import com.nimbusds.jwt.JWTClaimsSet;
import com.practise.test.model.authorization.AccessToken;
import com.practise.test.model.authorization.TokenPayload;
import lombok.experimental.NonFinal;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;

@Service
public class JwtTokenService {

    @NonFinal
    protected static final String SIGNER_KEY = "uINhVKYiBr0wmzDA2ngkPkoKDl7aWxsTuINhVKYiBr0wmzDA2ngkPkoKDl7aWxsT";
    private static final Logger log = LoggerFactory.getLogger(JwtTokenService.class);

    public AccessToken generateToken(TokenPayload payload) {
        JWSHeader header = new JWSHeader(JWSAlgorithm.HS512);

        Date issueTime = new Date();
        Date expirationTime = new Date(
                Instant.now().plus(1, ChronoUnit.HOURS).toEpochMilli()
        );

        JWTClaimsSet jwtClaimsSet = new JWTClaimsSet.Builder()
                .issuer("dev.com")
                .issueTime(issueTime)
                .expirationTime(expirationTime)
                .claim("userId", payload.getUserId())
                .claim("username", payload.getUsername())
                .claim("role", payload.getRole())
                .claim("roleName", payload.getRoleName())
                .build();
        Payload tokenPayload = new Payload(jwtClaimsSet.toJSONObject());

        JWSObject jwsObject = new JWSObject(header, tokenPayload);

        try {
            jwsObject.sign(new MACSigner(SIGNER_KEY.getBytes()));
            // Tạo AccessToken
            String token = jwsObject.serialize(); // Token dạng chuỗi
            String expiresAt = jwtClaimsSet.getExpirationTime().toInstant().toString(); // ISO-8601
            String expiresAtUtc = jwtClaimsSet.getExpirationTime().toInstant().toString(); // UTC

            return new AccessToken(token, expiresAt, expiresAtUtc);
        } catch (JOSEException e) {
            log.error("Error => ");
            throw new RuntimeException(e);
        }
    }

    public boolean verifyToken(String token) {
        try {
            JWSObject jwsObject = JWSObject.parse(token);
            return jwsObject.verify(new MACVerifier(SIGNER_KEY.getBytes()));
        } catch (JOSEException e) {
            throw new RuntimeException(e);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }

    public TokenPayload decodeToken(String token) {
        try {
            JWSObject jwsObject = JWSObject.parse(token);
            JWTClaimsSet jwtClaimsSet = JWTClaimsSet.parse(jwsObject.getPayload().toJSONObject());
            return new TokenPayload(
                    jwtClaimsSet.getStringClaim("userId"),
                    jwtClaimsSet.getStringClaim("username"),
                    jwtClaimsSet.getStringClaim("role"),
                    jwtClaimsSet.getStringClaim("roleName")
            );
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }

    public String extractUserId(String token) {
        try {
            String jwt = token.substring(7); // Remove "Bearer " prefix
            JWSObject jwsObject = JWSObject.parse(jwt);
            JWTClaimsSet claims = JWTClaimsSet.parse(jwsObject.getPayload().toJSONObject());
            return claims.getStringClaim("userId");
        } catch (Exception e) {
            return null;
        }
    }

}
