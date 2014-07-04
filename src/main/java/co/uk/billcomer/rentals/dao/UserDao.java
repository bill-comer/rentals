package co.uk.billcomer.rentals.dao;

public interface UserDao<T>
{
    public T getUserById(Long id);
}
