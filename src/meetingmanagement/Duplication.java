/*
STATUS: DONE
  September 17th, 2021
  This class checks for duplication 
  if the same date and time are chosen from which is already there in database, 
  it warns the user and prevent the duplication
TO-DO: Calculation class 
HOW: Create an Array List to match (loop) read each object in a loop and compare
     all to see if they match A=A, B=B, C=C
 */
package meetingmanagement;

import java.util.ArrayList;

public class Duplication
{

  // datafields declarations 
  private ArrayList<ArrayList<String>> dataList;
  private ArrayList<String> dateList;
  private ArrayList<String> timeList;
  private String dateData;
  private String timeData;
  private String dateInput;
  private String timeInput;
  private int dateResult;
  private int timeResult;
  private int result;

  // Constructor for arraylist
  public Duplication(String date, String time)
  {
    //db Info 
    String dbName = "Meeting Management";
    String tableName = "Meetings";
    String[] columnNames =
    {
      "Day", "Time", "MeetingName", "Priority", "Invitees"
    };

    this.dateInput = date;
    this.timeInput = time;

    JavaDatabase dbObj = new JavaDatabase(dbName);
    dataList = dbObj.getData(tableName, columnNames);

    // for loop to check duplicated date
    for (int r = 1; r < dataList.size(); r++) 
    {
      dateData = dataList.get(r).get(0);
      if (dateInput.equals(dateData))
      {
        dateResult = r;
        //dateResult = 1; // If statement true (duplicated)
      }
      if (!dateInput.equals(dateData))
      {
        dateResult = 0; // If statement is false (not duplicated)
        continue;
      }
      // for loop to check duplicated time
      timeData = dataList.get(r).get(1);

      if (timeInput.equals(timeData))
      {
        timeResult = r;
        //timeResult = 1; // If statement true (duplicated)
      }
      else if (!timeInput.equals(timeData))
      {
        timeResult = 0; // If statement is false (not duplicated)
        continue;
      }
      if (dateResult == timeResult)
      {
        result = 1;
        break;
      }
    }
    // identifies the result, if both 1, then its duplicated
    if (dateResult == 1 && timeResult == 1)
    {
      result = 1; // duplicated
    }
    // not duplicated 
    else 
    {
      result = 0; // not dupicated
    }
    // 0 means not duplicated 
    // 1 means duplicated 
    System.out.println("result is " + result);
  }

  // Get and set method for result
  public int getResult()
  {
    return this.result;
  }

  public void setResult(int result)
  {
    this.result = result;
  }

  // Creating a toString method 
  @Override
  public String toString()
  {
    String duplication = "Result is " + result;

    return duplication;
  }

  public static void main(String[] args)
  {
    String date = "Tuesday";
    String time = "10:00AM";
    Duplication calculation = new Duplication(date, time);
    //new Duplication(date, time);
  }
}
