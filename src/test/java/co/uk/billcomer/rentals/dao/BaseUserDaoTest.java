package co.uk.billcomer.rentals.dao;

import java.io.FileInputStream;

import org.dbunit.DBTestCase;
import org.dbunit.PropertiesBasedJdbcDatabaseTester;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.xml.FlatXmlDataSetBuilder;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;



public class BaseUserDaoTest extends DBTestCase
{
  @Autowired(required=true)
  @Qualifier("rentals.userDao")
  private UserDaoImpl userDao;
  
  public BaseUserDaoTest(String name)
  {
      super( name );
      System.setProperty( PropertiesBasedJdbcDatabaseTester.DBUNIT_DRIVER_CLASS, "com.mysql.jdbc.Driver" );
      System.setProperty( PropertiesBasedJdbcDatabaseTester.DBUNIT_CONNECTION_URL, "jdbc:mysql://localhost:3306/rentals_test" );
      System.setProperty( PropertiesBasedJdbcDatabaseTester.DBUNIT_USERNAME, "rentals" );
      System.setProperty( PropertiesBasedJdbcDatabaseTester.DBUNIT_PASSWORD, "rentals" );
// System.setProperty( PropertiesBasedJdbcDatabaseTester.DBUNIT_SCHEMA, "" );
  }
  
  //@Test
  public void testNAME() throws Exception
  {
    BaseUserDaoTest test = new BaseUserDaoTest("fooo");
    assertTrue(true);
  }
  
  
  
  @Override
  protected IDataSet getDataSet() throws Exception
  {
    return new FlatXmlDataSetBuilder().build(new FileInputStream("src/test/java/co/uk/billcomer/rentals/dao/rentals_2users_noRoles.xml"));
  }

}
