package co.uk.billcomer.rentals.responder;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.springframework.util.SerializationUtils;

import co.uk.billcomer.rentals.domain.User;

public class ResponderSeialisationTest
{

  @Test
  public void testSame() throws Exception
  {
    Response resp1 = createResponse1();
    Response resp2 = createResponse2();
    
    assertEquals("These two responses should be equal", resp1, resp2);
  }
  

  @Test
  public void test_DifftMessages() throws Exception
  {
    Response resp1 = createResponse1();
    resp1.getMessages().add("foobar");
    Response resp2 = createResponse2();
    
    assertFalse("These two responses should be difft", resp1.equals(resp2));
  }
  

  @Test
  public void test_DifftUsers() throws Exception
  {
    Response resp1 = createResponse1();
    resp1.getUsers().get(0).setSurname("foo");
    Response resp2 = createResponse2();
    
    assertFalse("These two responses should be difft", resp1.equals(resp2));
  }
  
  public void test_DifftSuccessStatus() throws Exception
  {
    Response resp1 = createResponse1();
    resp1.setSuccess(false);
    Response resp2 = createResponse2();
    
    assertFalse("These two responses should be difft", resp1.equals(resp2));
  }
  
  @Test
  public void testSerialzable_Same() throws Exception
  {
    Response resp1 = createResponse1();
    byte[] serialized = SerializationUtils.serialize(resp1);
    
    assertNotNull("Failed to sertialzed user", serialized);

    Response desrialzedUser = (Response)SerializationUtils.deserialize(serialized);
    
    assertEquals("Deseriaze failed to reproduce same object", resp1, desrialzedUser);
    
    Response resp2 = createResponse2();
  }
  
  @Test
  public void testSerialzable_DifftSuccess() throws Exception
  {
    Response resp1 = createResponse1();
    byte[] serialized = SerializationUtils.serialize(resp1);

    Response resp2 = createResponse1();
    resp2.setSuccess(false);
    
    
    assertNotNull("Failed to sertialzed user", serialized);

    Response desrialzedUser = (Response)SerializationUtils.deserialize(serialized);
    
    assertEquals("Deseriaze failed to reproduce same object", resp1, desrialzedUser);
    
    assertFalse("Deserialize object should be difft", resp2.equals(desrialzedUser));
  }
  
  @Test
  public void testSerialzable_DifftMessage() throws Exception
  {
    Response resp1 = createResponse1();
    byte[] serialized = SerializationUtils.serialize(resp1);

    Response resp2 = createResponse1();
    resp2.getMessages().add("foooo");
    
    
    assertNotNull("Failed to sertialzed user", serialized);

    Response desrialzedUser = (Response)SerializationUtils.deserialize(serialized);
    
    assertEquals("Deseriaze failed to reproduce same object", resp1, desrialzedUser);
    
    assertFalse("Deserialize object should be difft", resp2.equals(desrialzedUser));
  }
  
  @Test
  public void testSerialzable_DifftUser() throws Exception
  {
    Response resp1 = createResponse1();
    byte[] serialized = SerializationUtils.serialize(resp1);

    Response resp2 = createResponse1();
    resp2.getUsers().get(0).setSurname("hshgasjhgajdh");
    
    
    assertNotNull("Failed to sertialzed user", serialized);

    Response desrialzedUser = (Response)SerializationUtils.deserialize(serialized);
    
    assertEquals("Deseriaze failed to reproduce same object", resp1, desrialzedUser);
    
    assertFalse("Deserialize object should be difft", resp2.equals(desrialzedUser));
  }

  private Response createResponse1()
  {
    Response resp = new Response();
    resp.setSuccess(true);
    String m = "foo";
    List<String> messages = new ArrayList<String>();
    messages.add(m);
    User user = new User();
    user.setForename("foo");
    List<User> users = new ArrayList<User>();
    users.add(user);
    resp.setMessages(messages);
    resp.setUsers(users);
    return resp;
  }
  
  private Response createResponse2()
  {
    Response resp = new Response();
    resp.setSuccess(true);
    String m = "foo";
    List<String> messages = new ArrayList<String>();
    messages.add(m);
    User user = new User();
    user.setForename("foo");
    List<User> users = new ArrayList<User>();
    users.add(user);
    resp.setMessages(messages);
    resp.setUsers(users);
    return resp;
  }
}
