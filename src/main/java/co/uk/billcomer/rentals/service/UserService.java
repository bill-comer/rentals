package co.uk.billcomer.rentals.service;

public interface UserService<T>
{
  public T getUserById(Long id);
}
