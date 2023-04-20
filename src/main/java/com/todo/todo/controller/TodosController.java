package com.todo.todo.controller;

import java.net.URI;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.todo.todo.dto.Todo;
import com.todo.todo.service.TodoService;

@RestController
@RequestMapping("/todos")
public class TodosController {

    @Autowired
    TodoService todoService;

    @GetMapping("/gettodos")
    public ResponseEntity<List<Todo>> getTodos() {
        List<Todo> todos = todoService.getTodos();
        if (!todos.isEmpty())
            return ResponseEntity.ok(todos);
        else
            return ResponseEntity.of(Optional.empty());
    }

    @GetMapping("gettodo/{id}")
    public ResponseEntity<Todo> getTodo(@PathVariable int id) {
        Todo result = todoService.getTodoById(id);

        if (Optional.ofNullable(result).isPresent())
            return ResponseEntity.ok(result);
        else
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    @PostMapping("createtodo")
    public ResponseEntity<Todo> createTodo(@RequestBody Todo todo) {
        Todo result = todoService.createTodo(todo);

        if (Optional.ofNullable(result).isPresent()) {
            URI location = ServletUriComponentsBuilder.fromCurrentRequest().replacePath("todos/gettodo").path("/{id}").buildAndExpand(todo.getId()).
                    toUri();
            return ResponseEntity.status(HttpStatus.CREATED).location(location).build();

        }

        else
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }


    @PutMapping("updatetodo")
    public ResponseEntity<Todo> updatetodo(@RequestBody Todo todo) {
        Todo result = todoService.updateTodo(todo);

        if (Optional.ofNullable(result).isPresent()) {
            URI location = ServletUriComponentsBuilder.fromCurrentRequest().replacePath("todos/gettodo").path("/{id}").buildAndExpand(todo.getId()).
                    toUri();
            return ResponseEntity.status(HttpStatus.CREATED).location(location).body(result);

        }

        else
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    @DeleteMapping("/delete/todo")
    public ResponseEntity<Todo> deleteTodo(@RequestBody Todo todo) {
        Todo deleted = todoService.deleteTodo(todo);
        if (Optional.of(deleted).isEmpty())
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(todo);

        return ResponseEntity.status(HttpStatus.ACCEPTED).body(deleted);
    }

}
