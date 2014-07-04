package co.uk.billcomer.rentals.domain;

import static org.junit.Assert.*;

import org.junit.Test;
import org.springframework.util.SerializationUtils;

public class UserSerializableTest
{
  
  @Test
  public void testEquals() throws Exception
  {
    User srcUser1 = new User();
    srcUser1.setUserId(101L);
    srcUser1.setEmail("foo@bar.com");
    srcUser1.setForename("f_name");
    srcUser1.setSurname("s_name");
    srcUser1.setUsername("user_name");
    
    User srcUser2 = new User();
    srcUser2.setUserId(101L);
    srcUser2.setEmail("foo@bar.com");
    srcUser2.setForename("f_name");
    srcUser2.setSurname("s_name");
    srcUser2.setUsername("user_name");
    
    assertEquals("These two users should be equal", srcUser1, srcUser2);
  }

  @Test
  public void testSerialzable() throws Exception
  {
    User srcUser = new User();
    srcUser.setUserId(101L);
    srcUser.setEmail("foo@bar.com");
    srcUser.setForename("f_name");
    srcUser.setSurname("s_name");
    srcUser.setUsername("user_name");
    
    byte[] serialized = SerializationUtils.serialize(srcUser);
    
    assertNotNull("Failed to sertialzed user", serialized);
    
    User desrialzedUser = (User)SerializationUtils.deserialize(serialized);
    
    assertEquals("Deseriaze failed to reproduce same object", srcUser, desrialzedUser);
  }
}
