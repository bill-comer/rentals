package co.uk.billcomer.rentals.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import co.uk.billcomer.rentals.dao.UserDaoImpl;
import co.uk.billcomer.rentals.domain.User;


@Service("rentals.userService")
@Transactional(value = "transactionManager")
public class UserServiceImpl implements UserService<User>
{
  @Autowired(required=true)
  @Qualifier("rentals.userDao")
  private UserDaoImpl userDao;
  
  @Override
  public User getUserById(Long id)
  {
    return userDao.getById(id);
  }

  @Override
  public User getUserByUsername(String username) {
    return userDao.getUserByUsername(username);
  }

  @Override
  public User getUserBySurname(String surname) {
    return userDao.getUserBySurname(surname);
  }

}
