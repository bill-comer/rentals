package co.uk.billcomer.rentals.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

public abstract class GenericDao<T>
{
  @Autowired(required = true)
  @Qualifier(value = "sessionFactory")
  protected SessionFactory sessionFactory;
  
  protected Class<T> classType;

  public T getById(final Long aId)
  {
    return (T) getSession().get(getClassType(), aId);
  }
  
  public Class<T> getClassType()
  {
    return classType;
  }
  
  protected Session getSession()
  {
    return sessionFactory.getCurrentSession();
  }
  
  public void setSessionFactory(SessionFactory aSessionFactory)
  {
    sessionFactory = aSessionFactory;
  }
}
