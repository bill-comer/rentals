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
import co.uk.billcomer.rentals.domain.UserRole;
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
      response = Response.createFailedResponse("Failed to find a user with ID[" + userId + "]");
    }
    else {
      response = createSuccessResponse(user);
    }
    
    return response;
  }
  
  @RequestMapping( value="/user/throw/{testValue}", method = RequestMethod.GET )
  public @ResponseBody Response testThrowingException(@PathVariable String testValue) {

    Response response = Response.createFailedResponse("oops");
    
    if (testValue.equals("true")) {
      throw new IllegalArgumentException("test throw");
    }
    
    return response;
  }
  

  
  @RequestMapping( value="/user/username/{username:[a-zA-Z.]+}", method = RequestMethod.GET )
  public @ResponseBody Response getUserByUsername(@PathVariable String username) {

    Response response = null;
    User user = userService.getUserByUsername(username);
    
    if (user == null) {
      response = Response.createFailedResponse("Failed to find a user with USERNAME[" + username + "]");
    }
    else {
      response = createSuccessResponse(user);
    }
    
    return response;
  }
  

  
  @RequestMapping( value="/user/surname/{surname}", method = RequestMethod.GET )
  public @ResponseBody Response getUsersBySurname(@PathVariable String surname) {

    Response response = null;
    List<User> users = userService.getUsersBySurname(surname);
    
    if (users == null || users.size() == 0) {
      response = Response.createFailedResponse("Failed to find a user with SURNAME[" + surname + "]");
    }
    else {
      response = Response.createSuccessfulResponse(users);
    }
    
    return response;
  }
  
  @RequestMapping( value="/user/create/{username}/{email}/{surname}/{forename}", method = RequestMethod.GET )
  public @ResponseBody Response create(
              @PathVariable String username,
              @PathVariable String email,
              @PathVariable String surname, 
              @PathVariable String forename 
              ) {

    Response response = null;
    

    User user = userService.getUserByUsername(username.toLowerCase());
    if (user != null) {
      response = Response.createFailedResponse("Failed to create a user with username[" + username + "]. Username already exists.");
    }
    else {    
      user = userService.createUser(username.toLowerCase(), email.toLowerCase(), surname, forename);

      if (user == null)
      {
        response = Response.createFailedResponse("Failed to create a user with username[" + username + "]");
      }
      else
      {
        response = createSuccessResponse(user);
      }
    }
    return response;
  }
  
  @RequestMapping( value="/user/update/{username}/{email}/{surname}/{forename}", method = RequestMethod.GET )
  public @ResponseBody Response update(
              @PathVariable String username,
              @PathVariable String email,
              @PathVariable String surname, 
              @PathVariable String forename 
              ) {

    Response response = null;
    

    User user = userService.getUserByUsername(username.toLowerCase());
    if (user == null) {
      response = Response.createFailedResponse("Failed to find a user with username[" + username + "].");
    }
    else {
      
      if (user.isUpdateOfFieldsRequired(email, surname, forename)) {
        user = userService.updateUser(user, email.toLowerCase(), surname, forename);
        
        if (user == null) {
          response = Response.createFailedResponse("Failed to create a user with username[" + username + "]");
        }
        else
        {
          response = createSuccessResponse(user);
        }
      } else {
        // No Update required
        response = Response.createFailedResponse("No Update required for User with username[" + username + "].");
      }
    }
    return response;
  }
  
  @RequestMapping( value="/user/withroles/{size}", method = RequestMethod.GET )
  public @ResponseBody Response withRoles(@PathVariable int size) {

    Response response = null;
    
    List<User> users = userService.getUsersWithNumberOfRoles(size);

    String rolePluraliation = " roles.";
    if (users.size() == 0) {
      if (size == 1) {
         rolePluraliation = " role.";
      }
      response = Response.createSuccessfulResponse(users, "No users found with the " + size + rolePluraliation);
    } else {
      
      response = Response.createSuccessfulResponse(users);
    }
    
    
    return response;
  }
  
  @RequestMapping( value="/user/withmanager/{manager}/{size}", method = RequestMethod.GET )
  public @ResponseBody Response withManagerAndRoles(@PathVariable String manager, @PathVariable int size) {

    Response response = null;
    
    List<User> users = userService.getUsersWithManagerAndNumberOfRoles(manager, size);

    if (users.size() == 0) {
      String rolePluraliation = " roles.";
      if (size == 1) {
         rolePluraliation = " role.";
      }
      response = Response.createSuccessfulResponse(users, "No users found with manager[" + manager + " and with " + size + rolePluraliation);
    } else {
      
      response = Response.createSuccessfulResponse(users);
    }
    
    
    return response;
  }
  
  @RequestMapping( value="/user/addmanager/{username}/{managerToAdd:[a-zA-Z.]+}", method = RequestMethod.GET )
  public @ResponseBody Response addManagerToUsers(@PathVariable String username, @PathVariable String managerToAdd) {

    Response response = null;
    
    User user = userService.getUserByUsername(username.toLowerCase());
    if (user == null) {
      response = Response.createFailedResponse("Failed to find a user with username[" + username + "].");
    }
    else {      
      User manager = userService.getUserByUsername(managerToAdd.toLowerCase());
      if (manager == null) {
        response = Response.createFailedResponse("Failed to find a manager with username[" + managerToAdd + "].");
      }
      else {
        boolean userAdded = userService.addManagerToUser(user, manager);

        List<User> users = new ArrayList<User>();
        users.add(user);
        response = Response.createSuccessfulResponse(users, "Successfully added Manager[" + managerToAdd + "] to user");
      }
    }
    
    
    return response;
  }
  
  @RequestMapping( value="/user/addrole/{username}/{newrole}", method = RequestMethod.GET )
  public @ResponseBody Response addRole(@PathVariable String username, @PathVariable String newrole) {

    Response response = null;
    boolean userAlreadyHasRole = false;
    
    User user = userService.getUserByUsername(username.toLowerCase());
    if (user == null) {
      response = Response.createFailedResponse("Failed to find a user with username[" + username + "]."); 
      response = Response.createFailedResponse("Failed to find a user with username[" + username + "].");    
    }
    else { 
      for (UserRole userRole : user.getUserRoles())
      {
        if(userRole.getRole().equalsIgnoreCase(newrole)) {
          userAlreadyHasRole = true;
          response = Response.createFailedResponse("User [" + username + "] already has role[" + newrole + "]."); 
        }
      }
      
      if (!userAlreadyHasRole) {
        UserRole userRole = new UserRole();
        userRole.setRole(newrole);
        userRole.setUser(user);
        user.getUserRoles().add(userRole);
        user = userService.updateUser(user);

        response = createSuccessResponse(user);
      }
    }
    
    return response;
  }

  private Response createSuccessResponse(User user)
  {
    Response response;
    ArrayList<User> users = new ArrayList<User>();
    users.add(user);
    response = Response.createSuccessfulResponse(users);
    
    return response;
  }
  
  
}
