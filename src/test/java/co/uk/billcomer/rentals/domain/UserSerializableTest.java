package co.uk.billcomer.rentals.domain;

import static org.junit.Assert.*;

import org.junit.Test;
import org.springframework.util.SerializationUtils;

public class UserSerializableTest
{
  
  @Test
  public void testEquals() throws Exception
  {
    User srcUser1 = createUser1();
    
    User srcUser2 = createUser2();
    
    assertEquals("These two users should be equal", srcUser1, srcUser2);
  }
  
  @Test
  public void testEquals_difft_Id() throws Exception
  {
    User srcUser1 = createUser1();
    
    User srcUser2 = createUser2();
    srcUser2.setUserId(102L);
    
    assertFalse("These two users should be different", srcUser1.equals(srcUser2));
  }

  @Test
  public void testEquals_difft_username() throws Exception
  {
    User srcUser1 = createUser1();
    
    User srcUser2 = createUser2();
    srcUser2.setUsername("sdufsdfku");
    
    assertFalse("These two users should be different", srcUser1.equals(srcUser2));
  }
  
  @Test
  public void testEquals_difft_surname() throws Exception
  {
    User srcUser1 = createUser1();
    
    User srcUser2 = createUser2();
    srcUser2.setSurname("sdufsdfku");
    
    assertFalse("These two users should be different", srcUser1.equals(srcUser2));
  }
  
  @Test
  public void testEquals_difft_email() throws Exception
  {
    User srcUser1 = createUser1();
    
    User srcUser2 = createUser2();
    srcUser2.setEmail("sdufsdfku");
    
    assertFalse("These two users should be different", srcUser1.equals(srcUser2));
  }
  
  @Test
  public void testEquals_difft_forename() throws Exception
  {
    User srcUser1 = createUser1();
    
    User srcUser2 = createUser2();
    srcUser2.setForename("sdufsdfku");
    
    assertFalse("These two users should be different", srcUser1.equals(srcUser2));
  }
  
  
  private User createUser1()
  {
    User srcUser1 = new User();
    srcUser1.setUserId(101L);
    srcUser1.setEmail("foo@bar.com");
    srcUser1.setForename("f_name");
    srcUser1.setSurname("s_name");
    srcUser1.setUsername("user_name");
    return srcUser1;
  }
  
  private User createUser2()
  {
    User srcUser1 = new User();
    srcUser1.setUserId(101L);
    srcUser1.setEmail("foo@bar.com");
    srcUser1.setForename("f_name");
    srcUser1.setSurname("s_name");
    srcUser1.setUsername("user_name");
    return srcUser1;
  }

  @Test
  public void testSerialzable_Same() throws Exception
  {
    User srcUser = createUser1();
    
    byte[] serialized = SerializationUtils.serialize(srcUser);
    
    assertNotNull("Failed to sertialzed user", serialized);
    
    User desrialzedUser = (User)SerializationUtils.deserialize(serialized);
    
    assertEquals("Deseriaze failed to reproduce same object", srcUser, desrialzedUser);
  }
  
  @Test
  public void testSerialzable__NotSameDifftId() throws Exception
  {
    User srcUser = createUser1();
    User srcUser2 = createUser2();
    srcUser2.setUserId(102L);
    
    byte[] serialized = SerializationUtils.serialize(srcUser);
    
    assertNotNull("Failed to sertialzed user", serialized);
    
    User desrialzedUser = (User)SerializationUtils.deserialize(serialized);
    
    assertFalse("Deserialize object should be difft", srcUser2.equals(desrialzedUser));
  }
}
