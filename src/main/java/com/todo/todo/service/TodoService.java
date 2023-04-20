package com.todo.todo.service;

import java.util.List;
import java.util.Optional;

import com.todo.todo.common.ToDoNotFoundException;
import com.todo.todo.dto.Todo;
import com.todo.todo.repository.*;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

@Service
public class TodoService {

    @Autowired
   private TodoRep todorep;

   

    public List<Todo> getTodos() {
        return todorep.findAll();
    }

    public Todo getTodoById(int id) {
        return todorep.findById(id).orElseThrow(ToDoNotFoundException::new);
    }

    public Todo createTodo(Todo todo) {
      return todorep.saveAndFlush(todo);
    }

    public Todo updateTodo(Todo todo) {
     Todo todoToBeupdated=  todorep.findById(todo.getId()).get();

     if(Optional.ofNullable(todoToBeupdated).isEmpty())
     throw new ToDoNotFoundException();

     todo=todorep.saveAndFlush(todo);
     return todo;
    }


    public Todo deleteTodo(Todo todo) {
        Todo todoDelete=  todorep.findById(todo.getId()).get();
   
        if(Optional.ofNullable(todo).isEmpty())
            throw new ToDoNotFoundException();
           
        
       todorep.delete(todoDelete);
       return todoDelete;
       
       }

}
