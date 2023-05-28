/*
STATUS: DONE
  September 18th, 2021
  This class installs the database 
 */
package meetingmanagement;

public class InstallDatabase
{
  public static void main(String[] args)
  {
    String dbName = "Meeting Management";
    JavaDatabase objDb = new JavaDatabase();
    objDb.createDb(dbName);
    //{"Day", "Time", "MeetingName", "Priority", "Invitees"};
    String newTable = ("CREATE TABLE Meetings(Day varchar(10), Time varchar(10), "
                     + "MeetingName varchar(25), Priority varchar(10), Invitees "
                     + "varchar(100))");
    objDb.createTable(newTable, dbName);
  }
}
