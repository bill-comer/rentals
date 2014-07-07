package co.uk.billcomer.rentals.dao;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

@Repository
public abstract class GenericDao<T, ID extends Serializable>
{
  @Autowired(required = true)
  @Qualifier(value = "sessionFactory")
  protected SessionFactory sessionFactory;
  
  protected Class<T> classType;

  public T getById(final ID id)
  {
    return (T) getSession().get(getClassType(), id);
  }
  
  @SuppressWarnings("unchecked")
  public GenericDao()
  {
    Class<?> clazz = getClass();
    while (!(clazz.getGenericSuperclass() instanceof ParameterizedType))
    {
      clazz = clazz.getSuperclass();
    }
    classType = (Class<T>) ((ParameterizedType) clazz.getGenericSuperclass()).getActualTypeArguments()[0];
  }
  
  
  
  public Class<T> getClassType()
  {
    return classType;
  }
  
  public Session getSession()
  {
    return sessionFactory.getCurrentSession();
  }
  
  public void setSessionFactory(SessionFactory aSessionFactory)
  {
    sessionFactory = aSessionFactory;
  }
}
