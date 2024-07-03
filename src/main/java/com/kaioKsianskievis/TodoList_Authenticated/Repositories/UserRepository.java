package com.kaioKsianskievis.TodoList_Authenticated.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;

import com.kaioKsianskievis.TodoList_Authenticated.Models.User;

@Repository
public interface UserRepository extends JpaRepository<User,Integer>{
    
    //metodos

    UserDetails findByEmail(String email);
}
