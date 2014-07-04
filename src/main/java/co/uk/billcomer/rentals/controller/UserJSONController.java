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
 
    User user = new User();
    user.setUserId(userId);
    
    user.setUsername("foo bar");
    user.setEmail("foo@foobar.com");
 
    user = userService.getUserById(userId);
    return user;
 
  }
}
