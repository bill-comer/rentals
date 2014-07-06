package co.uk.billcomer.rentals.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import co.uk.billcomer.rentals.domain.User;
import co.uk.billcomer.rentals.service.UserService;


@Controller
public class UserJSONController
{
  @Autowired(required=true)
  @Qualifier("rentals.userService")
  private UserService<User> userService;
  
  @RequestMapping( value="/user/id/{userId}", method = RequestMethod.GET )
  public @ResponseBody User getUserById(@PathVariable Long userId) {
 
    User user = userService.getUserById(userId);
    
    if (user == null) {
      handleFailedRequest("Failed to find a user with ID[" + userId + "]");
    }
    
    return user;
  }
  
  @RequestMapping( value="/user/username/{username:[a-z.]+}", method = RequestMethod.GET )
  public @ResponseBody User getUserByUsername(@PathVariable String username) {
 
    User user = userService.getUserByUsername(username);
    
    if (user == null) {
      handleFailedRequest("Failed to find a user with USERNAME[" + username + "]");
    }
    
    return user;
  }
  

  
  @RequestMapping( value="/user/surname/{surname}", method = RequestMethod.GET )
  public @ResponseBody User getUserBySurname(@PathVariable String surname) {
 
    User user = userService.getUserBySurname(surname);
    
    if (user == null) {
      handleFailedRequest("Failed to find a user with SURNAME[" + surname + "]");
    }
    
    return user;
  }
  
  private void handleFailedRequest(String message) {
    throw new IllegalArgumentException(message);
  }
}
