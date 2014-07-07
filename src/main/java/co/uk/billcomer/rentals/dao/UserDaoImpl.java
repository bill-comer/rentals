package co.uk.billcomer.rentals.dao;

import java.util.List;

import org.hibernate.Query;
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
    // TODO Auto-generated method stub
    return null;
  }

}
