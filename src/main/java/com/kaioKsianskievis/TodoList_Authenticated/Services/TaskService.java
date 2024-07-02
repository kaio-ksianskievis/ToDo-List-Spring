package com.kaioKsianskievis.TodoList_Authenticated.Services;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.kaioKsianskievis.TodoList_Authenticated.Models.Mensagem;
import com.kaioKsianskievis.TodoList_Authenticated.Repositories.TaskRepository;

@Service
public class TaskService {

    //importando os repositórios

    @Autowired
    private TaskRepository taskRepository;   

    @Autowired
    private Mensagem mensagem;
    
    //metodos

    public ResponseEntity<?> buscaTarefas(Integer id){
        if(taskRepository.countByUserId_Id(id) == 0){
            mensagem.setTexto("Nenhuma tarefa encontrada");
            return new ResponseEntity<>(mensagem,HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(taskRepository.findByUserId_Id(id),HttpStatus.OK);
    }
}
