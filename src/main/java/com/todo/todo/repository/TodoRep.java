package com.todo.todo.repository;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import com.todo.todo.dto.Todo;

import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface TodoRep extends JpaRepository<Todo,Integer> {

}
