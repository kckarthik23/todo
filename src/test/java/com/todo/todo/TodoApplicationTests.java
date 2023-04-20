package com.todo.todo;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;

import com.todo.todo.Launcher.TodoApplication;
import com.todo.todo.controller.TodosController;
import com.todo.todo.repository.TodoRep;
import com.todo.todo.service.TodoService;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


//@SpringBootTest(classes =TodoApplication.class)
@ContextConfiguration(classes =TodoApplication.class)
@WebMvcTest(TodosController.class)
//@DataJpaTest
@AutoConfigureMockMvc
class TodoApplicationTests {

	@Autowired
	private MockMvc client;

	@MockBean
	private TodoService todoService;


	@Test
	void getTodos() throws Exception {
		client.perform(get("/todos/gettodos")).andExpect(status().isOk());
	}

}
