package com.kaioKsianskievis.TodoList_Authenticated.Services;

import java.time.Instant;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.kaioKsianskievis.TodoList_Authenticated.Models.User;

@Service
public class JwtService {

    //valores do application.properties
    @Value("${api.security.token.secret}")
    private  String secret;

    @Value("${api.security.token.issue}")
    private  String issue;

    //metodos para autenticação

    public String generateToken(User obj){
        try{
            Algorithm algorithm = Algorithm.HMAC256(secret);
            String token = JWT.create()
                .withIssuer(issue)
                .withIssuedAt(Instant.now())
                .withExpiresAt(Instant.now().plusSeconds(10800))
                .withSubject(obj.getEmail())
                .sign(algorithm);
            return token;
                
        }catch(JWTCreationException exception){
            throw new JWTCreationException("erro ao gerar token", exception);
        }
    }

    public String validateToken(String token){
        try{
            Algorithm algorithm = Algorithm.HMAC256(secret);
            String auth = JWT.require(algorithm)
                .withIssuer(issue)
                .build()
                .verify(token)
                .getSubject();
            return auth;
                
        }catch(JWTCreationException exception){
            return null;
        }
    }

    
}
