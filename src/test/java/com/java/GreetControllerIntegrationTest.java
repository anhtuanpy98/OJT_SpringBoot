package com.java;

import static org.hamcrest.CoreMatchers.containsString;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultHandler;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Date;

import com.java.api.crudStudent.StudentApi;
import com.java.entity.Student;
import com.java.repository.StudentRepo;

@ExtendWith(SpringExtension.class)
@ContextConfiguration()
@WebAppConfiguration

@SpringBootTest
@AutoConfigureMockMvc
class GreetControllerIntegrationTest {

	@Autowired
	private WebApplicationContext webApplicationContext;

	private MockMvc mockMvc;

	@MockBean
	private StudentRepo studentRepo;

	private static final String CONTENT_TYPE = "application/json";

	@BeforeEach
	public void setup() {
		this.mockMvc = MockMvcBuilders.webAppContextSetup(this.webApplicationContext).build();
		// this.mockMvc = MockMvcBuilders.standaloneSetup(new StudentApi()).build();
	}

//	@Test
//	public void getAllStudentAPI() throws Exception {
//		final MvcResult mvcResult = this.mockMvc.perform(MockMvcRequestBuilders.get("/getStudent")).andDo(print())
//				.andExpect(MockMvcResultMatchers.status().isOk())
//				.andExpect(MockMvcResultMatchers.jsonPath("$.id").value(10)).andReturn();
//		assertEquals(CONTENT_TYPE, mvcResult.getResponse().getContentType());
//
//	}

	@Test
	public void givenGreetURIWithPathVariable_whenMockMVC_thenResponseOK() throws Exception {
		Date now = new Date();
		Student student = new Student(11, "acx", "b", now);
		studentRepo.save(student);
		
		this.mockMvc.perform(MockMvcRequestBuilders.get("/getStudent/{id}", 11))
				.andExpect(status().isOk())
		        .andExpect(content().string(containsString(
		            "{\"id\":100,\"name\":\"Bill\",\"address\":\"b\",\"birthday\":\"2021-09-21\"}")))
				.andDo(print())
//				.andExpect(MockMvcResultMatchers.status().isOk())
//				.andExpect(MockMvcResultMatchers.content().contentType(CONTENT_TYPE))
//				.andExpect(MockMvcResultMatchers.jsonPath("$.name").value("acx")).andReturn()
				;
		
		
	}
}
