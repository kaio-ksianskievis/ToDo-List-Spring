package com.kaioKsianskievis.TodoList_Authenticated.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.kaioKsianskievis.TodoList_Authenticated.Models.RecordLogin;
import com.kaioKsianskievis.TodoList_Authenticated.Models.User;
import com.kaioKsianskievis.TodoList_Authenticated.Repositories.UserRepository;
import com.kaioKsianskievis.TodoList_Authenticated.Services.JwtService;

@RestController
public class AuthenticationControll {
    
    //importando repositórios/Serviços

    @Autowired
    private JwtService jwtService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AuthenticationManager authenticationManager;

    //rotas

    @PostMapping("user/login")
    private ResponseEntity<?> login(@RequestBody RecordLogin obj){
        var emailPassword = new UsernamePasswordAuthenticationToken(obj.email(),obj.password());
        var auth = authenticationManager.authenticate(emailPassword);
        String token = jwtService.generateToken((User) auth.getPrincipal());
        return new ResponseEntity<>(token,HttpStatus.OK);
    }

    @PostMapping("user/cadastro")
    private ResponseEntity<?> cadastro(@RequestBody User obj){
        if(userRepository.findByUsername(obj.getUsername()) != null){
            return new ResponseEntity<>("usuario já existente",HttpStatus.BAD_REQUEST);
        }
        String senhaNova = new BCryptPasswordEncoder().encode(obj.getPassword());
        obj.setPassword(senhaNova);
        userRepository.save(obj);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
