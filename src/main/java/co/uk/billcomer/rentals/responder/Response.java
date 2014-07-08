package co.uk.billcomer.rentals.responder;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import co.uk.billcomer.rentals.domain.User;

public class Response implements Serializable
{

  private static final long serialVersionUID = -5983495834795L;
  
  private List<User> users;
  private Boolean success;
  private List<String> messages;
  
  public static Response createSuccessfulResponse(List<User> users) {
   Response response = new Response();
   response.setSuccess(true);
   response.setUsers(users);
   
   return response;
  }
  
  public static Response createFailedResponse( String message) {
    Response response = new Response();
    response.setSuccess(false);
    ArrayList<String> messages = new ArrayList<String>();
    messages.add(message);
    response.setMessages(messages);
    
    return response;
   }
  
  public static Response createSuccessfulResponse(List<User> users, String message)
  {
    Response response = createSuccessfulResponse(users);
    
    ArrayList<String> messages = new ArrayList<String>();
    messages.add(message);
    response.setMessages(messages);
    
    return response;
  }
  
  public static Response createFailedResponseForUser(User user, String message) {
    Response response = new Response();
    
    response.setSuccess(false);
    
    ArrayList<String> messages = new ArrayList<String>();
    messages.add(message);
    response.setMessages(messages);
    
    List<User> users = new ArrayList<User>();
    users.add(user);
    response.setUsers(users);
    
    return response;
   }
  
  public List<User> getUsers()
  {
    return users;
  }
  public void setUsers(List<User> users)
  {
    this.users = users;
  }
  
  public Boolean isSuccess()
  {
    return success;
  }
  public void setSuccess(Boolean success)
  {
    this.success = success;
  }

  public List<String> getMessages()
  {
    return messages;
  }
  public void setMessages(List<String> messages)
  {
    this.messages = messages;
  }
  
  @Override
  public boolean equals(Object aObj)
  {
    if (this == aObj)
    {
      return true;
    }

    if (aObj == null)
    {
      return false;
    }

    if (!(aObj instanceof Response))
    {
      return false;
    }

    Response castObj = (Response) aObj;

    return equality(getUsers(), castObj.getUsers())
      && equality(isSuccess(), castObj.isSuccess())
      && equality(getMessages(), castObj.getMessages())
                  ;
  }
  
  @Override
  public int hashCode()
  {
    int result = 17;
    result = 37 * (getUsers() != null ? (result + getUsers().hashCode()) : result);
    result = 37 * (isSuccess() != null ? (result + isSuccess().hashCode()) : result);
    result = 37 * (getMessages() != null ? (result + getMessages().hashCode()) : result);
    return result;
  }
  
  private boolean equality(Object aObjA, Object aObjB)
  {
    return (aObjA == null) == (aObjB == null)
      && (aObjA != null && aObjA.equals(aObjB))
      || aObjA == null;
  }

  
}
