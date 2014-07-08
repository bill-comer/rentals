package co.uk.billcomer.rentals.controller;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.io.StringWriter;
import java.io.Writer;
import java.nio.charset.Charset;
import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.mockito.MockitoAnnotations.Mock;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import co.uk.billcomer.rentals.domain.User;
import co.uk.billcomer.rentals.responder.Response;
import co.uk.billcomer.rentals.service.UserService;

import com.fasterxml.jackson.databind.ObjectMapper;

public class UserJSONControllerGetUserBySurnameTest
{

  public static final MediaType APPLICATION_JSON_UTF8 = new MediaType(MediaType.APPLICATION_JSON.getType(),
              MediaType.APPLICATION_JSON.getSubtype(),                        
              Charset.forName("utf8")                     
              );
  
  private MockMvc mockMvc;
  
  @InjectMocks
  private UserJSONController userJSONController;
  
  @Mock
  private UserService<User> userservice;

  @Before
  public void init() {
      MockitoAnnotations.initMocks(this);
  }
  
  
  
  @Test
  public void test_getUserById_success_surname() throws Exception
  {
    //Mock expected results
    User mockFoundUser = new User();
    mockFoundUser.setUserId(2L);
    mockFoundUser.setSurname("m_sur");
    mockFoundUser.setForename("m_for");
    mockFoundUser.setEmail("m_e@e.com");
    mockFoundUser.setUsername("musername");
    ArrayList<User> users = new ArrayList<User>();
    users.add(mockFoundUser);
    Response expectedResponse = Response.createSuccessfulResponse(users);
    
    ObjectMapper mapper = new ObjectMapper();
    Writer jsonResponseWriter = new StringWriter();
    mapper.writeValue(jsonResponseWriter, expectedResponse);

    
    this.mockMvc = MockMvcBuilders.standaloneSetup(userJSONController).build();
    
    when(userservice.getUsersBySurname("m_sur")).thenReturn(users);
    
    //Test method
    ResultActions results = mockMvc.perform(get("/user/surname/m_sur"))
         .andExpect(status().isOk())
         .andExpect(content().contentType(APPLICATION_JSON_UTF8))
         .andExpect(content().string(jsonResponseWriter.toString()))
       ;
  }
  
  @Test
  public void test_getUserById_failed() throws Exception
  {
    //Mock expected results
    Response expectedResponse = Response.createFailedResponse("Failed to find a user with SURNAME[foo_surname]");
    
    ObjectMapper mapper = new ObjectMapper();
    Writer jsonResponseWriter = new StringWriter();
    mapper.writeValue(jsonResponseWriter, expectedResponse);

    
    this.mockMvc = MockMvcBuilders.standaloneSetup(userJSONController).build();
    
    when(userservice.getUsersBySurname("foo_surname")).thenReturn(null);
    
    //Test method
    ResultActions results = mockMvc.perform(get("/user/surname/foo_surname"))
         .andExpect(status().isOk())
         .andExpect(content().contentType(APPLICATION_JSON_UTF8))
         .andExpect(content().string(jsonResponseWriter.toString()))
       ;
  }
}
