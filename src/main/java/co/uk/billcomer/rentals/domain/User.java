package co.uk.billcomer.rentals.domain;

import java.beans.Transient;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

import com.fasterxml.jackson.annotation.JsonIgnore;



@Entity
@Table(name="USER")
public class User implements Serializable
{

  private static final long serialVersionUID = -2398472394872948279L;
  
  private Long userId;
  
  private String username;
  private String forename;
  private String surname;
  private String email;
  

  private Collection<UserRole> userRoles = new ArrayList<UserRole>();
  
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
  
  @OneToMany(mappedBy="user")
  @Cascade({CascadeType.PERSIST, CascadeType.SAVE_UPDATE})
  public Collection<UserRole> getUserRoles()
  {
    return userRoles;
  }
  
  public void setUserRoles(Collection<UserRole> userRoles)
  {
    this.userRoles = userRoles;
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

    if (!(aObj instanceof User))
    {
      return false;
    }

    User castObj = (User) aObj;

    return equality(getUserId(), castObj.getUserId())
      && equality(getUsername(), castObj.getUsername())
      && equality(getEmail(), castObj.getEmail())
      && equality(getForename(), castObj.getForename())
      && equality(getSurname(), castObj.getSurname())
      && equality(getUserRoles(), castObj.getUserRoles())
                  ;
  }

  /**
   * @see java.lang.Object#hashCode()
   */
  @Override
  public int hashCode()
  {
    int result = 17;
    result = 37 * (getUserId() != null ? (result + getUserId().hashCode()) : result);
    result = 37 * (getUsername() != null ? (result + getUsername().hashCode()) : result);
    result = 37 * (getEmail() != null ? (result + getEmail().hashCode()) : result);
    result = 37 * (getForename() != null ? (result + getForename().hashCode()) : result);
    result = 37 * (getSurname() != null ? (result + getSurname().hashCode()) : result);
    result = 37 * (getUserRoles() != null ? (result + getUserRoles().hashCode()) : result);
    return result;
  }

  private boolean equality(Object aObjA, Object aObjB)
  {
    return (aObjA == null) == (aObjB == null)
      && (aObjA != null && aObjA.equals(aObjB))
      || aObjA == null;
  }
  
  public String toString() {
    return "username[" + getUsername() + "],forname[" + getForename() 
                + "],surname[" + getSurname() + "], email[" + getEmail() + "], roles[" + getUserRoles() + "]";
  }
  
  public boolean isUpdateOfFieldsRequired(String email, String surname, String forename)
  {
    if (!email.equals(this.email)) {      
      return true;
    }
    
    if (!surname.equalsIgnoreCase(this.surname)) {      
      return true;
    }
    
    if (!forename.equalsIgnoreCase(this.forename)) {      
      return true;
    }
    
    return false;
  }
  
}
