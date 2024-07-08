# ToDo-List-Spring
## Sobre

Essa é uma API que armazena as tarefas de um usuario logado, nela voçê pode tanto criar um usuario quanto logar com um já existente, o mesmo vale para as tarefas o diferencial é nelas voçê pode criar,deletar,atualizar,ler  as tarefas.
## Dependências

* Spring boot
* Spring Security
* Java-jwt
* Lombok
* Mysql

## Endpoints

Verbo Http | Url | função
:---: | :-----: | :-----:
GET  | http://localhost:8080/tarefas | Mostra as tarefas do usuario
POST  | http://localhost:8080/tarefas | Cria uma nova tarefa
POST  | http://localhost:8080/login | loga o usuario e fornece o mesmo um token
POST  | http://localhost:8080/cadastro | cria um usuario no banco de dados
PUT  | http://localhost:8080/tarefas/id | Atualiza uma tarefa do usuario
DELETE  | http://localhost:8080/tarefas/id | Deleta a tarefas do usuario
