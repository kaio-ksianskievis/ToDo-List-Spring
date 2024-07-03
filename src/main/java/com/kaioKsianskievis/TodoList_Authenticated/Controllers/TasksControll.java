package com.kaioKsianskievis.TodoList_Authenticated.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.kaioKsianskievis.TodoList_Authenticated.Models.Tasks;
import com.kaioKsianskievis.TodoList_Authenticated.Services.TaskService;

@RestController
public class TasksControll {
   
    //importando Serviço

    @Autowired
    private TaskService taskService;

    // rotas

    @GetMapping("/tarefas")
    public ResponseEntity<?> buscaTask(){
        return taskService.buscaTarefas();
    }

    @PostMapping("/tarefas")
    public ResponseEntity<?> criaTarefas(@RequestBody Tasks tarefa){
        return taskService.criaTarefas(tarefa);
    }

    @PutMapping("/tarefas/{id}")
    public ResponseEntity<?> atualizaTarefas(@RequestBody Tasks tarefa,@PathVariable Integer id){
        return taskService.atualizaTarefas(tarefa, id);
    }

    @DeleteMapping("tarefas/{id}")
    public ResponseEntity<?> deletaTarefas(@PathVariable Integer id){
        return taskService.deletaTarefas(id);
    }
}   
    
