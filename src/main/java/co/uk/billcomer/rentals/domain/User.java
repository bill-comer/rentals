package co.uk.billcomer.rentals.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;


//@Entity
//@Table(name="USER")
public class User implements Serializable
{

  private static final long serialVersionUID = -2398472394872948279L;
  
  private Long userId;
  
  private String username;
  private String forename;
  private String surname;
  private String email;
  
  @Id
  @Column(name="userId", nullable=false)
  @GeneratedValue(strategy=GenerationType.AUTO)
  public Long getUserId()
  {
    return userId;
  }
  public void setUserId(Long userId)
  {
    this.userId = userId;
  }
  
  
  @Column(name="username", nullable=false,length=128)
  public String getUsername()
  {
    return username;
  }
  public void setUsername(String username)
  {
    this.username = username;
  }
  

  @Column(name="forename", nullable=false,length=128)
  public String getForename()
  {
    return forename;
  }
  public void setForename(String forename)
  {
    this.forename = forename;
  }

  @Column(name="surname", nullable=false,length=128)
  public String getSurname()
  {
    return surname;
  }
  public void setSurname(String surname)
  {
    this.surname = surname;
  }
  

  @Column(name="email", nullable=false,length=128)
  public String getEmail()
  {
    return email;
  }
  public void setEmail(String email)
  {
    this.email = email;
  }
  
  
  
}
