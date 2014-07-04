package co.uk.billcomer.rentals.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import co.uk.billcomer.rentals.dao.UserDao;


@Service("rentals.userService")
@Transactional(value = "transactionManager")
public class UserServiceImpl<User> implements UserService<User>
{
  @Autowired(required=true)
  @Qualifier("rentals.userDao")
  private UserDao<User> userDao;
  
  @Override
  public User getUserById(Long id)
  {
    return userDao.getUserById(id);
  }

}
