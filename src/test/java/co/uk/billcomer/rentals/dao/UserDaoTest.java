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

  
}
