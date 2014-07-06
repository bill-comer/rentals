package co.uk.billcomer.rentals.service;

import co.uk.billcomer.rentals.domain.User;

public interface UserService<T>
{
  public T getUserById(Long id);
  
  public T getUserByUsername(String username);

  public User getUserBySurname(String surname);
}
