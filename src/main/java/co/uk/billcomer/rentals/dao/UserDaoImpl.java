package co.uk.billcomer.rentals.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Transaction;
import org.springframework.stereotype.Repository;

import co.uk.billcomer.rentals.domain.User;
import co.uk.billcomer.rentals.domain.UserRole;


@Repository("rentals.userDao")
public class UserDaoImpl extends GenericDao<User, Long> 
{
  @SuppressWarnings("unchecked")
  public List<User> getAll()
  {
    return (List<User>) getSession().createQuery("from User user ").list();
  }

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
    for (UserRole aRole : user.getUserRoles())
    {
      if (aRole.getRole().equalsIgnoreCase(role)) {
        return true;
      }
    }
    return false;
  }


  public List<User> getUsersWithNumberOfRoles(int numRoles)
  {
    Query query = getSession().createQuery("FROM User user WHERE user.userRoles.size = :numRoles");
    query.setParameter("numRoles", numRoles);
    return (List<User>) query.list();
  }

  public List<User> getUsersWithManagerAndNumberOfRoles(String manager, int numRoles)
  {
    Query query = getSession().createQuery("FROM User user WHERE user.manager.username = :manager AND user.userRoles.size = :numRoles");
    query.setParameter("numRoles", numRoles);
    query.setParameter("manager", manager);
    return (List<User>) query.list();
  }


  public boolean addManagerToUser(User user, User manager)
  {
    user.setManager(manager);
    makePersistent(user);
    return true;
  }

}
