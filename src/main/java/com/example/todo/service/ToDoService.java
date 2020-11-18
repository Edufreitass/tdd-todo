package com.example.todo.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.todo.model.ToDo;
import com.example.todo.repository.ToDoRepository;

@Service
public class ToDoService {

	private ToDoRepository toDoRepository;

	public ToDoService(ToDoRepository toDoRepository) {
		this.toDoRepository = toDoRepository;
	}

	public List<ToDo> findAll() {
		return toDoRepository.findAll();
	}

}
