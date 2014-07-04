package co.uk.billcomer.rentals.dao;

import org.springframework.stereotype.Repository;


@Repository("rentals.userDao")
public class UserDaoImpl<User> extends GenericDao<User> implements UserDao<User> 
{

  @Override
  public User getUserById(Long id)
  {
    return getById(id);
  }

}
