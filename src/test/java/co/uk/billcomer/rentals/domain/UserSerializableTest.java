package co.uk.billcomer.rentals.domain;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.springframework.util.SerializationUtils;

public class UserSerializableTest
{
  
  @Test
  public void testEqualsWithNoRoles() throws Exception
  {
    User srcUser1 = createUser1();
    
    User srcUser2 = createUser2();
    
    assertEquals("These two users should be equal", srcUser1, srcUser2);
  }
  
  @Test
  public void testEqualsWithRoles() throws Exception
  {
    User srcUser3 = createUser1_2();
    
    User srcUser4 = createUser2_2();
    
    assertEquals("These two users should be equal", srcUser3, srcUser4);
  }
  
  @Test
  public void testEqualsWithDifftRoles() throws Exception
  {
    User srcUser3 = createUser1_2();
    
    User srcUser4 = createUser2_2();
    UserRole role = new UserRole();
    role.setRole("role2");
    role.setUser(srcUser4);
    srcUser4.getUserRoles().add(role);
    
    assertFalse("These two users have difft roles", srcUser3.equals(srcUser4));
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

  private User createUser1_2()
  {
    User user = createUser1();
    UserRole role = new UserRole();
    role.setRole("role1");
    role.setUser(user);
    List<UserRole> roles = new ArrayList<UserRole>();
    roles.add(role);
    user.setUserRoles(roles);
    
    return user;
  }
  
  private User createUser2_2()
  {
    User user = createUser2();
    UserRole role = new UserRole();
    role.setRole("role1");
    role.setUser(user);
    List<UserRole> roles = new ArrayList<UserRole>();
    roles.add(role);
    user.setUserRoles(roles);
    
    return user;
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
