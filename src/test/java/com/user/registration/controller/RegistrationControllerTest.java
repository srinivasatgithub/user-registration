package com.user.registration.controller;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.Date;

import org.hibernate.SessionFactory;
import org.hibernate.classic.Session;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.ContextHierarchy;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.user.registration.config.CoreAppConfig;
import com.user.registration.config.WebApplicationConfig;
import com.user.registration.domain.User;
import com.user.registration.service.UserRegistrationService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextHierarchy({ @ContextConfiguration(classes = CoreAppConfig.class),
		@ContextConfiguration(classes = WebApplicationConfig.class) })
@WebAppConfiguration
@Transactional
public class RegistrationControllerTest {

	private MockMvc mockMvc;

	@Autowired
	private SessionFactory sessionFactory;

	private Session currentSession;
	
	@Autowired
	private UserRegistrationService userRegistrationService;


	@Autowired
	private WebApplicationContext webApplicationContext;

	@Autowired
	private ObjectMapper objectMapper;

	public MediaType APPLICATION_JSON_UTF8;
	
	private User user1;
	private User user2;

	@Before
	public void setup() {
		mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();

		APPLICATION_JSON_UTF8 = new MediaType(MediaType.APPLICATION_JSON.getType(),
				MediaType.APPLICATION_JSON.getSubtype(), Charset.forName("utf8"));
		
		currentSession = sessionFactory.getCurrentSession();
		setupData();
	}
	
	public void setupData() {
		userRegistrationService.deleteAll();
		// inserting records
		user1 = new User("romeo", "romeo@gmail.com", new Date());
		currentSession.save(user1);
		user2 = new User("juliet", "juliet@gmail.com", new Date());
		currentSession.save(user2);

		currentSession.flush();
	}

	@After
	public void tearDownData(){

		// deleting records
		currentSession.delete(user1);
		currentSession.delete(user2);
		currentSession.flush();

	}
	
	@Test
	public void shouldHaveASessionFactory() {
		assertNotNull(sessionFactory);
	}
	
	/*@Test
	public void testAllMethods() throws Exception {
		//testGetAllUsers() ;
		//testGetUser();
		testCreateUser();
		testUpdateUser();
		testDeleteUser() ;
	}
	*/
	
	/*public void testGetAllUsers() throws Exception {


		mockMvc.perform(get("/users.json")).andExpect(status().isOk())
				.andExpect(content().contentType(APPLICATION_JSON_UTF8)).andExpect(jsonPath("$", hasSize(2)))
				.andExpect(jsonPath("$[0].id", is(1))).andExpect(jsonPath("$[0].name", is("romeo")))
				.andExpect(jsonPath("$[0].email", is("romeo@gmail.com"))).andExpect(jsonPath("$[1].id", is(2)))
				.andExpect(jsonPath("$[1].name", is("juliet"))).andExpect(jsonPath("$[1].email", is("juliet@gmail.com")));

	}

	public void testGetUser() throws Exception {

		mockMvc.perform(get("/users/1.json")).andExpect(status().isOk())
				.andExpect(content().contentType(APPLICATION_JSON_UTF8)).andExpect(jsonPath("$.id", is(1)))
				.andExpect(jsonPath("$.name", is("romeo"))).andExpect(jsonPath("$.email", is("romeo@gmail.com")));

	}*/
	@Test
	public void testCRUDUser() throws Exception {
		User user = new User("John", "john@gmail.com", new Date());
		ResultActions actions = mockMvc.perform(post("/users").contentType(APPLICATION_JSON_UTF8).content(convertUserToJsonBytes(user)));
		actions.andExpect(status().isOk());
		actions.andExpect(content().contentType(APPLICATION_JSON_UTF8));
		actions.andExpect(jsonPath("$", is(Integer.class)));
		Long id = new Long(actions.andReturn().getResponse().getContentAsString());
		
		mockMvc.perform(get(String.format("/users/%s",id))).andExpect(status().isOk())
				.andExpect(content().contentType(APPLICATION_JSON_UTF8)).andExpect(jsonPath("$.name", is("John")))
				.andExpect(jsonPath("$.email", is("john@gmail.com")));

		
		user = (User)currentSession.get(User.class, id);		
		// updating email
		user.setEmail("papa@gmail.com");
		mockMvc.perform(
				put("/users").contentType(APPLICATION_JSON_UTF8).content(convertUserToJsonBytes(user)))
				.andExpect(status().isOk()).andExpect(content().contentType(APPLICATION_JSON_UTF8))
				.andExpect(jsonPath("$", is(true)));

		mockMvc.perform(get(String.format("/users/%s",id))).andExpect(status().isOk())
		.andExpect(content().contentType(APPLICATION_JSON_UTF8)).andExpect(jsonPath("$.name", is("John")))
		.andExpect(jsonPath("$.email", is("papa@gmail.com")));
		
		//test delete
		mockMvc.perform(delete(String.format("/users/%s",id)).contentType(APPLICATION_JSON_UTF8))
		.andExpect(status().isOk()).andExpect(content().contentType(APPLICATION_JSON_UTF8))
		.andExpect(jsonPath("$", is(true)));

		assertNull(currentSession.get(User.class, id));
		
		currentSession.flush();
	}

	private byte[] convertUserToJsonBytes(User user) throws IOException {
		// ObjectMapper objectMapper = new ObjectMapper();
		objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
		return objectMapper.writeValueAsBytes(user);
	}

	/*public void testUpdateUser() throws Exception {
		
		User user = (User)currentSession.get(User.class, 1L);		

		// updating email
		user.setEmail("papa@gmail.com");

		mockMvc.perform(
				put("/users.json").contentType(APPLICATION_JSON_UTF8).content(convertUserToJsonBytes(user)))
				.andExpect(status().isOk()).andExpect(content().contentType(APPLICATION_JSON_UTF8))
				.andExpect(jsonPath("$", is(true)));

		mockMvc.perform(get("/users/1.json")).andExpect(status().isOk())
		.andExpect(content().contentType(APPLICATION_JSON_UTF8)).andExpect(jsonPath("$.name", is("romeo")))
		.andExpect(jsonPath("$.email", is("papa@gmail.com")));

	}

	public void testDeleteUser() throws Exception {
		
		mockMvc.perform(delete("/users/2.json").contentType(APPLICATION_JSON_UTF8))
				.andExpect(status().isOk()).andExpect(content().contentType(APPLICATION_JSON_UTF8))
				.andExpect(jsonPath("$", is(true)));

		assertNull(currentSession.get(User.class, 2L));

	}*/
}
