package com.example.todo.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.todo.model.ToDo;
import com.example.todo.repository.ToDoRepository;

@SpringBootTest
class ToDoServiceTest {

	/*
	 * The structure of a test case. It follows AAA pattern(Arrange, Act, Assert).
	 * 
	 * Arrange: This is the first step of a unit test. Here we will arrange the
	 * test, in other words we will do the necessary setup of the test. For example,
	 * to perform the test we need to create an object of the targeted class, if
	 * necessary, then we need to create mock objects and other variable
	 * initialization, something like these.
	 * 
	 * Act: In this step we will execute the test. In other words we will do the
	 * actual unit testing and the result will be obtained from the test
	 * application. Basically we will call the targeted function in this step using
	 * the object that we created in the previous step.
	 * 
	 * Assert: This is the last step of a unit test. In this step we will check and
	 * verify the returned result with expected results.
	 */

	/* Repository with @MockBean */

//	@MockBean
//	private ToDoRepository toDoRepository;
//
//	@Test
//	void getAllToDos() {
//		List<ToDo> toDoList = new ArrayList<ToDo>();
//		toDoList.add(new ToDo(1L, "Todo Sample 1", true));
//		toDoList.add(new ToDo(2L, "Todo Sample 2", true));
//		toDoList.add(new ToDo(3L, "Todo Sample 3", false));
//		when(toDoRepository.findAll()).thenReturn(toDoList);
//		ToDoService toDoService = new ToDoService(toDoRepository);
//
//		List<ToDo> result = toDoService.findAll();
//
//		assertEquals(3, result.size());
//	}

	/* Repository with @Autowired */

	@Autowired
	private ToDoRepository toDoRepository;

	@AfterEach
	void tearDown() {
		toDoRepository.deleteAll();
	}

	@Test
	void getAllToDos() {
		ToDo todoSample = new ToDo("Todo Sample 1", true);
		toDoRepository.save(todoSample);
		ToDoService toDoService = new ToDoService(toDoRepository);

		List<ToDo> toDoList = toDoService.findAll();
		ToDo lastToDo = toDoList.get(toDoList.size() - 1);

		assertEquals(todoSample.getText(), lastToDo.getText());
		assertEquals(todoSample.isCompleted(), lastToDo.isCompleted());
		assertEquals(todoSample.getId(), lastToDo.getId());
	}

//	@Test
//	void getAllToDos() {
//		ToDo todoSample = new ToDo("Todo Sample 1", true);
//		toDoRepository.save(todoSample);
//		ToDoService toDoService = new ToDoService(toDoRepository);
//
//		ToDo firstResult = toDoService.findAll().get(0);
//
//		assertEquals(todoSample.getText(), firstResult.getText());
//		assertEquals(todoSample.isCompleted(), firstResult.isCompleted());
//		assertEquals(todoSample.getId(), firstResult.getId());
//	}

	@Test
	void saveAToDo() {
		ToDoService toDoService = new ToDoService(toDoRepository);
		ToDo todoSample = new ToDo("Todo Sample 1", true);

		toDoService.save(todoSample);

		assertEquals(1.0, toDoRepository.count());
	}

}
