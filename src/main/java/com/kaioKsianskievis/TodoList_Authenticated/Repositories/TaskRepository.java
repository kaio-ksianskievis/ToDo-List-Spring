package com.kaioKsianskievis.TodoList_Authenticated.Repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.kaioKsianskievis.TodoList_Authenticated.Models.Tasks;
import com.kaioKsianskievis.TodoList_Authenticated.Models.TasksProjection;

@Repository
public interface TaskRepository extends JpaRepository<Tasks,Integer> {
    
    //metodos
    
    List<TasksProjection> findByUserId_Id(Integer id);

    Integer countByUserId_Id(Integer id);

    Integer countById(Integer id);


    //esse metodo procura pelo id que é a chave estrangeira da tabela tasks
}
