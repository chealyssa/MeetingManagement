/*
STATUS : NOT DONE, NOT TESTED 
  September 21st, 2021
  Database that displays meeting by date, time, name, invitees and priority.
*/
package meetingmanagement;

// imports (not organized)
import java.sql.Connection;
import java.util.ArrayList;
import java.sql.Statement;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class JavaDatabase
{
  private String dbName;
  private Connection dbConn;
  private ArrayList<ArrayList<String>> data;
  private String dayFormatted;
  
  public JavaDatabase()
  {
    dbName = "";
    dbConn = null;
    data = null;
  }
  
  public JavaDatabase(String dbName)
  {
    setDbName(dbName);
    setDbConn();
    data = null;
  }

  // Get and Set method for DbName
  public String getDbName()
  {
    return dbName;
  }
  
  public void setDbName(String dbName)
  {
    this.dbName = dbName;
  }

  // Get and Set method for DbConn
  public Connection getDbConn()
  {
    return dbConn;
  }
  
  public void setDbConn()
  {
    String connectionURL = "jdbc:derby:" + this.dbName; 
    this.dbConn = null;
    try
    {
      Class.forName("org.apache.derby.jdbc.EmbeddedDriver");
      this.dbConn = DriverManager.getConnection(connectionURL);
    }
    catch(ClassNotFoundException ex)
    {
      System.out.println("Driver not found, check library");
    }
    catch (SQLException err)
    {
      System.out.println("SQL Connection error.");
    }
  }

  // Get method for Daily Data
  public ArrayList<ArrayList<String>> getDataDaily(String tableName, String[] 
                                                   tableHeaders)
  {
    // Constructing day - not used (I can't figure it out)
    LocalDateTime currentDateTime = LocalDateTime.now();
    DayOfWeek currentDay = currentDateTime.getDayOfWeek();
    DateTimeFormatter dayFormat = DateTimeFormatter.ofPattern("EEEE");
    dayFormatted = currentDateTime.format(dayFormat);
    System.out.println("Today is " +dayFormatted);
    
    int columnCount = tableHeaders.length;
    Statement s = null;
    ResultSet rs = null;
    String dbQuery = "SELECT * FROM " + tableName + " WHERE Day ='" 
                     +dayFormatted+ "' OR Day ='Date'";
    this.data = new ArrayList<>();
    // read the data 
    try 
    {
      // Send the query and recieve data
      s = this.dbConn.createStatement();
      rs = s.executeQuery(dbQuery);
      
      // read the data using rs and store in ArrayList data
      while (rs.next())
      {
        // row object to hold one row data
        ArrayList<String> row = new ArrayList<>();
        // go through the row and read each cell
        for (int i = 0; i < columnCount; i++)
        {
          // Read cell 1
          String cell = rs.getString(tableHeaders[i]);
          // add the cell to the row
          row.add(cell);
        }
        // add the row to the data
        this.data.add(row);
      }
    }
    catch(SQLException se)
    {
      System.out.println("SQL Error: Not able to get data");
    }
    return data;
  }
  
  // Get method for data
  public ArrayList<ArrayList<String>> getData(String tableName, 
                                              String[] tableHeaders)
  {
    int columnCount = tableHeaders.length; // columns in the table
    Statement s = null;
    ResultSet rs = null;
    String dbQuery = "SELECT * FROM " + tableName;
    this.data = new ArrayList<>();
    // read the data
    try 
    {
      // Send the query and recieve data
      s = this.dbConn.createStatement();
      rs = s.executeQuery(dbQuery);
      
      // read the data using rs and store in ArrayList data
      while (rs.next())
      {
        // row object to hold one row data
        ArrayList<String> row = new ArrayList<>();
        // go through the row and read each cell
        for (int i = 0; i < columnCount; i++)
        {
          // Read cell 1
          String cell = rs.getString(tableHeaders[i]);
          // add the cell to the row
          row.add(cell);
        }
        // add the row to the data
        this.data.add(row);
      }
    }
    catch(SQLException se)
    {
      System.out.println("SQL Error: Not able to get data");
    }
    return data;
  }

  // Set method for data
  public void setData(ArrayList<ArrayList<String>> data)
  {
    this.data = data;
  }
  
  // Creating the database
  public void createDb(String newDbName)
  {
    setDbName(newDbName);
    String connectionURL = "jdbc:derby:" + this.dbName
                         + ";create=true";
    String query = "CREATE DATABASE " + this.dbName;
    this.dbConn = null;
    try
    {
      Class.forName("org.apache.derby.jdbc.EmbeddedDriver");
      this.dbConn = DriverManager.getConnection(connectionURL);
      System.out.println("New Database " + this.dbName + " created");
    }
    catch(ClassNotFoundException ex) 
    {
      System.out.println("Driver not found, check library");
    }
    catch (SQLException se)
    {
      System.out.println("SQL Connection error, DB was not created");
    }
  }
  
  // Creating the table 
  public void createTable(String newTable, String dbName)
  {
    System.out.println(newTable);
    setDbName(dbName);
    setDbConn();
    Statement s;
    try
    {
      s = this.dbConn.createStatement();
      s.execute(newTable);
      System.out.println("New table created.");
      this.dbConn.close();
    }
    catch (SQLException err)
    {
      System.out.println("Error creating table " + newTable);
    }
  }
  
  //to convert 
  public Object[][] to2dArray(ArrayList<ArrayList<String>> data)
  {
    Object[][]dataList = null;
    if (data.size() == 0) // if database is empty
    {
      dataList = new Object[0][0]; // empty datalist
    }
    else // if there is a data 
    {
      int columnCount = data.get(0).size();
      dataList = new Object[data.size()][columnCount];
      for (int i = 0; i < data.size(); i++)
      {
        ArrayList<String> row = data.get(i);
        for (int j = 0; j < columnCount; j++)
        {
          dataList[i][j] = row.get(j);
        }
      }
    }
    return dataList;
  }
  
  public void closeDbConn()
  {
    try
    {
      this.dbConn.close();
    }
    catch (Exception err)
    {
      System.out.println("DB closing error.");
    }
  }
  
  public static void main(String[] args)
  {
    //db Info 
    String dbName = "Meeting Management";
    String tableName = "Meetings";
    String[] columnNames = {"Day", "Time", "MeetingName", "Priority", "Invitees", "Duration"};
    // create the conection
    JavaDatabase objDb = new JavaDatabase(dbName);
    Connection dbConnection = objDb.getDbConn();
    // query to run 
    String query = "INSERT INTO Meetings VALUES (?,?,?,?,?,?)";
    // read the data (monday, 9am, high, morning brief)
    String meetingDate = "Monday";
    String meetingTime = "9:00AM"; //FROM TIME TO STRING
    String meetingName = "Morning Brief";
    String meetingPriority = "High";
    String meetingInvites = "Person1, Person2, Person3";
    String meetingDuration = "1 Hour";
    
    try
    {
      // prepare statement 
      PreparedStatement ps = dbConnection.prepareStatement(query);
      // insert data into query 
      //date, time, name, invitees and priority.
      ps.setString(1, meetingDate);
      ps.setString(2, meetingTime);
      ps.setString(3, meetingName);
      ps.setString(4, meetingPriority);
      ps.setString(5, meetingInvites);
      ps.setString(6, meetingDuration);
      
      // execute the query 
      ps.executeUpdate();
      System.out.println("Data inserted succesfully");
    }
    catch (SQLException se)
    {
      System.out.println("Error inserting data");
    }
    ArrayList<ArrayList<String>> data = objDb.getData(tableName, columnNames);
    System.out.println(data);
  }
}
