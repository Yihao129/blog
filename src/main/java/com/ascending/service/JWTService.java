package com.ascending.service;

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
        JwtBuilder builder = Jwts.builder().setClaims(claims).signWith(alg,signingKey);

        return builder.compact();
    }

    public User decodeToken(String token){

        return null;
    }

}
