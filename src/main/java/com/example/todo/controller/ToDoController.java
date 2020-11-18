package com.example.todo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.todo.model.ToDo;
import com.example.todo.service.ToDoService;

@RestController
public class ToDoController {

	@Autowired
	private ToDoService toDoService;

	@GetMapping("/todos")
	public ResponseEntity<List<ToDo>> getAllTodos() {
		return new ResponseEntity<>(toDoService.findAll(), HttpStatus.OK);
	}

}
