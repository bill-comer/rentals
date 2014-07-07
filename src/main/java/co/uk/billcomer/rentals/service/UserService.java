package co.uk.billcomer.rentals.service;

import java.util.List;

import co.uk.billcomer.rentals.domain.User;

public interface UserService<T>
{
  public T getUserById(Long id);
  
  public T getUserByUsername(String username);

  public List<User> getUsersBySurname(String surname);

  public User createUser(String username, String email, String surname, String forename);

  public User updateUser(User user, String email, String surname, String forename);
}
