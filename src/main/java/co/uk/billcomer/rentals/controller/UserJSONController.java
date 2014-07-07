package co.uk.billcomer.rentals.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import co.uk.billcomer.rentals.domain.User;
import co.uk.billcomer.rentals.responder.Response;
import co.uk.billcomer.rentals.service.UserService;


@Controller
public class UserJSONController
{
  @Autowired(required=true)
  @Qualifier("rentals.userService")
  private UserService<User> userService;
  
  @RequestMapping( value="/user/id/{userId}", method = RequestMethod.GET )
  public @ResponseBody Response getUserById(@PathVariable Long userId) {
 
    Response response = null;
    
    User user = userService.getUserById(userId);
    
    
    if (user == null) {
      //handleFailedRequest("Failed to find a user with ID[" + userId + "]");
      response = Response.createFailedResponse("Failed to find a user with ID[" + userId + "]");
    }
    else {
      ArrayList<User> users = new ArrayList<User>();
      users.add(user);
      response = Response.createSuccessfulResponse(users);
    }
    
    return response;
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
  public @ResponseBody List<User> getUsersBySurname(@PathVariable String surname) {
 
    List<User> users = userService.getUsersBySurname(surname);
    
    if (users == null || users.size() == 0) {
      handleFailedRequest("Failed to find a user with SURNAME[" + surname + "]");
    }
    
    return users;
  }
  
  private void handleFailedRequest(String message) {
    throw new IllegalArgumentException(message);
  }
}
