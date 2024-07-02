package com.kaioKsianskievis.TodoList_Authenticated.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.kaioKsianskievis.TodoList_Authenticated.Services.TaskService;

@RestController
public class TasksControll {
   
    //importando Serviço

    @Autowired
    private TaskService taskService;

    // rotas

    @GetMapping("/{id}")
    public ResponseEntity<?> buscaTask(@PathVariable Integer id){
        return taskService.buscaTarefas(id);
    }
}
