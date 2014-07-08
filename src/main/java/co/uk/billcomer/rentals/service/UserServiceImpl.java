package co.uk.billcomer.rentals.service;

import java.util.List;

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
  public List<User> getUsersBySurname(String surname) {
    return userDao.getUsersBySurname(surname);
  }

  @Override
  public User createUser(String username, String email, String surname, String forename)
  {
    User user = new User();
    user.setUsername(username);
    user.setSurname(surname);
    user.setEmail(email);
    user.setForename(forename);
    
    return userDao.createUser(user);
  }

  @Override
  public User updateUser(User user, String email, String surname, String forename)
  {
    boolean changeRequired = user.isUpdateOfFieldsRequired(email, surname, forename);
    
    if (changeRequired) {
      user.setEmail(email.toLowerCase());
      user.setSurname(surname);
      user.setForename(forename);

      userDao.updateUser(user);
    }
    return user;
  }

  @Override
  public boolean doesUserHaveRole(User user, String role)
  {
    return userDao.doesUserHaveRole(user, role);
  }

  @Override
  public User updateUser(User user)
  {
    return userDao.makePersistent(user);
  }

  @Override
  public List<User> getUsersWithNumberOfRoles(int numRoles)
  {
    return userDao.getUsersWithNumberOfRoles(numRoles);
  }

  @Override
  public List<User> getUsersWithManagerAndNumberOfRoles(String manager, int numRoles)
  {
    return userDao.getUsersWithManagerAndNumberOfRoles(manager, numRoles);
  }



}
