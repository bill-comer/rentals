package co.uk.billcomer.rentals.dao;

import static org.junit.Assert.*;

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
    User user = userDao.getById(3L);
    
    assertNull("Should have found no user with ID 3", user);
  }
  



  
}
