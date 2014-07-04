package co.uk.billcomer.rentals.dao;

import org.springframework.stereotype.Repository;

import co.uk.billcomer.rentals.domain.User;


@Repository("rentals.userDao")
public class UserDaoImpl extends GenericDao<User, Long> 
{

}
