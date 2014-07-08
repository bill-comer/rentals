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

public class UserJSONControllerUpdateUserTest
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
  public void test_update_failed_userDoesNotExist() throws Exception
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
    
    Response expectedResponse = Response.createFailedResponse("Failed to find a user with username[" + mockFoundUser.getUsername() + "].");
    
    ObjectMapper mapper = new ObjectMapper();
    Writer jsonResponseWriter = new StringWriter();
    mapper.writeValue(jsonResponseWriter, expectedResponse);
    
    this.mockMvc = MockMvcBuilders.standaloneSetup(userJSONController).build();
    
    //mocked service call to create
    when(userservice.getUserByUsername(mockFoundUser.getUsername())).thenReturn(null);
    
    //Test method
    ResultActions results = mockMvc.perform(get("/user/update/musername/foo/bar/fish"))
         .andExpect(status().isOk())
         .andExpect(content().contentType(APPLICATION_JSON_UTF8))
         .andExpect(content().string(jsonResponseWriter.toString()))
       ;
  }
  
  @Test
  public void test_update_failed_userIsSame() throws Exception
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
    
    Response expectedResponse = Response.createFailedResponse("No Update required for User with username[" + mockFoundUser.getUsername() + "].");
    
    ObjectMapper mapper = new ObjectMapper();
    Writer jsonResponseWriter = new StringWriter();
    mapper.writeValue(jsonResponseWriter, expectedResponse);
    
    this.mockMvc = MockMvcBuilders.standaloneSetup(userJSONController).build();
    
    //mocked service call to create
    when(userservice.getUserByUsername(mockFoundUser.getUsername())).thenReturn(mockFoundUser);
    
    //Test method
    ResultActions results = mockMvc.perform(get("/user/update/musername/m_e@e.com/m_sur/m_for"))
         .andExpect(status().isOk())
         .andExpect(content().contentType(APPLICATION_JSON_UTF8))
         .andExpect(content().string(jsonResponseWriter.toString()))
       ;
  }
  
  
  ////////////////////////////////////////
  
  @Test
  public void test_create_failed() throws Exception
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
    
    Response expectedResponse = Response.createFailedResponse("Failed to create a user with username[" + mockFoundUser.getUsername() + "]");
    
    ObjectMapper mapper = new ObjectMapper();
    Writer jsonResponseWriter = new StringWriter();
    mapper.writeValue(jsonResponseWriter, expectedResponse);
    
    this.mockMvc = MockMvcBuilders.standaloneSetup(userJSONController).build();
    
    //mocked service call to create
    when(userservice.createUser(
                mockFoundUser.getUsername(), mockFoundUser.getEmail(),
                mockFoundUser.getSurname(), mockFoundUser.getForename())).thenReturn(null);
    
    //Test method
    ResultActions results = mockMvc.perform(get("/user/create/musername/m_e@e.com/m_sur/m_for"))
         .andExpect(status().isOk())
         .andExpect(content().contentType(APPLICATION_JSON_UTF8))
         .andExpect(content().string(jsonResponseWriter.toString()))
       ;
  }
  
  @Test
  public void test_create_failed_user_already_exists() throws Exception
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
    
    Response expectedResponse = Response.createFailedResponse("Failed to create a user with username[" + mockFoundUser.getUsername() + "]. Username already exists.");
    
    ObjectMapper mapper = new ObjectMapper();
    Writer jsonResponseWriter = new StringWriter();
    mapper.writeValue(jsonResponseWriter, expectedResponse);
    
    this.mockMvc = MockMvcBuilders.standaloneSetup(userJSONController).build();
    //mocked service call to create
    when(userservice.getUserByUsername("musername")).thenReturn(mockFoundUser);
    
    //Test method
    ResultActions results = mockMvc.perform(get("/user/create/musername/m_e@e.com/m_sur/m_for"))
         .andExpect(status().isOk())
         .andExpect(content().contentType(APPLICATION_JSON_UTF8))
         .andExpect(content().string(jsonResponseWriter.toString()))
       ;
  }
  
 
}
