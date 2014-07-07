package co.uk.billcomer.rentals.domain;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class UserCompareUpdateTest
{

  @Test
  public void test_compare_allTheSame() throws Exception
  {
    User user = new User();
    user.setEmail("email");
    user.setSurname("surname");
    user.setForename("forename");

    assertFalse(user.isUpdateOfFieldsRequired("email", "surname", "forename"));

  }
  
  @Test
  public void test_compare_surname_caseDifft() throws Exception
  {
    User user = new User();
    user.setEmail("email");
    user.setSurname("surname");
    user.setForename("forename");

    assertFalse(user.isUpdateOfFieldsRequired("email", "Surname", "forename"));

  }
  

  @Test
  public void test_compare_forename_caseDifft() throws Exception
  {
    User user = new User();
    user.setEmail("email");
    user.setSurname("surname");
    user.setForename("forename");

    assertFalse(user.isUpdateOfFieldsRequired("email", "surname", "Forename"));

  }

  @Test
  public void test_compare_email_difft() throws Exception
  {
    User user = new User();
    user.setEmail("email");
    user.setSurname("surname");
    user.setForename("forename");

    assertTrue(user.isUpdateOfFieldsRequired("email1", "surname", "forename"));

  }

  @Test
  public void test_compare_surname_difft() throws Exception
  {
    User user = new User();
    user.setEmail("email");
    user.setSurname("surname");
    user.setForename("forename");

    assertTrue(user.isUpdateOfFieldsRequired("email", "1surname", "forename"));

  }

  @Test
  public void test_compare_forrename_difft() throws Exception
  {
    User user = new User();
    user.setEmail("email");
    user.setSurname("surname");
    user.setForename("forename");

    assertTrue(user.isUpdateOfFieldsRequired("email", "surname", "1forename"));
  }
}
