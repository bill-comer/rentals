package co.uk.billcomer.rentals.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Transaction;
import org.springframework.stereotype.Repository;

import co.uk.billcomer.rentals.domain.User;


@Repository("rentals.userDao")
public class UserDaoImpl extends GenericDao<User, Long> 
{

  public User getUserByUsername(String username) {
    Query query = getSession().createQuery("FROM User user WHERE user.username = :username");
    query.setParameter("username", username);
    return (User) query.uniqueResult();
  }


  public List<User> getUsersBySurname(String surname) {
    Query query = getSession().createQuery("FROM User user WHERE user.surname = :surname");
    query.setParameter("surname", surname);
    return (List<User>) query.list();
  }


  public User createUser(User user)
  {
    makePersistent(user);
    return user;
  }
  
  public User updateUser(User user)
  {
    makePersistent(user);
    return user;
  }
  
  public User makePersistent(final User user)
  {
    Transaction tx = getSession().beginTransaction();
    if (user.getUserId() == null)
    {
      getSession().save(user);
    }
    else
    {
      getSession().update(user);
    }
    tx.commit();
    return user;
  }


  public boolean doesUserHaveRole(User user, String role)
  {
    return false;
  }


  public List<User> getUsersWithNumberOfRoles(int numRoles)
  {
    Query query = getSession().createQuery("FROM User user WHERE user.userRoles.size = :numRoles");
    query.setParameter("numRoles", numRoles);
    return (List<User>) query.list();
  }

}
