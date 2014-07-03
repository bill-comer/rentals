package co.uk.billcomer.rentals.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import co.uk.billcomer.rentals.domain.User;


@Controller
public class UserJSONController
{
  @RequestMapping( value="/user/id/{userId}", method = RequestMethod.GET )
  public @ResponseBody User getUserById(@PathVariable Long userId) {
 
    User user = new User();
    user.setUserId(userId);
    
    user.setUsername("foo bar");
 
    return user;
 
  }
}
