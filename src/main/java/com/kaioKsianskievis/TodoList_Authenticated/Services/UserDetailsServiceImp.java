package com.kaioKsianskievis.TodoList_Authenticated.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import com.kaioKsianskievis.TodoList_Authenticated.Repositories.UserRepository;

@Service
public class UserDetailsServiceImp implements UserDetailsService {

    //importanto o repositorio
    @Autowired
    private UserRepository userRepository;

    //metodo

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        
        return userRepository.findByEmail(username);
    }
    
}
