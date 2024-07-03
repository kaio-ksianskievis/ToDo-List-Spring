package com.kaioKsianskievis.TodoList_Authenticated.Services;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.kaioKsianskievis.TodoList_Authenticated.Models.Mensagem;
import com.kaioKsianskievis.TodoList_Authenticated.Models.Tasks;
import com.kaioKsianskievis.TodoList_Authenticated.Models.User;
import com.kaioKsianskievis.TodoList_Authenticated.Repositories.TaskRepository;
import com.kaioKsianskievis.TodoList_Authenticated.Repositories.UserRepository;

@Service
public class TaskService {

    //importando os repositórios

    @Autowired
    private TaskRepository taskRepository;   

    @Autowired
    private Mensagem mensagem;

    @Autowired
    private UserRepository userRepository;
    
    //metodo que encontra usuario logado

    public User findUser(){
        
        var dadosArmazenado =(User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        var usuarioArmazenado = (User) userRepository.findByEmail(dadosArmazenado.getEmail());

        return usuarioArmazenado;
    }


    //metodo de visualizar tarefas

    public ResponseEntity<?> buscaTarefas(){
        
        if(taskRepository.countByUserId_Id(findUser().getId()) == 0){
            mensagem.setTexto("nenhuma tarefa encontrada para este usuario");
            return new ResponseEntity<>(mensagem,HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(taskRepository.findByUserId_Id(findUser().getId()),HttpStatus.OK);
    }
    
    //metodo de criar tarefas

    public ResponseEntity<?> criaTarefas(Tasks tarefa){

        
        tarefa.setUserId(findUser());

        taskRepository.save(tarefa);

        mensagem.setTexto("tarefa criada");

        return new ResponseEntity<>(mensagem,HttpStatus.CREATED);
    }

    //metodo de atualizar tarefas

    public ResponseEntity<?> atualizaTarefas(Tasks novaTarefa,Integer idTarefa){

        if(taskRepository.countById(idTarefa) == 0){
            mensagem.setTexto("Nenhuma tarefa com esse id foi encontrado");
            return new ResponseEntity<>(mensagem,HttpStatus.BAD_REQUEST);
        }


        novaTarefa.setUserId(findUser());

        novaTarefa.setId(idTarefa);
        
        taskRepository.save(novaTarefa);

        mensagem.setTexto("tarefa atualizada");

        return new ResponseEntity<>(mensagem,HttpStatus.OK);

    }

    //metodo que deleta tarefas

    public ResponseEntity<?> deletaTarefas(Integer idTarefa){
        Tasks tasks = taskRepository.findById(idTarefa).orElseThrow();

        taskRepository.delete(tasks);
        mensagem.setTexto("tarefa deletada");
        return new ResponseEntity<>(mensagem,HttpStatus.OK);
    }
}
