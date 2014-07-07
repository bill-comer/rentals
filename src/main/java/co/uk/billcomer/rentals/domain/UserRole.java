package co.uk.billcomer.rentals.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

public class UserRole implements Serializable {

  private static final long serialVersionUID = -2398472394872948279L;


  private User user;
  private Long userRoleId;
  private String role;

  @Id
  @Column(name="userRoleId", nullable=false)
  @GeneratedValue(strategy=GenerationType.AUTO)
  public Long getUserRoleId()
  {
    return userRoleId;
  }
  public void setUserRoleId(Long userRoleId)
  {
    this.userRoleId = userRoleId;
  }

  @Column(name="role", nullable=false,length=128)
  public String getRole()
  {
    return role;
  }


  public void setRole(String role)
  {
    this.role = role;
  }
  
  @ManyToOne(fetch=FetchType.EAGER, optional=false)
  @JoinColumn(name="userId", nullable=false)
  public User getUser()
  {
    return user;
  }
  public void setUser(User user)
  {
    this.user = user;
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

    if (!(aObj instanceof UserRole))
    {
      return false;
    }

    UserRole castObj = (UserRole) aObj;

    return equality(getUserRoleId(), castObj.getUserRoleId())
      && equality(getRole(), castObj.getRole())
                  ;
  }

  /**
   * @see java.lang.Object#hashCode()
   */
  @Override
  public int hashCode()
  {
    int result = 17;
    result = 37 * (getUserRoleId() != null ? (result + getUserRoleId().hashCode()) : result);
    result = 37 * (getRole() != null ? (result + getRole().hashCode()) : result);
    result = 37 * (getUser() != null ? (result + getUser().hashCode()) : result);
    return result;
  }

  private boolean equality(Object aObjA, Object aObjB)
  {
    return (aObjA == null) == (aObjB == null)
      && (aObjA != null && aObjA.equals(aObjB))
      || aObjA == null;
  }
  
  public String toString() {
    return "userRoleId[" + getUserRoleId() +  "],role[" + getRole() + "]";
  }
}
