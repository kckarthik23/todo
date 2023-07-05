package com.todo.todo;

import com.todo.todo.dto.Todo;
import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;

import com.todo.todo.Launcher.TodoApplication;
import com.todo.todo.controller.TodosController;
import com.todo.todo.repository.TodoRep;
import com.todo.todo.service.TodoService;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest(classes =TodoApplication.class)
//@ContextConfiguration(classes =TodoApplication.class)
//@WebMvcTest(TodosController.class)
//@DataJpaTest
@TestPropertySource("/application.yml")
//@AutoConfigureMockMvc
class TodoApplicationTests {

	/*@Autowired
	private MockMvc client;*/

	@Autowired
	private TodoService todoService;

	@MockBean
	private TodoRep todorep;

	@Autowired
	private JdbcTemplate jdbc;

	@BeforeEach
public void setUpDatabase()
	{
		System.out.println("executed..........");
		jdbc.execute("insert into todo (description , done , username ,priority , created_date,id)\n" +
				"values ('work on springBoot server inundation,error page , yml changes' , false ,'Karthik K C' ,10,'2023-04-20','173607'); ");
	}


	@Test
	@DisplayName(" test username")
	void getTodos() throws Exception {
		//client.perform(get("/todos/gettodos")).andExpect(status().isOk());
		Optional<Todo> todo= todorep.findById(173607);
		assertNull(todo.get(),"object is null");
	//	assertEquals("Karthik K C",todo.getUsername());
	}

	@AfterEach
public void clearDataBase()
	{
		jdbc.execute("delete from todo");
	}
}
