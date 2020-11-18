package com.example.todo.controller;

import com.example.todo.model.ToDo;
import com.example.todo.service.ToDoService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest
public class ToDoControllerTest {

	@Autowired
	MockMvc mockMvc;
	
	@MockBean
	private ToDoService toDoService;
	
	@Test
	void getAllTodos() throws Exception {
		List<ToDo> toDoList = new ArrayList<>();
		toDoList.add(new ToDo(1L, "Eat thrice", true));
		toDoList.add(new ToDo(2L, "Sleep Twice", true));
		when(toDoService.findAll()).thenReturn(toDoList);
		
		mockMvc.perform(MockMvcRequestBuilders.get("/todos")
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(jsonPath("$", hasSize(2))).andDo(print());
	}
	
	@Test
	void successfullyCreateAToDo() throws Exception {
		// Arrange
		ToDo eatToDo = new ToDo(1L, "Eat thrice", true);
        when(toDoService.save(any(ToDo.class))).thenReturn(eatToDo);
        ObjectMapper objectMapper = new ObjectMapper();
        String eatToDoJSON = objectMapper.writeValueAsString(eatToDo);
		
		// Act
		ResultActions result = mockMvc.perform(post("/todos")
				.contentType(MediaType.APPLICATION_JSON)
				.content(eatToDoJSON)
		);
		
		// Assert
		result.andExpect(status().isCreated())
				.andExpect(jsonPath("$.text").value("Eat thrice"))
				.andExpect(jsonPath("$.completed").value(true));
		
	}
	
}
