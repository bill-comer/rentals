package co.uk.billcomer.rentals.dao;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.support.DirtiesContextTestExecutionListener;
import org.springframework.test.context.transaction.TransactionConfiguration;

import co.uk.billcomer.rentals.domain.User;

import com.github.springtestdbunit.DbUnitTestExecutionListener;
import com.github.springtestdbunit.TransactionDbUnitTestExecutionListener;
import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.github.springtestdbunit.annotation.DbUnitConfiguration;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations= {
            "classpath*:config/testContext.xml",
            "classpath*:config/test-dao.xml",
        })
        @DbUnitConfiguration(databaseConnection="rentals_dataSource")
        @TestExecutionListeners({
          DependencyInjectionTestExecutionListener.class,
          DirtiesContextTestExecutionListener.class,
          TransactionDbUnitTestExecutionListener.class, //<-- needed if using transactions otherwise use TransactionalTestExecutionListener.class
          DbUnitTestExecutionListener.class })
        @TransactionConfiguration(transactionManager="transactionManager", defaultRollback= true)
public class UserDaoTest 
{

  @Autowired(required=true)
  @Qualifier("rentals.userDao")
  private UserDaoImpl userDao;
  
  @Test
  @DatabaseSetup("rentals_2users_noRoles.xml")
  public void test_getById_foundOne() throws Exception
  {
    assertNotNull("UserDaoImpl Not injected", userDao);
    
    User user = userDao.getById(1L);
    
    assertNotNull("Should have found user with ID 1", user);
    assertEquals("foo1_username", user.getUsername());
  }
  

  @Test
  @DatabaseSetup("rentals_2users_noRoles.xml")
  public void test_getById_NoneToFind() throws Exception
  {
    User user = userDao.getById(99L);
    
    assertNull("Should have found no user with ID 99", user);
  }
  

  @Test
  @DatabaseSetup("rentals_2users_noRoles.xml")
  public void test_getUsersBySurname_NoneToFind() throws Exception
  {
    List<User> users = userDao.getUsersBySurname("bar");
    
    assertNotNull("Should be an empty list", users);
    assertEquals("Should be a list with no members", 0, users.size());
  }
  
  @Test
  @DatabaseSetup("rentals_2users_noRoles.xml")
  public void test_getUsersBySurname_OneToFind() throws Exception
  {
    List<User> users = userDao.getUsersBySurname("foo2_surname");
    
    assertNotNull("Should be an empty list", users);
    assertEquals("Should be a list with 1 members", 1, users.size());
    
    assertEquals("user should be foo2_username", "foo2_username", users.get(0).getUsername());
  }


  @Test
  @DatabaseSetup("rentals_2users_noRoles.xml")
  public void test_getUsersBySurname_TwoToFind() throws Exception
  {
    List<User> users = userDao.getUsersBySurname("foo1_surname");
    
    assertNotNull("Should be an empty list", users);
    assertEquals("Should be a list with 1 members", 2, users.size());
    
    boolean foundFoo1 = false;
    boolean foundFoo2 = false;
    
    for (User user : users)
    {
      if (user.getUsername().equals("foo1_username")) {
        foundFoo1 = true;
      }
      else 
        if (user.getUsername().equals("foo2_username")) {
          foundFoo2 = true;
        }
    }

    assertTrue("Should have found user-foo1_username", foundFoo1);
    assertTrue("Should have found user-foo1_username", foundFoo1);
  }

  @Test
  @DatabaseSetup("rentals_2users_noRoles.xml")
  public void test_createUser() throws Exception
  {
    User newUser = new User();
    newUser.setUsername("new1");
    newUser.setEmail("foo@foo.com");
    newUser.setForename("new_f");
    newUser.setSurname("new_s");
    
    List<User> users = userDao.getUsersBySurname("new_s");
    assertTrue("Expected no users with name 'new_s'", users.size() == 0);
    
    newUser = userDao.createUser(newUser);
    assertNotNull("iserId should now have a value", newUser.getUserId());
    
    users = userDao.getUsersBySurname("new_s");
    assertTrue("There should noe be a user 'new_s'", users.size() == 1);
  }
  
  @Test
  @DatabaseSetup("rentals_2users_noRoles.xml")
  public void test_updateUser() throws Exception
  {
 
    User user = userDao.getById(1L);
    
    assertNotNull("Should have found user with ID 1", user);
    assertEquals("foo1_username", user.getUsername()); 
    assertEquals("foo1_surname", user.getSurname()); 
    
    user.setSurname("foobar");
    userDao.updateUser(user);
    
    User user2 = userDao.getById(1L);
    assertNotNull("Should have found user with ID 1", user2);
    assertEquals("foo1_username", user2.getUsername()); 
    assertEquals("foobar", user2.getSurname()); 
    
  }
 
  @Test
  @DatabaseSetup("rentals_2users_noRoles.xml")
  public void test_DoesUserHaveRole_userHasNoRoles() throws Exception
  {
    User user = userDao.getById(1L);
    assertFalse("This user should have no roles", userDao.doesUserHaveRole(user, "Foooo"));
  }
  

  @Test
  @DatabaseSetup("rentals_3users_withRoles.xml")
  public void test_DoesUserHaveRole_userHasOneRole() throws Exception
  {
    User user = userDao.getById(1L);
    assertTrue("This user should have this role", userDao.doesUserHaveRole(user, "role_1"));
  }
  

  @Test
  @DatabaseSetup("rentals_3users_withRoles.xml")
  public void test_DoesUserHaveRole_userHasOneRole_ButNotThisOne() throws Exception
  {
    User user = userDao.getById(1L);
    assertFalse("This user should not have this roles", userDao.doesUserHaveRole(user, "role_2"));
  }
  

  @Test
  @DatabaseSetup("rentals_3users_withRoles.xml")
  public void test_getUsersWithNumberOfRoles() throws Exception
  {
    assertEquals("no users with no roles",1,  userDao.getUsersWithNumberOfRoles(0).size() );
    assertEquals("one user with 1 roles",1,  userDao.getUsersWithNumberOfRoles(1).size() );
    assertEquals("one user with 2 roles",1,  userDao.getUsersWithNumberOfRoles(2).size() );
  }
  

  @Test
  @DatabaseSetup("rentals_3users_withRoles.xml")
  public void test_getUsersWithManagerAndNumberOfRoles_noUserHasAManager() throws Exception
  {
    assertEquals("no users have a manager",0,  userDao.getUsersWithManagerAndNumberOfRoles("some_manager", 1).size() );
  }
  

  @Test
  @DatabaseSetup("rentals_3users_withRolesAndManager.xml")
  public void test_getUsersWithManagerAndNumberOfRoles_HasAManager_ButWrongNumRoles() throws Exception
  {
    assertEquals("no users have a manager with two role",0,  userDao.getUsersWithManagerAndNumberOfRoles("foo1_username", 2).size() );
  }
  

  @Test
  @DatabaseSetup("rentals_3users_withRolesAndManager.xml")
  public void test_getUsersWithManagerAndNumberOfRoles_HasAManager_withOneRole() throws Exception
  {
    
    User manager = userDao.getById(1L);
    User user = userDao.getById(2L);
    user.setManager(manager);
    userDao.updateUser(user);

    
    List<User> all = userDao.getAll();
    assertEquals("no users have a manager with two role",1,  userDao.getUsersWithManagerAndNumberOfRoles("foo1_username", 1).size() );
  }
}
