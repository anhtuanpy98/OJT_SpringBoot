package com.java;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

import java.util.Date;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.hamcrest.Matchers;
import static org.hamcrest.CoreMatchers.containsString;

import com.java.api.crudStudent.StudentApi;
import com.java.config.YAMLConfig;
import com.java.entity.Student;
import com.java.repository.StudentRepo;
import com.java.service.StudentService;

@WebMvcTest(controllers = StudentApi.class)
class RestWebTest {
	@Autowired
	MockMvc mockMvc;

	@MockBean
	StudentService service;

	@MockBean
	StudentRepo studentRepo;

	@MockBean
	YAMLConfig myConfig;

	private static final String CONTENT_TYPE = "application/json";

//	@BeforeEach
//	void setUp(WebApplicationContext wac) throws Exception {
//		this.mockMvc = MockMvcBuilders.webAppContextSetup(wac).alwaysExpect(MockMvcResultMatchers.status().isOk())
//				.alwaysExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON)).alwaysDo(print())
//				.build();
//	}
	

	@Test
	public void givenGreetURIWithPathVariable_whenMockMVC_thenResponseOK() throws Exception {
		Date now = new Date();
		Student student = new Student(100, "Bill", "b", now);

		//when(service.findById(100)).thenReturn(Optional.of(student));

		this.mockMvc
				.perform(post("/addStudent/100").contentType(MediaType.APPLICATION_JSON).content(
						"{\"id\": \"100\",\"name\": \"Bill\",\"address\": \"Gates\",\"birthday\": \"2021-09-20\"}"))
				.andExpect(status().isCreated()).andExpect(header().exists("Location"))
				.andExpect(header().string("Location", Matchers.containsString("http://localhost/getStudent")))
				.andDo(print());

	}
	
	
	@Test
	void whenReadStudent_returnJsonContent() throws Exception {
		Date now = new Date();
		Student student = new Student(11, "Bill", "b", now);
		
	     studentRepo.save(student);
	    //when(service.findById(11)).thenReturn(Optional.of(student));
	     
	 
	    this.mockMvc.perform(get("/getStudent/{id}", 11))
	        .andExpect(status().isOk())  // status 200 mean that request is successful
	        .andExpect(content().string(containsString(
	            "{\"id\":11,\"name\":\"Bill\",\"address\":\"b\",\"birthday\":\"2021-09-21\"}")))
	        .andDo(print());
	}

}
