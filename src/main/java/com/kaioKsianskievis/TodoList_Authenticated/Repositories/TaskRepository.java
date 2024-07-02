package com.kaioKsianskievis.TodoList_Authenticated.Repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.kaioKsianskievis.TodoList_Authenticated.Models.Tasks;

@Repository
public interface TaskRepository extends JpaRepository<Tasks,Integer> {
    
    //metodos

    List<Tasks> findByUserId_Id(Integer id);

    //esse metodo procura pelo id que é a chave estrangeira da tabela tasks
}
