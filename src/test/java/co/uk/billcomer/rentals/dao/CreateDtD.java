package co.uk.billcomer.rentals.dao;



import java.io.FileOutputStream;
import java.sql.DriverManager;

import org.dbunit.database.DatabaseConnection;
import org.dbunit.database.IDatabaseConnection;
import org.dbunit.dataset.xml.FlatDtdDataSet;

import java.sql.Connection;

public class CreateDtD
{
  public static void main(String[] args) throws Exception
  {
      // database connection
      Class driverClass = Class.forName("com.mysql.jdbc.Driver");
      Connection jdbcConnection = DriverManager.getConnection("jdbc:mysql://localhost:3306/rentals_test", "rentals", "rentals");
      IDatabaseConnection connection = new DatabaseConnection(jdbcConnection);

      // write DTD file
      FlatDtdDataSet.write(connection.createDataSet(), new FileOutputStream("rentals_test.dtd"));
  }
}
