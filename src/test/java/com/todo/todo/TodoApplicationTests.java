package com.todo.todo;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.*;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.http.MediaType;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;

import com.todo.todo.Launcher.TodoApplication;
import com.todo.todo.controller.TodosController;
import com.todo.todo.repository.TodoRep;
import com.todo.todo.service.TodoService;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


//@SpringBootTest(classes =TodoApplication.class)
//@ContextConfiguration(classes =TodoApplication.class)
//@WebMvcTest(TodosController.class)
//@EnableJpaRepositories
@AutoConfigureMockMvc
//@Transactional
@SpringBootTest(classes = TodoApplication.class)
class TodoApplicationTests {

	/*@Autowired
	private MockMvc client;*/

	@Autowired
	private MockMvc client;

	private static MockHttpServletRequest request;

	//@PersistenceContext
	//EntityManager enm;


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


//	@Mock
//	private TodoService todoService;

	//@Autowired
	//private TodoRep repo;

@BeforeAll
public static void setUpRequest()
{
	request=new MockHttpServletRequest();
	request.setParameter("id","173608");
	request.setParameter("done","yes");
	request.setParameter("description","task of creating issues in jira");
	request.setParameter("createdDate","2023-05-20");
	request.setParameter("priority","5");
	request.setParameter("username","Avanish");
}
	@Test
	@DisplayName("Test with get call")
	void getTodos() throws Exception {
		MvcResult result=client
				.perform(get("/todos/gettodos"))
				.andExpect(status().isOk())
				.andReturn();
		System.out.println("get call :"+ result.getResponse().getContentAsString());
	}

	@Test
	@DisplayName("Test with get call by id")
	void getTodosById() throws Exception {
		client.perform(get("/todos/gettodo/{id}",173609))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.id").value(173609));
	}

	@Test
	@DisplayName("Test with Post with todo")
	void createTodo() throws Exception {
	String requestBody="{\"id\":173608,\"done\":\"true\"," +
			"\"description\":\"task of creating issues in jira\"," +
			"\"createdDate\":\"2023-05-26\",\"priority\":5," +
			"\"username\":\"Avanish Chandra\"}";
		MvcResult result= client.perform(post("/todos/createtodo")
						.contentType(MediaType.APPLICATION_JSON)
						.content(requestBody)
						.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isCreated()).andReturn();

		System.out.println("post call :"+result.getResponse().getContentAsString());
	}

    /*
    @Autowired
    TodoRep repo;
    @Autowired
    JdbcTemplate jdbc;


    @BeforeEach
    public void databaseInsert() {
        jdbc.execute("insert into todo (description , done , username ,priority , created_date,id) values ('work on springBoot server updation,error page yml changes' , false ,'Karthik K C' ,10,'2023-04-20',173609);");
    }


    @Test
    public void dataTest() {
        assertEquals(repo.findById(173609).get().getUsername(), "Karthik K C");
    }

    @AfterEach
    public void deleteDatabaseInsert() {
        jdbc.execute("delete from todo");
    }
*/
}
