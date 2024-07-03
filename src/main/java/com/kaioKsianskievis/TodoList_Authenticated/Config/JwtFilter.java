package com.kaioKsianskievis.TodoList_Authenticated.Config;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.kaioKsianskievis.TodoList_Authenticated.Repositories.UserRepository;
import com.kaioKsianskievis.TodoList_Authenticated.Services.JwtService;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class JwtFilter extends OncePerRequestFilter {
    
    //importando serviços
    @Autowired
    private JwtService jwtService;

    @Autowired
    private UserRepository userRepository;

    //metodo pega o token

    public String takeTokeFromHeader(HttpServletRequest request){
        String token = request.getHeader("Authorization");
        if(token!= null){
            return token.replace("Bearer ", "");
        }
        return null;
    }

    // metodo do filtro

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)throws ServletException, IOException {
        String token = takeTokeFromHeader(request);
        if(token != null){
            String validacao = jwtService.validateToken(token);
            UserDetails user = userRepository.findByEmail(validacao);
            var auth = new UsernamePasswordAuthenticationToken(user,null ,user.getAuthorities());
            SecurityContextHolder.getContext().setAuthentication(auth);
        }
        filterChain.doFilter(request, response);
    }
    
}
