package co.uk.billcomer.rentals.dao;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import co.uk.billcomer.rentals.domain.User;


@Repository("rentals.userDao")
public class UserDaoImpl extends GenericDao<User, Long> 
{

  public User getUserByUsername(String username)
  {
    Query query = getSession().createQuery("FROM User user WHERE user.username = :username");
    query.setParameter("username", username);
    return (User) query.uniqueResult();
  }

}
