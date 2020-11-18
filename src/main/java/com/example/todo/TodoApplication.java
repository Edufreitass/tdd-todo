package com.example.todo;

//import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
//import org.springframework.context.annotation.Bean;

//import com.example.todo.model.ToDo;
//import com.example.todo.repository.ToDoRepository;

@SpringBootApplication
public class TodoApplication {

	public static void main(String[] args) {
		SpringApplication.run(TodoApplication.class, args);
	}
	
	/*@Bean
	public CommandLineRunner setup(ToDoRepository toDoRepository) {
		return (args) -> {
			toDoRepository.save(new ToDo("Add a new test case", true));
			toDoRepository.save(new ToDo("Make it fail", true));
			toDoRepository.save(new ToDo("Do changes to the code", false));
			toDoRepository.save(new ToDo("Pass the test", true));
		};
	}*/

}
