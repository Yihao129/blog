package com.ascending.service;

import com.ascending.model.Role;
import com.ascending.model.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;
import java.security.Key;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class JWTService {
    private Logger logger = (Logger) LoggerFactory.getLogger(this.getClass());
    private final String SECRET_KEY = "wang yang";
    private final String ISSUER = "com.ascending";
    private final long EXPIRATION = 3600*24*1000;

    public String generateToken(User user){
        SignatureAlgorithm alg = SignatureAlgorithm.HS256;
        byte[] apiKeySecret = DatatypeConverter.parseBase64Binary(SECRET_KEY);
        Key signingKey = new SecretKeySpec(apiKeySecret,alg.getJcaName());

        Claims claims = Jwts.claims();
        claims.setId(String.valueOf(user.getId()));
        claims.setSubject(user.getName());
        claims.setIssuedAt(new Date(System.currentTimeMillis()));
        claims.setIssuer(ISSUER);
        claims.setExpiration(new Date(System.currentTimeMillis()+EXPIRATION));

        List<Role> roles = user.getRoles();
        String allowedReadResources = "";
        String allowedCreateResources = "";
        String allowedUpdateResources = "";
        String allowedDeleteResources = "";
        String allowedResource = roles.stream().map(role -> role.getAllowedResource()).collect(Collectors.joining(","));
        claims.put("allowedResource", allowedResource);
        for (Role role : roles) {
            if (role.isAllowedRead()) allowedReadResources = String.join(role.getAllowedResource(), allowedReadResources, ",");
            if (role.isAllowedCreate()) allowedCreateResources = String.join(role.getAllowedResource(), allowedCreateResources, ",");
            if (role.isAllowedUpdate()) allowedUpdateResources = String.join(role.getAllowedResource(), allowedUpdateResources, ",");
            if (role.isAllowedDelete()) allowedDeleteResources = String.join(role.getAllowedResource(), allowedDeleteResources, ",");
        }
        claims.put("allowedReadResources", allowedReadResources.replaceAll(".$", ""));
        claims.put("allowedCreateResources", allowedCreateResources.replaceAll(".$", ""));
        claims.put("allowedUpdateResources", allowedUpdateResources.replaceAll(".$", ""));
        claims.put("allowedDeleteResources", allowedDeleteResources.replaceAll(".$", ""));

        JwtBuilder builder = Jwts.builder().setClaims(claims).signWith(alg,signingKey);

        return builder.compact();
    }

    public Claims decodeToken(String token){
        Claims claims = Jwts.parser()
                .setSigningKey(DatatypeConverter.parseBase64Binary(SECRET_KEY))
                .parseClaimsJws(token).getBody();

        return claims;
    }


}
